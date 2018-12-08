package cn.edu.cauc.service.event.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cauc.dao.event.IApproveInfoDao;
import cn.edu.cauc.dao.event.ICasualtiesDao;
import cn.edu.cauc.dao.event.IEventInfoDao;
import cn.edu.cauc.dao.event.IEventViewDao;
import cn.edu.cauc.dao.event.IMeasuresDao;
import cn.edu.cauc.dao.event.IPlaneDao;
import cn.edu.cauc.dao.event.IPollutantDao;
import cn.edu.cauc.model.po.event.ApproveInfo;
import cn.edu.cauc.model.po.event.Casualties;
import cn.edu.cauc.model.po.event.EventInfo;
import cn.edu.cauc.model.po.event.EventView;
import cn.edu.cauc.model.po.event.Measures;
import cn.edu.cauc.model.po.event.Plane;
import cn.edu.cauc.model.po.event.Pollutant;
import cn.edu.cauc.model.po.system.UserRoleRelation;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.service.event.IEventService;
import cn.edu.cauc.util.ConfigReader;
import cn.edu.cauc.util.Constant;
import cn.edu.cauc.util.StringUtil;

@Transactional
@Service("eventService")
public class EventServiceImpl implements IEventService {
	
	@Resource
	private IEventInfoDao eventInfoDao;
	@Resource
	private IPlaneDao planeDao;
	@Resource
	private ICasualtiesDao casualtiesDao;
	@Resource
	private IMeasuresDao measuresDao;
	@Resource
	private IPollutantDao pollutantDao;
	@Resource
	private IApproveInfoDao approveInfoDao;
	@Resource
	private IEventViewDao eventViewDao;

	@Override
	public Page<EventView> findEventList(EventView event, int pageNo,
			Integer pageSize) {
		long startTime = new Date().getTime();
		Page<EventView> pageList = eventViewDao.findEventViewPageList(event, pageNo, pageSize);
		long endTime = new Date().getTime();
		System.out.println("---------总共"+(endTime - startTime)+"ms-------");
		if(pageList != null) {
			List<EventView> eventList = pageList.getList();
			if(eventList != null && !eventList.isEmpty()) {
				for(EventView info : eventList) {
					String source = info.getSource();
					if(source.equals(Constant.NTSB)) { //NTSB数据
						//飞行阶段显示
						String phaseNo = info.getPhaseFlight();
						if(!StringUtil.isNull(phaseNo)) {
							String value = ConfigReader.getValue("phase.no."+phaseNo.trim());
							if(!StringUtil.isNull(value)) {
								info.setPhaseDesc(value);
							}
						}
						//天气状况显示
						String weatherConditions = info.getWeatherConditions();
						if(!StringUtil.isNull(weatherConditions)) {
							String value = ConfigReader.getValue("weather.conditions."+weatherConditions.trim());
							if(!StringUtil.isNull(value)) {
								info.setWeather(value);
							}
						}
						//飞机损害程度
						String aircraftDamage = info.getAircraftDamage();
						if(!StringUtil.isNull(aircraftDamage)) {
							String value = ConfigReader.getValue("aircraft.damage."+aircraftDamage.trim());
							if(!StringUtil.isNull(value)) {
								info.setDamage(value);
							}
						}
					}
				}
			}
		}
		return pageList;
	}

	@Override
	public List<EventView> findEventViewList(EventView event) {
		return eventViewDao.findEventViewList(event);
	}

	@Override
	public EventView findEventViewById(Integer id) {
		EventView eventView = eventViewDao.getById(id);
		String source = eventView.getSource();
		if(source.equals(Constant.NTSB)) { //NTSB数据
			String phaseNo = eventView.getPhaseFlight();
			if(!StringUtil.isNull(phaseNo)) {
				String value = ConfigReader.getValue("phase.no."+phaseNo.trim());
				if(!StringUtil.isNull(value)) {
					eventView.setPhaseDesc(value);
				}
			}
			
			//天气状况显示
			String weatherConditions = eventView.getWeatherConditions();
			if(!StringUtil.isNull(weatherConditions)) {
				String value = ConfigReader.getValue("weather.conditions."+weatherConditions.trim());
				if(!StringUtil.isNull(value)) {
					eventView.setWeather(value);
				}
			}
			//飞机损害程度
			String aircraftDamage = eventView.getAircraftDamage();
			if(!StringUtil.isNull(aircraftDamage)) {
				String value = ConfigReader.getValue("aircraft.damage."+aircraftDamage.trim());
				if(!StringUtil.isNull(value)) {
					eventView.setDamage(value);
				}
			}
			
			String pollutantSource = eventView.getPullutantSource();
			if(!StringUtil.isNull(pollutantSource)) {
				String value = ConfigReader.getValue("pollutant.source."+pollutantSource.trim());
				if(!StringUtil.isNull(value)) {
					eventView.setSourceName(value);
				}
			}
			String bugModel = eventView.getBugModel();
			if(!StringUtil.isNull(bugModel)) {
				String value = ConfigReader.getValue("bug.model."+bugModel.trim());
				if(!StringUtil.isNull(value)) {
					eventView.setBugModelName(value);
				}
			}
		}
		return eventView;
	}

