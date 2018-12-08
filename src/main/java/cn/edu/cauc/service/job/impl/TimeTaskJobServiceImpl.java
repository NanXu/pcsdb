package cn.edu.cauc.service.job.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.edu.cauc.service.job.ITimeTaskJobService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cauc.dao.data.IAIDSRawDataDao;
import cn.edu.cauc.dao.data.INtsbAircraftDao;
import cn.edu.cauc.dao.data.INtsbEnginesDao;
import cn.edu.cauc.dao.data.INtsbEventDao;
import cn.edu.cauc.dao.data.INtsbEventsSequenceDao;
import cn.edu.cauc.dao.data.INtsbFindingsDao;
import cn.edu.cauc.dao.data.INtsbInjuryDao;
import cn.edu.cauc.dao.data.INtsbNarrativesDao;
import cn.edu.cauc.dao.data.ISdrRawDataDao;
import cn.edu.cauc.dao.event.ICasualtiesDao;
import cn.edu.cauc.dao.event.IEventInfoDao;
import cn.edu.cauc.dao.event.IMeasuresDao;
import cn.edu.cauc.dao.event.IPlaneDao;
import cn.edu.cauc.dao.event.IPollutantDao;
import cn.edu.cauc.dao.log.ISpiderLogDao;
import cn.edu.cauc.dao.log.IValidateFailedDetailLogDao;
import cn.edu.cauc.dao.log.IValidateLogDao;
import cn.edu.cauc.model.po.data.AidsRawData;
import cn.edu.cauc.model.po.data.NtsbAircraft;
import cn.edu.cauc.model.po.data.NtsbEngines;
import cn.edu.cauc.model.po.data.NtsbEvent;
import cn.edu.cauc.model.po.data.NtsbEventsSequence;
import cn.edu.cauc.model.po.data.NtsbFindings;
import cn.edu.cauc.model.po.data.NtsbNarratives;
import cn.edu.cauc.model.po.data.SdrRawData;
import cn.edu.cauc.model.po.event.Casualties;
import cn.edu.cauc.model.po.event.EventInfo;
import cn.edu.cauc.model.po.event.Measures;
import cn.edu.cauc.model.po.event.Plane;
import cn.edu.cauc.model.po.event.Pollutant;
import cn.edu.cauc.model.po.log.SpiderLog;
import cn.edu.cauc.model.po.log.ValidateFailedDetailLog;
import cn.edu.cauc.model.po.log.ValidateLog;
import cn.edu.cauc.util.Constant;
import cn.edu.cauc.util.DateUtil;
import cn.edu.cauc.util.StringUtil;
import cn.edu.cauc.util.html.JsoupUtil;

/**
 * 
 * 作者： 徐楠
 *
 * 描述：<p>TODO</p>
 * 创建时间：2016年10月12日
 */
@Service("timeTaskJobService")
public class TimeTaskJobServiceImpl implements ITimeTaskJobService {
	
	private static final Logger logger = Logger.getLogger(TimeTaskJobServiceImpl.class);
	
	private final String DEFAULT_USER = "admin";
	private final String RESOLVE_SUCCESS = "1";
	private final String RESOLVE_ERROR = "2";
	
	@Resource
	private IAIDSRawDataDao aidsRawDataDao;
	@Resource
	private ISpiderLogDao spiderLogDao;
	@Resource
	private ISdrRawDataDao sdrRawDataDao;
	@Resource
	private IValidateLogDao validateDao;
	@Resource
	private IValidateFailedDetailLogDao validateFailedDetailLogDao;
	@Resource
	private IEventInfoDao eventInfoDao;
	@Resource
	private IPlaneDao planeDao;
	@Resource
	private IPollutantDao pollutantDao;
	@Resource
	private IMeasuresDao  measuresDao;
	@Resource
	private ICasualtiesDao casualtiesDao;
	@Resource
	private INtsbEventDao ntsbEventDao;
	@Resource
	private INtsbAircraftDao ntsbAircraftDao;
	@Resource
	private INtsbEventsSequenceDao ntsbEventsSequenceDao;
	@Resource
	private INtsbNarrativesDao ntsbNarrativesDao;
	@Resource
	private INtsbEnginesDao ntsbEnginesDao;
	@Resource
	private INtsbFindingsDao ntsbFindingsDao;
	@Resource
	private INtsbInjuryDao ntsbInjuryDao;

