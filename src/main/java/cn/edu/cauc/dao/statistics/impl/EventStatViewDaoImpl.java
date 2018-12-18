package cn.edu.cauc.dao.statistics.impl;

import cn.edu.cauc.model.vo.KeywordsStatView;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.statistics.IEventStatViewDao;
import cn.edu.cauc.model.vo.EventStatView;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.util.StringUtil;

@Repository("eventStatViewDao")
public class EventStatViewDaoImpl extends BaseDaoImpl<EventStatView> implements
		IEventStatViewDao {

	@Override
	public Page<EventStatView> statEventTypeList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select distinct event_type as name, count(event_type) as total ")
				.append("from ev_event_info where status='1' ");
		if (!StringUtil.isNull(eventStatView.getName())) {
			sql.append("and event_type like '%" + eventStatView.getName() + "%' ");
		}
		if(!StringUtil.isNull(eventStatView.getStartDate())) {
			sql.append("and local_date >= str_to_date('"+eventStatView.getStartDate()+"','%Y-%m-%d') ");
		}
		if(!StringUtil.isNull(eventStatView.getEndDate())) {
			sql.append("and local_date <= str_to_date('"+eventStatView.getEndDate()+"','%Y-%m-%d') ");
		}
		sql.append("group by event_type");
		return findEventStatInfoBySQL(eventStatView.getType(), sql.toString(), pageNo, pageSize);
	}

	@Override
	public Page<EventStatView> statAircraftDamageList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select distinct AIRCRAFT_DAMAGE as name, count(AIRCRAFT_DAMAGE) as total ")
				.append("from ev_event_info where status='1' ");
		if (!StringUtil.isNull(eventStatView.getName())) {
			sql.append("and AIRCRAFT_DAMAGE like '%" + eventStatView.getName() + "%' ");
		}
		if(!StringUtil.isNull(eventStatView.getStartDate())) {
			sql.append("and local_date >= str_to_date('"+eventStatView.getStartDate()+"','%Y-%m-%d') ");
		}
		if(!StringUtil.isNull(eventStatView.getEndDate())) {
			sql.append("and local_date <= str_to_date('"+eventStatView.getEndDate()+"','%Y-%m-%d') ");
		}
		sql.append("group by AIRCRAFT_DAMAGE");
		return findEventStatInfoBySQL(eventStatView.getType(), sql.toString(), pageNo, pageSize);
	}

	@Override
	public Page<EventStatView> statPhaseFlightList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select distinct PHASE_FLIGHT as name, count(PHASE_FLIGHT) as total ")
				.append("from ev_event_info where status='1' ");
		if (!StringUtil.isNull(eventStatView.getName())) {
			sql.append("and PHASE_FLIGHT like '%" + eventStatView.getName() + "%' ");
		}
		if(!StringUtil.isNull(eventStatView.getStartDate())) {
			sql.append("and local_date >= str_to_date('"+eventStatView.getStartDate()+"','%Y-%m-%d') ");
		}
		if(!StringUtil.isNull(eventStatView.getEndDate())) {
			sql.append("and local_date <= str_to_date('"+eventStatView.getEndDate()+"','%Y-%m-%d') ");
		}
		sql.append("group by PHASE_FLIGHT");
		return findEventStatInfoBySQL(eventStatView.getType(), sql.toString(), pageNo, pageSize);
	}

	@Override
	public Page<EventStatView> statAircraftModelList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select distinct AIRCRAFT_MODEL as name, count(AIRCRAFT_MODEL) as total ")
				.append("from ev_plane where 1=1 ");
		if (!StringUtil.isNull(eventStatView.getName())) {
			sql.append("and AIRCRAFT_MODEL like '%" + eventStatView.getName() + "%' ");
		}
		if(!StringUtil.isNull(eventStatView.getStartDate()) 
				|| !StringUtil.isNull(eventStatView.getEndDate())) {
			sql.append(" and exists (select 1 from ev_event_info where ev_plane.EVENT_ID = ev_event_info.ID ");
			if(!StringUtil.isNull(eventStatView.getStartDate())) {
				sql.append("and ev_event_info.local_date >= str_to_date('"+eventStatView.getStartDate()+"','%Y-%m-%d') ");
			}
			if(!StringUtil.isNull(eventStatView.getEndDate())) {
				sql.append("and ev_event_info.local_date <= str_to_date('"+eventStatView.getEndDate()+"','%Y-%m-%d') ");
			}
			sql.append(") ");
		}
		sql.append("group by AIRCRAFT_MODEL");
		return findEventStatInfoBySQL(eventStatView.getType(), sql.toString(), pageNo, pageSize);
	}

	@Override
	public Page<EventStatView> statAircraftMakeList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select distinct AIRCRAFT_MAKE as name, count(AIRCRAFT_MAKE) as total ")
				.append("from ev_plane where 1=1 ");
		if (!StringUtil.isNull(eventStatView.getName())) {
			sql.append("and AIRCRAFT_MAKE like '%" + eventStatView.getName() + "%' ");
		}
		if(!StringUtil.isNull(eventStatView.getStartDate()) 
				|| !StringUtil.isNull(eventStatView.getEndDate())) {
			sql.append(" and exists (select 1 from ev_event_info where ev_plane.EVENT_ID = ev_event_info.ID ");
			if(!StringUtil.isNull(eventStatView.getStartDate())) {
				sql.append("and ev_event_info.local_date >= str_to_date('"+eventStatView.getStartDate()+"','%Y-%m-%d') ");
			}
			if(!StringUtil.isNull(eventStatView.getEndDate())) {
				sql.append("and ev_event_info.local_date <= str_to_date('"+eventStatView.getEndDate()+"','%Y-%m-%d') ");
			}
			sql.append(") ");
		}
		sql.append("group by AIRCRAFT_MAKE");
		return findEventStatInfoBySQL(eventStatView.getType(), sql.toString(), pageNo, pageSize);
	}

	@Override
	public Page<EventStatView> statEngineManufactuerList(EventStatView eventStatView, Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select distinct ENGINE_MANUFACTER as name, count(ENGINE_MANUFACTER) as total ")
				.append("from ev_plane where 1=1 ");
		if (!StringUtil.isNull(eventStatView.getName())) {
			sql.append("and ENGINE_MANUFACTER like '%" + eventStatView.getName() + "%' ");
		}
		if(!StringUtil.isNull(eventStatView.getStartDate()) 
				|| !StringUtil.isNull(eventStatView.getEndDate())) {
			sql.append(" and exists (select 1 from ev_event_info where ev_plane.EVENT_ID = ev_event_info.ID ");
			if(!StringUtil.isNull(eventStatView.getStartDate())) {
				sql.append("and ev_event_info.local_date >= str_to_date('"+eventStatView.getStartDate()+"','%Y-%m-%d') ");
			}
			if(!StringUtil.isNull(eventStatView.getEndDate())) {
				sql.append("and ev_event_info.local_date <= str_to_date('"+eventStatView.getEndDate()+"','%Y-%m-%d') ");
			}
			sql.append(") ");
		}
		sql.append("group by ENGINE_MANUFACTER");
		return findEventStatInfoBySQL(eventStatView.getType(), sql.toString(), pageNo, pageSize);
	}

	@Override
	public Page<EventStatView> statEngineModelList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select distinct ENGINE_MODEL as name, count(ENGINE_MODEL) as total ")
				.append("from ev_plane where 1=1 ");
		if (!StringUtil.isNull(eventStatView.getName())) {
			sql.append("and ENGINE_MODEL like '%" + eventStatView.getName() + "%' ");
		}
		if(!StringUtil.isNull(eventStatView.getStartDate()) 
				|| !StringUtil.isNull(eventStatView.getEndDate())) {
			sql.append(" and exists (select 1 from ev_event_info where ev_plane.EVENT_ID = ev_event_info.ID ");
			if(!StringUtil.isNull(eventStatView.getStartDate())) {
				sql.append("and ev_event_info.local_date >= str_to_date('"+eventStatView.getStartDate()+"','%Y-%m-%d') ");
			}
			if(!StringUtil.isNull(eventStatView.getEndDate())) {
				sql.append("and ev_event_info.local_date <= str_to_date('"+eventStatView.getEndDate()+"','%Y-%m-%d') ");
			}
			sql.append(") ");
		}
		sql.append("group by ENGINE_MODEL");
		return findEventStatInfoBySQL(eventStatView.getType(), sql.toString(), pageNo, pageSize);
	}

	@Override
	public Page<EventStatView> statOperatorList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select distinct OPERATOR as name, count(OPERATOR) as total ")
				.append("from ev_plane where 1=1 ");
		if (!StringUtil.isNull(eventStatView.getName())) {
			sql.append("and OPERATOR like '%" + eventStatView.getName() + "%' ");
		}
		if(!StringUtil.isNull(eventStatView.getStartDate()) 
				|| !StringUtil.isNull(eventStatView.getEndDate())) {
			sql.append(" and exists (select 1 from ev_event_info where ev_plane.EVENT_ID = ev_event_info.ID ");
			if(!StringUtil.isNull(eventStatView.getStartDate())) {
				sql.append("and ev_event_info.local_date >= str_to_date('"+eventStatView.getStartDate()+"','%Y-%m-%d') ");
			}
			if(!StringUtil.isNull(eventStatView.getEndDate())) {
				sql.append("and ev_event_info.local_date <= str_to_date('"+eventStatView.getEndDate()+"','%Y-%m-%d') ");
			}
			sql.append(") ");
		}
		sql.append("group by OPERATOR");
		return findEventStatInfoBySQL(eventStatView.getType(), sql.toString(), pageNo, pageSize);
	}

	@Override
	public Page<EventStatView> statMaintainTypeList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select distinct MAINTAIN_TYPE as name, count(MAINTAIN_TYPE) as total ")
				.append("from ev_plane where 1=1 ");
		if (!StringUtil.isNull(eventStatView.getName())) {
			sql.append("and MAINTAIN_TYPE like '%" + eventStatView.getName() + "%' ");
		}
		if(!StringUtil.isNull(eventStatView.getStartDate()) 
				|| !StringUtil.isNull(eventStatView.getEndDate())) {
			sql.append(" and exists (select 1 from ev_event_info where ev_plane.EVENT_ID = ev_event_info.ID ");
			if(!StringUtil.isNull(eventStatView.getStartDate())) {
				sql.append("and ev_event_info.local_date >= str_to_date('"+eventStatView.getStartDate()+"','%Y-%m-%d') ");
			}
			if(!StringUtil.isNull(eventStatView.getEndDate())) {
				sql.append("and ev_event_info.local_date <= str_to_date('"+eventStatView.getEndDate()+"','%Y-%m-%d') ");
			}
			sql.append(") ");
		}
		sql.append("group by MAINTAIN_TYPE");
		return findEventStatInfoBySQL(eventStatView.getType(), sql.toString(), pageNo, pageSize);
	}

	@Override
	public Page<EventStatView> statSourceList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select distinct SOURCE as name, count(SOURCE) as total ")
				.append("from ev_pollutant where 1=1 ");
		if (!StringUtil.isNull(eventStatView.getName())) {
			sql.append("and SOURCE like '%" + eventStatView.getName() + "%' ");
		}
		if(!StringUtil.isNull(eventStatView.getStartDate()) 
				|| !StringUtil.isNull(eventStatView.getEndDate())) {
			sql.append(" and exists (select 1 from ev_event_info where ev_pollutant.EVENT_ID = ev_event_info.ID ");
			if(!StringUtil.isNull(eventStatView.getStartDate())) {
				sql.append("and ev_event_info.local_date >= str_to_date('"+eventStatView.getStartDate()+"','%Y-%m-%d') ");
			}
			if(!StringUtil.isNull(eventStatView.getEndDate())) {
				sql.append("and ev_event_info.local_date <= str_to_date('"+eventStatView.getEndDate()+"','%Y-%m-%d') ");
			}
			sql.append(") ");
		}
		sql.append("group by SOURCE");
		return findEventStatInfoBySQL(eventStatView.getType(), sql.toString(), pageNo, pageSize);
	}

	@Override
	public Page<EventStatView> statRelateToSystemList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select distinct RELATE_TO_SYSTEM as name, count(RELATE_TO_SYSTEM) as total ")
				.append("from ev_pollutant where 1=1 ");
		if (!StringUtil.isNull(eventStatView.getName())) {
			sql.append("and RELATE_TO_SYSTEM like '%" + eventStatView.getName() + "%' ");
		}
		if(!StringUtil.isNull(eventStatView.getStartDate()) 
				|| !StringUtil.isNull(eventStatView.getEndDate())) {
			sql.append(" and exists (select 1 from ev_event_info where ev_pollutant.EVENT_ID = ev_event_info.ID ");
			if(!StringUtil.isNull(eventStatView.getStartDate())) {
				sql.append("and ev_event_info.local_date >= str_to_date('"+eventStatView.getStartDate()+"','%Y-%m-%d') ");
			}
			if(!StringUtil.isNull(eventStatView.getEndDate())) {
				sql.append("and ev_event_info.local_date <= str_to_date('"+eventStatView.getEndDate()+"','%Y-%m-%d') ");
			}
			sql.append(") ");
		}
		sql.append("group by RELATE_TO_SYSTEM");
		return findEventStatInfoBySQL(eventStatView.getType(), sql.toString(), pageNo, pageSize);
	}

	@Override
	public Page<EventStatView> statBugLocationList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select distinct BUG_LOCATION as name, count(BUG_LOCATION) as total ")
				.append("from ev_pollutant where 1=1 ");
		if (!StringUtil.isNull(eventStatView.getName())) {
			sql.append("and BUG_LOCATION like '%" + eventStatView.getName() + "%' ");
		}
		if(!StringUtil.isNull(eventStatView.getStartDate()) 
				|| !StringUtil.isNull(eventStatView.getEndDate())) {
			sql.append(" and exists (select 1 from ev_event_info where ev_pollutant.EVENT_ID = ev_event_info.ID ");
			if(!StringUtil.isNull(eventStatView.getStartDate())) {
				sql.append("and ev_event_info.local_date >= str_to_date('"+eventStatView.getStartDate()+"','%Y-%m-%d') ");
			}
			if(!StringUtil.isNull(eventStatView.getEndDate())) {
				sql.append("and ev_event_info.local_date <= str_to_date('"+eventStatView.getEndDate()+"','%Y-%m-%d') ");
			}
			sql.append(") ");
		}
		sql.append("group by BUG_LOCATION");
		return findEventStatInfoBySQL(eventStatView.getType(), sql.toString(), pageNo, pageSize);
	}

	@Override
	public Page<EventStatView> statBugModelList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select distinct BUG_MODEL as name, count(BUG_MODEL) as total ")
				.append("from ev_pollutant where 1=1 ");
		if (!StringUtil.isNull(eventStatView.getName())) {
			sql.append("and BUG_MODEL like '%" + eventStatView.getName() + "%' ");
		}
		if(!StringUtil.isNull(eventStatView.getStartDate()) 
				|| !StringUtil.isNull(eventStatView.getEndDate())) {
			sql.append(" and exists (select 1 from ev_event_info where ev_pollutant.EVENT_ID = ev_event_info.ID ");
			if(!StringUtil.isNull(eventStatView.getStartDate())) {
				sql.append("and ev_event_info.local_date >= str_to_date('"+eventStatView.getStartDate()+"','%Y-%m-%d') ");
			}
			if(!StringUtil.isNull(eventStatView.getEndDate())) {
				sql.append("and ev_event_info.local_date <= str_to_date('"+eventStatView.getEndDate()+"','%Y-%m-%d') ");
			}
			sql.append(") ");
		}
		sql.append("group by BUG_MODEL");
		return findEventStatInfoBySQL(eventStatView.getType(), sql.toString(), pageNo, pageSize);
	}

	@Override
	public Page<EventStatView> statPreventList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select distinct CONTROLS as name, count(CONTROLS) as total ")
				.append("from ev_measures where 1=1 ");
		if (!StringUtil.isNull(eventStatView.getName())) {
			sql.append("and CONTROLS like '%" + eventStatView.getName() + "%' ");
		}
		if(!StringUtil.isNull(eventStatView.getStartDate()) 
				|| !StringUtil.isNull(eventStatView.getEndDate())) {
			sql.append(" and exists (select 1 from ev_event_info where ev_measures.EVENT_ID = ev_event_info.ID ");
			if(!StringUtil.isNull(eventStatView.getStartDate())) {
				sql.append("and ev_event_info.local_date >= str_to_date('"+eventStatView.getStartDate()+"','%Y-%m-%d') ");
			}
			if(!StringUtil.isNull(eventStatView.getEndDate())) {
				sql.append("and ev_event_info.local_date <= str_to_date('"+eventStatView.getEndDate()+"','%Y-%m-%d') ");
			}
			sql.append(") ");
		}
		sql.append("group by CONTROLS");
		return findEventStatInfoBySQL(eventStatView.getType(), sql.toString(), pageNo, pageSize);
	}

	@Override
	public Page<EventStatView> statMaintainList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select distinct MAINTAIN as name, count(MAINTAIN) as total ")
				.append("from ev_measures where 1=1 ");
		if (!StringUtil.isNull(eventStatView.getName())) {
			sql.append("and MAINTAIN like '%" + eventStatView.getName() + "%' ");
		}
		if(!StringUtil.isNull(eventStatView.getStartDate()) 
				|| !StringUtil.isNull(eventStatView.getEndDate())) {
			sql.append(" and exists (select 1 from ev_event_info where ev_measures.EVENT_ID = ev_event_info.ID ");
			if(!StringUtil.isNull(eventStatView.getStartDate())) {
				sql.append("and ev_event_info.local_date >= str_to_date('"+eventStatView.getStartDate()+"','%Y-%m-%d') ");
			}
			if(!StringUtil.isNull(eventStatView.getEndDate())) {
				sql.append("and ev_event_info.local_date <= str_to_date('"+eventStatView.getEndDate()+"','%Y-%m-%d') ");
			}
			sql.append(") ");
		}
		sql.append("group by MAINTAIN");
		return findEventStatInfoBySQL(eventStatView.getType(), sql.toString(), pageNo, pageSize);
	}

	@Override
	public Page<KeywordsStatView> statKeywordsList(KeywordsStatView keywordsStatView, Integer pageNo, Integer pageSize) {
//		StringBuffer sql = new StringBuffer();
		String sql = "";
		sql += "select distinct source, count(1) as total from ev_event_info where status='1' ";
		if (!StringUtil.isNull(keywordsStatView.getKeywords())) {
			String keywords = keywordsStatView.getKeywords();
			String[] keywordsArr = keywords.split(",");
			if (keywordsArr != null && keywordsArr.length > 0) {
				sql += "and (";
				for (String keyword : keywordsArr) {
					sql += "event_remarks like '%" + keyword + "%' or ";
					sql += "reason_remarks like '%" + keyword + "%' or ";
				}
				sql = sql.substring(0, sql.length()-3);
				sql+= ") ";
			}
		}
		if (!StringUtil.isNull(keywordsStatView.getStartDate())) {
			sql += " and local_date >= str_to_date('"+keywordsStatView.getStartDate()+"','%Y-%m-%d')";
		}
		if (!StringUtil.isNull(keywordsStatView.getEndDate())) {
			sql += " and local_date < str_to_date('"+keywordsStatView.getEndDate()+"','%Y-%m-%d') ";
		}
		if (!StringUtil.isNull(keywordsStatView.getPhaseFlight())) {
			sql += " and phase_flight like '%"+keywordsStatView.getPhaseFlight()+"%'";
		}
		sql += "group by source";
		return findEventPagerByKeywords(sql,pageNo,  pageSize);
	}

}