	@Override
	public Page<EventInfo> findDraftEventList(EventInfo event, int pageNo,
			Integer pageSize) {
		return eventInfoDao.findDraftEventList(event, pageNo, pageSize);
	}

	@Override
	public EventInfo findEventById(Integer id) {
		EventInfo info = eventInfoDao.getById(id);
		String source = info.getSource();
		if(source.equals(Constant.NTSB)) { //NTSB数据
			String phaseNo = info.getPhaseFlight();
			if(!StringUtil.isNull(phaseNo)) {
				String value = ConfigReader.getValue("phase.no."+phaseNo.trim());
				if(!StringUtil.isNull(value)) {
					info.setPhaseDesc(value);
				}
			}
			
			//天气状况显示
			String weatherConditions = info.getWeatherConditions();
			if(!StringUtil.isNull(weatherConditions)) {
				String value = ConfigReader.getValue("weather.conditions."+weatherConditions.trim());
				if(!StringUtil.isNull(value)) {
					info.setWeather(value);
				}
			}
			//飞机损害程度
			String aircraftDamage = info.getAircraftDamage();
			if(!StringUtil.isNull(aircraftDamage)) {
				String value = ConfigReader.getValue("aircraft.damage."+aircraftDamage.trim());
				if(!StringUtil.isNull(value)) {
					info.setDamage(value);
				}
			}
		}
		return info;
	}

	@Override
	public Plane findPlaneByEventId(Integer eventId) {
		return planeDao.findPlaneByEventId(eventId);
	}

	@Override
	public Casualties findCasualtiesByEventId(Integer eventId) {
		return casualtiesDao.findCasualtiesByEventId(eventId);
	}

	@Override
	public Measures findMeasuresByEventId(Integer eventId) {
		return measuresDao.findMeasuresByEventId(eventId);
	}

	@Override
	public Pollutant findPollutantByEventId(Integer eventId) {
		EventInfo event = eventInfoDao.getById(eventId);
		Pollutant pollutant = pollutantDao.findPollutantByEventId(eventId);
		if(event.getSource().equals(Constant.NTSB) && pollutant != null) {
			String source = pollutant.getSource();
			if(!StringUtil.isNull(source)) {
				String value = ConfigReader.getValue("pollutant.source."+source.trim());
				if(!StringUtil.isNull(value)) {
					pollutant.setSourceName(value);
				}
			}
			String bugModel = pollutant.getBugModel();
			if(!StringUtil.isNull(bugModel)) {
				String value = ConfigReader.getValue("bug.model."+bugModel.trim());
				if(!StringUtil.isNull(value)) {
					pollutant.setBugModelName(value);
				}
			}
		}
		
		return pollutant;
	}

	@Override
	public Integer saveEventInfo(EventInfo event) {
		return eventInfoDao.insert(event);
	}

	@Override
	public void savePlane(Plane plane) {
		planeDao.insert(plane);
	}

	@Override
	public void saveCasualties(Casualties casualties) {
		casualtiesDao.insert(casualties);
	}

	@Override
	public void savePollutant(Pollutant pollutant) {
		pollutantDao.insert(pollutant);
	}

	@Override
	public void saveMeasures(Measures measures) {
		measuresDao.insert(measures);
	}

	@Override
	public void approveEvent(ApproveInfo approveInfo) {
		approveInfoDao.insert(approveInfo);
	}

	@Override
	public void modifyEventInfo(EventInfo eventInfo) {
		eventInfoDao.update(eventInfo);
	}

	@Override
	public void edit(EventView view) {
		eventViewDao.update(view);
	}

	@Override
	public void modifyPlane(Plane p) {
		planeDao.update(p);
	}

	@Override
	public void modifyCasualties(Casualties c) {
		casualtiesDao.update(c);
	}

	@Override
	public void modifyPollutant(Pollutant po) {
		pollutantDao.update(po);
	}

	@Override
	public void modifyMeasures(Measures m) {
		measuresDao.update(m);
	}

	@Override
	public void deleteEvents(String ids) {
		String[] idArray = ids.split(",");
		if(idArray != null && idArray.length > 0) {
			for(int i = 1; i < idArray.length; i++) {
				Integer id = Integer.parseInt(idArray[i]);
				EventInfo eventInfo = eventInfoDao.getById(id);
				eventInfo.setIsDelete("1");//删除
				eventInfoDao.update(eventInfo);
			}
		}
	}

	
}
