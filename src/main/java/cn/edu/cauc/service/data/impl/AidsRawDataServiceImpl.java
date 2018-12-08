package cn.edu.cauc.service.data.impl;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cauc.dao.data.IAIDSRawDataDao;
import cn.edu.cauc.dao.event.ICasualtiesDao;
import cn.edu.cauc.dao.event.IEventInfoDao;
import cn.edu.cauc.dao.event.IPlaneDao;
import cn.edu.cauc.dao.log.ISpiderLogDao;
import cn.edu.cauc.dao.log.IValidateFailedDetailLogDao;
import cn.edu.cauc.dao.log.IValidateLogDao;
import cn.edu.cauc.model.po.data.AidsRawData;
import cn.edu.cauc.model.po.event.Casualties;
import cn.edu.cauc.model.po.event.EventInfo;
import cn.edu.cauc.model.po.event.Plane;
import cn.edu.cauc.model.po.log.SpiderLog;
import cn.edu.cauc.model.po.log.ValidateFailedDetailLog;
import cn.edu.cauc.model.po.log.ValidateLog;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.model.vo.SessionUser;
import cn.edu.cauc.service.data.IAidsRawDataService;
import cn.edu.cauc.util.ConfigReader;
import cn.edu.cauc.util.Constant;
import cn.edu.cauc.util.DateUtil;
import cn.edu.cauc.util.FileUtil;
import cn.edu.cauc.util.StringUtil;
import cn.edu.cauc.util.csv.CsvUtil;
import cn.edu.cauc.util.html.JsoupUtil;

@Transactional
@Service("aidsRawDataService")
public class AidsRawDataServiceImpl implements IAidsRawDataService {
	
	private final String AIDS_DATA_PATH = "adis.file.load.path";
	
	private static final Logger logger = Logger.getLogger(AidsRawDataServiceImpl.class);
	
	/*注入dao*/
	
	@Resource
	private IAIDSRawDataDao aidsRawDataDao;
	@Resource
	private IEventInfoDao eventInfoDao;
	@Resource
	private IValidateLogDao validateDao;
	@Resource
	private IValidateFailedDetailLogDao validateFailedDetailLogDao;
	@Resource
	private IPlaneDao planeDao;
	@Resource
	private ICasualtiesDao casualtiesDao;
	@Resource
	private ISpiderLogDao spiderLogDao;

	/**
	 * 导入CSV格式数据
	 */
	@Override
	public void importAidsRawDatas() throws Exception {
		String pathname = ConfigReader.getValue(AIDS_DATA_PATH);
		if(!StringUtil.isNull(pathname)) {//路径不为空 读取数据 此路径为文件夹路径
			//1.扫描路径下csv数据文件
			File file = new File(pathname);
			if(file.isFile() && file.exists()) {
				List<AidsRawData> rawDataList = CsvUtil.readAIDSRawDataFromCsv(pathname);
				//2.解析数据
				aidsRawDataDao.batchInsert(rawDataList);
				//3.删除所有数据文件
				FileUtil.delAllFile(pathname);
			}
		}
	}

	/**
	 * 抽取数据信息
	 * 
	 * 1、查询所有待入库数据（ready = "1"）
	 * 2、解析数据记录解析日志
	 * 3、转化到事件对象，保存对象
	 */
	@Override
	public void extractRaws(SessionUser sessionUser) {
		List<AidsRawData> rawList = aidsRawDataDao.findReadyRawsDatas();
		if(rawList != null && !rawList.isEmpty()) {
			for(AidsRawData rawData : rawList) {
				try {
					//数据解析转化日志记录
					resolveRawData(rawData, sessionUser);
					rawData.setReady(Constant.READY_COMPLETED);
					aidsRawDataDao.update(rawData);
				} catch (Exception e) {
					continue;
//					rawData.setStatus(Constant.s);
//					aidsRawDataDao.update(rawData);
				}
			}
		}
	}

	/**
	 * 列表查询方法
	 */
	@Override
	public Page<AidsRawData> findRawList(AidsRawData aidsRawData,
			Integer pageNo, Integer pageSize) {
		return aidsRawDataDao.findRawList(aidsRawData, pageNo, pageSize);
	}

	@Override
	public AidsRawData findRawDataByReportNumber(String reportNumber) {
		return aidsRawDataDao.findRawDataByReportNumber(reportNumber);
	}
	