	@Transactional(readOnly = true)
	public void spiderEventRemark() {
		logger.info("--------------开始执行爬虫程序【"+DateUtil.date2String(new Date())+"】--------------");
		List<AidsRawData> rawList = aidsRawDataDao.findImportedRawsData();
		logger.info("total = " + rawList.size() + " need spide");
		if(rawList != null && !rawList.isEmpty()) {
			for(AidsRawData rawData : rawList) {
				SpiderLog spiderLog = new SpiderLog();
				spiderLog.setSourceId(rawData.getReportNumber());
				spiderLog.setCreateDate(new Date());
				try {
					String eventRemark = JsoupUtil.captureAidsEventRemarks(rawData.getReportNumber());
					rawData.setEventRemarks(eventRemark);
					rawData.setReady(Constant.READY_SPIDER);
					aidsRawDataDao.modify(rawData);
					spiderLog.setStatus(Constant.SPIDER_SUCCESS);
					spiderLog.setContent("AIDS reportNumber =[" + rawData.getReportNumber() + "] Event Remarks Update Success");
					spiderLogDao.save(spiderLog);
					logger.info("reportNumber = " + rawData.getReportNumber() + " success!");
				} catch (Exception e) {
					spiderLog.setStatus(Constant.SPIDER_FAILARE);
					spiderLog.setContent("AIDS reportNumber =[" + rawData.getReportNumber() + "] Event Remarks Update Failure, Failure Log [" + e.getMessage() + "].");
					spiderLogDao.save(spiderLog);
					logger.info("reportNumber = " + rawData.getReportNumber() + " error!");
					continue;
				}
				
			}
		}
		logger.info("--------------爬虫程序执行完成【"+DateUtil.date2String(new Date())+"】--------------");
	}

	/**
	 * SDR数据解析如事件数据库
	 * 步骤：	1、查询待入库的数据
	 * 		2、解析数据并入库记录log
	 * 		3、数据苦如
	 */
	@Override
	@Transactional(readOnly = true)
	public void sdrToEvent() {
		logger.info("--------------SDR数据开始入事故数据库【"+DateUtil.date2String(new Date())+"】--------------");
		List<SdrRawData> sdrRasList = sdrRawDataDao.findPendingStorageList();//查询待入库的SDR数据
		logger.info("total = " + sdrRasList.size() + " need to resolve");
		if(sdrRasList != null && !sdrRasList.isEmpty()) {
			for(SdrRawData sdrRawData : sdrRasList) {
				try {
					resolveSdrData(sdrRawData);
					sdrRawData.setStatus(RESOLVE_SUCCESS);
					sdrRawDataDao.modify(sdrRawData);
					logger.info("Sequence Number = " + sdrRawData.getSeqNumber() + " success!");
				} catch (Exception e) {
					logger.info(sdrRawData.getSeqNumber() + "error,[" + e.getMessage() +"]");
					sdrRawData.setStatus(RESOLVE_ERROR);
					sdrRawDataDao.modify(sdrRawData);
					logger.info("Sequence Number = " + sdrRawData.getSeqNumber() + " error!");
					continue;
				}

			}
		}
		logger.info("--------------SDR数据入库完成【"+DateUtil.date2String(new Date())+"】--------------");
	}

	@Override
	@Transactional(readOnly = true)
	public void ntsbToEvent() {
		logger.info("--------------NTSB数据开始入事故数据库【"+DateUtil.date2String(new Date())+"】--------------");
		List<NtsbEvent> ntsbEventList = ntsbEventDao.findPendingStorageList();//查询待入库的NTSB数据
		logger.info("total = " + ntsbEventList.size() + " need to resolve");
		if(ntsbEventList != null && !ntsbEventList.isEmpty()) {
			for(NtsbEvent ntsbEvent : ntsbEventList) {
				try {
					resolveNtsbData(ntsbEvent);
					ntsbEvent.setStatus(RESOLVE_SUCCESS);
					ntsbEventDao.modify(ntsbEvent);
					logger.info("Sequence Number = " + ntsbEvent.getEventId() + " success!");
				} catch (Exception e) {
					e.printStackTrace();
					logger.info(ntsbEvent.getEventId() + "error,[" + e.getMessage() +"]");
					ntsbEvent.setStatus(RESOLVE_ERROR);
					ntsbEventDao.modify(ntsbEvent);
					logger.info("Event ID = " + ntsbEvent.getEventId() + " error!");
					continue;
				}
			}
		}

		logger.info("--------------NTSB数据入库完成【"+DateUtil.date2String(new Date())+"】--------------");
	}

