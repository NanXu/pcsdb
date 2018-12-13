package cn.edu.cauc.service.statistics.impl;

import javax.annotation.Resource;

import cn.edu.cauc.model.vo.KeywordsStatView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cauc.dao.statistics.IEventStatViewDao;
import cn.edu.cauc.model.vo.EventStatView;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.service.statistics.IStatisticsService;
import cn.edu.cauc.util.StringUtil;

@Transactional
@Service("statisticsService")
public class StatisticsServiceImpl implements IStatisticsService {
	
	@Resource
	private IEventStatViewDao eventStatViewDao;

	@Override
	public Page<EventStatView> statEventInfo(EventStatView eventStatView, Integer pageNo, Integer pageSize) {
		Page<EventStatView> page = new Page<EventStatView>();
		String type = eventStatView.getType();
		if(!StringUtil.isNull(type)) {
			switch (type) {
			case "eventType":
				page = eventStatViewDao.statEventTypeList(eventStatView, pageNo, pageSize);
				break;
			case "aircraftDamage":
				page = eventStatViewDao.statAircraftDamageList(eventStatView, pageNo, pageSize);
				break;
			case "phaseFlight":
				page = eventStatViewDao.statPhaseFlightList(eventStatView, pageNo, pageSize);
				break;
			case "aircraftModel":
				page = eventStatViewDao.statAircraftModelList(eventStatView, pageNo, pageSize);
				break;
			case "aircraftMake":
				page = eventStatViewDao.statAircraftMakeList(eventStatView, pageNo, pageSize);
				break;
			case "engineManufactuer":
				page = eventStatViewDao.statEngineManufactuerList(eventStatView, pageNo, pageSize);
				break;
			case "engineModel":
				page = eventStatViewDao.statEngineModelList(eventStatView, pageNo, pageSize);
				break;
			case "operator":
				page = eventStatViewDao.statOperatorList(eventStatView, pageNo, pageSize);
				break;
			case "maintainType":
				page = eventStatViewDao.statMaintainTypeList(eventStatView, pageNo, pageSize);
				break;
			case "source":
				page = eventStatViewDao.statSourceList(eventStatView, pageNo, pageSize);
				break;
			case "relateToSystem":
				page = eventStatViewDao.statRelateToSystemList(eventStatView, pageNo, pageSize);
				break;
			case "bugLocation":
				page = eventStatViewDao.statBugLocationList(eventStatView, pageNo, pageSize);
				break;
			case "bugModel":
				page = eventStatViewDao.statBugModelList(eventStatView, pageNo, pageSize);
				break;
			case "prevent":
				page = eventStatViewDao.statPreventList(eventStatView, pageNo, pageSize);
				break;
			case "maintain":
				page = eventStatViewDao.statMaintainList(eventStatView, pageNo, pageSize);
				break;
			default:
				break;
			}
		}
		return page;
	}

	@Override
	public Page<KeywordsStatView> statEventInfoByKeywords(KeywordsStatView keywordsStatView, Integer pageNo, Integer pageSize) {
		return eventStatViewDao.statKeywordsList(keywordsStatView, pageNo,pageSize );
	}

}