	/**
	 * 1.查询需要抓取的reportNumber
	 * 2.抓取数据
	 * 3.记录抓取日志
	 */
	@Override
	public void spiderEventRemark() {
		logger.info("--------------开始执行爬虫程序【"+DateUtil.date2String(new Date())+"】--------------");
		List<AidsRawData> rawList = aidsRawDataDao.findImportedRawsData();
		if(rawList != null && !rawList.isEmpty()) {
			for(AidsRawData rawData : rawList) {
				SpiderLog spiderLog = new SpiderLog();
				spiderLog.setSourceId(rawData.getReportNumber());
				spiderLog.setCreateDate(new Date());
				try {
					String eventRemark = JsoupUtil.captureAidsEventRemarks(rawData.getReportNumber());
					rawData.setEventRemarks(eventRemark);
					aidsRawDataDao.update(rawData);
					spiderLog.setStatus(Constant.SPIDER_SUCCESS);
					spiderLog.setContent("AIDS reportNumber =[" + rawData.getReportNumber() + "] Event Remarks Update Success");
					spiderLogDao.insert(spiderLog);
				} catch (Exception e) {
					spiderLog.setStatus(Constant.SPIDER_FAILARE);
					spiderLog.setContent("AIDS reportNumber =[" + rawData.getReportNumber() + "] Event Remarks Update Failure, Failure Log [" + e.getMessage() + "].");
					spiderLogDao.insert(spiderLog);
					continue;
				}
				
			}
		}
		logger.info("--------------爬虫程序执行完成【"+DateUtil.date2String(new Date())+"】--------------");
	}

	//	private String generatorEventID() {
//		String id = "";
//		String genId = EventIDGenerator.generator();
//		String maxId = eventInfoDao.findDistinctMaxID(genId);
//		if(!StringUtil.isNull(maxId)) {
//			String suffix = maxId.substring(genId.length());
//			if(StringUtil.isNull(suffix)) {
//				id = genId + "a";
//			} else {
//				char c = suffix.charAt(0);
//				char cur = (char)((int)c + 26);
//				id = genId+cur;
//			}
//		} else {
//			id = genId;
//		}
//		return id;
//	}
//	
	/**
	 * 解析原始数据
	 * 
	 * @param raw
	 */
	private void resolveRawData(AidsRawData raw, SessionUser sessionUser) {
		ValidateLog log = new ValidateLog();
		if(raw != null) {
			log.setDataID(raw.getReportNumber());
			log.setCteateTime(new Date());
			log.setStatus(Constant.VALIDATE_STATUS_SUCCESS);
			validateDao.insert(log);
			//事件基本信息
			EventInfo event = new EventInfo();
			//event.setId(generatorEventID());
			event.setSource(Constant.AIDS);
			event.setSourceId(raw.getReportNumber());
			event.setEventType(raw.getEventType());
			try {
				Date date = DateUtil.formatEnglishDateStr(raw.getLocalEventDate());
				event.setLocalDate(date);
			} catch (ParseException e) {
				ValidateFailedDetailLog detailLog = new ValidateFailedDetailLog();
				detailLog.setValidateLogId(log.getId());
				detailLog.setField("Local Date");
				detailLog.setContent("日期转化错误");
				log.setStatus(Constant.VALIDATE_STATUS_FALIARE);
				validateDao.update(log);
				validateFailedDetailLogDao.insert(detailLog);
				//e.printStackTrace();
				logger.debug("reportNumber = "+raw.getReportNumber()+" Local Event Date 转化错误");
			}
			event.setEventLocation(raw.getEventCity() + " " +raw.getEventState());//市+州
			//event.setWeatherConditions(raw.getp); //天气状况字段不存在
			event.setAircraftDamage(raw.getAircraftDamage());
			event.setPhaseFlight(raw.getFlightPhase());
			event.setEventRemarks(raw.getEventRemarks());
			event.setStatus(Constant.EVENT_PUBLISH);//导入数据为发布状态
			event.setDraftUser(sessionUser.getUsername());
			event.setDraftTime(new Date());
			event.setApproveUser(sessionUser.getUsername());
			event.setApproveTime(new Date());
			eventInfoDao.insert(event);
			//污染物信息从remark总提取
			//飞机信息
			Plane plane = new Plane();
			plane.setEventId(event.getId());
			plane.setAircraftModel(raw.getAircraftModel());
			plane.setAircraftMake(raw.getAircraftMake());
			plane.setRegistrationNumber(raw.getAircraftRegistrationNbr());
			plane.setEngineManufactuer(raw.getAircraftEngineMake());
			plane.setEngineModel(raw.getAircraftEngineModel());
			plane.setNumberOfEngines(raw.getNbrofEngines());
			plane.setOperator(raw.getOperator());
			planeDao.insert(plane);
			//采取措施(remark中提取)
			//受伤情况
			Casualties casu = new Casualties();
			casu.setEventId(event.getId());
			casu.setFatalities(raw.getTotalFatalities());
			casu.setInjuries(raw.getTotalInjuries());
			casualtiesDao.insert(casu);
		}
	}
}