	/**
	 * 解析NTSB原始数据
	 * 
	 * @param ntsbEvent
	 */
	private void resolveNtsbData(NtsbEvent ntsbEvent) {
		ValidateLog log = new ValidateLog();
		if(ntsbEvent != null) {
			String eventId = ntsbEvent.getEventId();
			log.setDataID(eventId);
			log.setCteateTime(new Date());
			log.setStatus(Constant.VALIDATE_STATUS_SUCCESS);
			validateDao.add(log);
			
			NtsbAircraft aircraft = ntsbAircraftDao.findById(eventId);
			NtsbEventsSequence eventsSequence = ntsbEventsSequenceDao.findById(eventId);
			NtsbNarratives narratives = ntsbNarrativesDao.findById(eventId);
			NtsbEngines engines = ntsbEnginesDao.findById(eventId);
			NtsbFindings findings = ntsbFindingsDao.findById(eventId);
			
			//事件基本信息
			EventInfo event = new EventInfo();
			
			event.setSource(Constant.NTSB);
			event.setSourceId(ntsbEvent.getEventId());
			event.setEventType(ntsbEvent.getEventType());
			event.setLocalDate(ntsbEvent.getLocalDate());
			if(aircraft != null) {
				event.setLastDeparturePoint(aircraft.getDprtAptId());
				event.setDestinationLocal(aircraft.getDestAptId());
				event.setAircraftDamage(aircraft.getDamage());
			}
			event.setEventLocation(ntsbEvent.getCity());
			event.setWeatherConditions(ntsbEvent.getWxCondBasic());
			if(eventsSequence != null) {
				event.setPhaseFlight(eventsSequence.getPhaseNo());
			}
			if(narratives != null) {
				event.setEventRemarks(narratives.getNarrAccf());
				event.setReasonRemarks(narratives.getNarrCause());
			}
			event.setStatus(Constant.EVENT_PUBLISH);//导入数据为发布状态
			event.setDraftUser(DEFAULT_USER);
			event.setDraftTime(new Date());
			event.setApproveUser(DEFAULT_USER);
			event.setApproveTime(new Date());
			eventInfoDao.add(event);
			//飞机信息
			Plane plane = new Plane(); 
			plane.setEventId(event.getId());
			if(aircraft != null) {
				plane.setAircraftModel(aircraft.getAcftModel());
				plane.setAircraftMake(aircraft.getAcftMake());
				plane.setRegistrationNumber(aircraft.getRegistNo());
				plane.setOperator(aircraft.getOperatorName());
				plane.setRunRules(aircraft.getFarPart());
				plane.setRunTotalHours(aircraft.getArmHrs()+"");
				plane.setMaintainType(aircraft.getTypeLastInsp());
				plane.setMaintainHours(aircraft.getArmHrsLastInsp()+"");
			}
			if(engines != null) {
				plane.setEngineManufactuer(engines.getEngMfgr());
				plane.setEngineModel(engines.getEngModel());
				plane.setNumberOfEngines(engines.getEngNumber()+"");
			}
			planeDao.add(plane);
			//Pollutant(污染物信息)
			Pollutant pollutant = new Pollutant();
			if(findings != null) {
				pollutant.setEventId(event.getId());
				pollutant.setSource(findings.getCategoryNo());
				if(findings.getCategoryNo().equals("01") && findings.getSubCategoryNo().equals("02")){
					//污染物来源是飞机系统的，填写涉及系统字段
					pollutant.setRelateToSystem(findings.getSectionNo());
				}
				pollutant.setBugModel(findings.getModifierNo());
				pollutantDao.add(pollutant);
			}
			//采取措施(remark中提取)
			Measures measures = new Measures();
			measures.setEventId(event.getId());
			String prevent = "";
//			if(aircraft != null) {
//				String evacuation = aircraft.getEvacuation();
//				prevent += evacuation;
//			}
			if(narratives != null) {
				String narrAccp = narratives.getNarrAccp();
				if(!StringUtil.isNull(prevent)) {
					prevent += narrAccp;
				}
			}
			measures.setPrevent(prevent);
			measuresDao.add(measures);
			
			//伤亡信息
			Casualties casualties = new Casualties();
			String ntsbId = event.getSourceId();
			int crewFatals = ntsbInjuryDao.findCrewFatals(ntsbId);
			int crewSerious = ntsbInjuryDao.findCrewSerious(ntsbId);
			int crewMinor = ntsbInjuryDao.findCrewMinors(ntsbId);
			int passengerFatal = ntsbInjuryDao.findPassengerFatals(ntsbId);
			int passengerSerious = ntsbInjuryDao.findPassengerSerious(ntsbId);
			int passengerMinor = ntsbInjuryDao.findPassengerMinors(ntsbId);
			casualties.setEventId(event.getId());
			casualties.setCrewFatal(crewFatals);
			casualties.setCrewSerious(crewSerious);
			casualties.setCrewMinor(crewMinor);
			casualties.setPassengerFatal(passengerFatal);
			casualties.setPassengerSerious(passengerSerious);
			casualties.setPassengerMinor(passengerMinor);
			casualties.setFatalities(crewFatals+passengerFatal+"");
			casualties.setInjuries(crewSerious+passengerSerious+"");
			casualtiesDao.add(casualties);
		}
	}

