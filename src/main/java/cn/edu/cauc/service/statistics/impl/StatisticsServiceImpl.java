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

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("statisticsService")
public class StatisticsServiceImpl implements IStatisticsService {

	private static final List<String> keywordsList = new ArrayList<String>();
	static {
		keywordsList.add("thunderstorm");
		keywordsList.add("icing");
		keywordsList.add("rain");
		keywordsList.add("moisture");
		keywordsList.add("fog");
		keywordsList.add("snow");
		keywordsList.add("wet");
		keywordsList.add("crosswind");
		keywordsList.add("freezing rain");
		keywordsList.add("cold");
		keywordsList.add("low temperature");
		keywordsList.add("turbulence");
		keywordsList.add("visibility");
		keywordsList.add("bad weather");
		keywordsList.add("nimbus");
		keywordsList.add("wind shear");
		keywordsList.add("gusty wind");
		keywordsList.add("overcast");
		keywordsList.add("precipitation");
		keywordsList.add("freezing");
		keywordsList.add("ice");
		keywordsList.add("convective weather");
		keywordsList.add("low ceiling");
		keywordsList.add("obscuration");
		keywordsList.add("lightning");
	}
	
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
		Page<KeywordsStatView> page = new Page<KeywordsStatView>();
		List<KeywordsStatView> list = new ArrayList<KeywordsStatView>();
		for (String keyword : keywordsList) {
			KeywordsStatView  view = new KeywordsStatView();
			Long total = eventStatViewDao.statKeywordsList(keywordsStatView, pageNo,pageSize, keyword);
			view.setKeyword(keyword);
			view.setTotal(total);
			list.add(view);
		}
		page.setCount(keywordsList.size());
		page.setList(list);
		return page;
	}

}