	/**
	 * 解析原始数据
	 * 
	 * @param sdrRawData
	 */
	private void resolveSdrData(SdrRawData sdrRawData) {
		ValidateLog log = new ValidateLog();
		if(sdrRawData != null) {
			log.setDataID(sdrRawData.getSeqNumber());
			log.setCteateTime(new Date());
			log.setStatus(Constant.VALIDATE_STATUS_SUCCESS);
			validateDao.add(log);
			
			//事件基本信息
			EventInfo event = new EventInfo();
			//event.setId(generatorEventID());
			event.setSource(Constant.SDR);
			event.setSourceId(sdrRawData.getSeqNumber());
			
			try {
				Date date = DateUtil.format(sdrRawData.getOccurrenceDate(), "yyyyMMdd");
				event.setLocalDate(date);
			} catch (ParseException e) {
				ValidateFailedDetailLog detailLog = new ValidateFailedDetailLog();
				detailLog.setValidateLogId(log.getId());
				detailLog.setField("Local Date");
				detailLog.setContent("日期转化错误");
				log.setStatus(Constant.VALIDATE_STATUS_FALIARE);
				validateDao.modify(log);
				validateFailedDetailLogDao.add(detailLog);
				//e.printStackTrace();
				logger.debug("seqNumber = "+sdrRawData.getSeqNumber()+" Local Event Date 转化错误");
			}
			event.setPhaseFlight(sdrRawData.getOperationText());
			event.setEventRemarks(sdrRawData.getRemark());
			event.setStatus(Constant.EVENT_PUBLISH);//导入数据为发布状态
			event.setDraftUser(DEFAULT_USER);
			event.setDraftTime(new Date());
			event.setApproveUser(DEFAULT_USER);
			event.setApproveTime(new Date());
			eventInfoDao.add(event);
			//飞机信息
			Plane plane = new Plane();
			plane.setEventId(event.getId());
			plane.setAircraftModel(sdrRawData.getAircraftManufacturerCode());
			plane.setAircraftMake(sdrRawData.getAircraftManufacturerName());
			plane.setRegistrationNumber(sdrRawData.getAircraftRegistrationNumber());
			plane.setEngineManufactuer(sdrRawData.getEngineManufacturerName());
			plane.setEngineModel(sdrRawData.getEngineManufacturerModel());
			plane.setNumberOfEngines(sdrRawData.getNumberOfEngines());
			plane.setOperator(sdrRawData.getOperatorCode());
			planeDao.add(plane);
			//Pollutant(污染物信息)
			Pollutant pollutant = new Pollutant();
			pollutant.setEventId(event.getId());
			pollutant.setSource(sdrRawData.getPartName());
			pollutant.setRelateToSystem(sdrRawData.getAtaCode());
			pollutant.setBugModel(sdrRawData.getFailedPartDesc());
			pollutantDao.add(pollutant);
			//采取措施(remark中提取)
			Measures measures = new Measures();
			measures.setEventId(event.getId());
			measures.setControls(sdrRawData.getPreDesc());
			measures.setMaintain(sdrRawData.getMaintain());
			if(sdrRawData.getMeasureRemark().equals("N")) {
				measures.setRemark("N");	
			}
			measuresDao.add(measures);
		}
	}
}
