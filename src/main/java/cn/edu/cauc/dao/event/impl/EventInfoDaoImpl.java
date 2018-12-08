package cn.edu.cauc.dao.event.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.event.IEventInfoDao;
import cn.edu.cauc.model.po.event.EventInfo;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.util.Constant;
import cn.edu.cauc.util.StringUtil;

@Repository("eventInfoDao")
public class EventInfoDaoImpl extends BaseDaoImpl<EventInfo> implements
		IEventInfoDao {

	@Override
	public String findDistinctMaxID(String id) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(EventInfo.class);
		criteria.setProjection(Projections.max("id"));
		criteria.add(Restrictions.like("id", id+"%"));
		return (String)criteria.uniqueResult();
	}

	@Override
	public Page<EventInfo> findEventList(EventInfo event, int pageNo,
			Integer pageSize) {
		Page<EventInfo> page = new Page<EventInfo>();
		String sql = "SELECT\n" +
					"	e.ID,\n" +
					"	e.SOURCE,\n" +
					"	e.SOURCE_ID,\n" +
					"	e.EVENT_TYPE,\n" +
					"	e.LOCAL_DATE,\n" +
					"	e.LOCAL_TIME,\n" +
					"	e.LAST_DEPARTURE_POINT,\n" +
					"	e.DESTINATION_LOCAL,\n" +
					"	e.EVENT_LOCATION,\n" +
					"	e.WEATHER_CONDITIONS,\n" +
					"	e.AIRCRAFT_DAMAGE,\n" +
					"	e.PHASE_FLIGHT,\n" +
					"	e.EVENT_REMARKS,\n" +
					"	e.REASON_REMARKS,\n" +
					"	e.APPROVE_USER,\n" +
					"	e.APPROVE_TIME,\n" +
					"	e.DRAFT_USER,\n" +
					"	e.DRAFT_TIME,\n" +
					"	e.`STATUS`,\n" +
					"	p.RELATE_TO_SYSTEM,\n" +
					"	p.BUG_MODEL,\n" +
					"	m.PREVENT,\n" +
					"	m.CONTROLS,\n" +
					"	m.MAINTAIN\n" +
					"FROM\n" +
					"	ev_event_info e\n" +
					"LEFT JOIN ev_pollutant p ON e.ID = p.EVENT_ID\n" +
					"LEFT JOIN ev_measures m ON e.ID = m.EVENT_ID where 1 = 1 ";
		if(event != null) {
			if(!StringUtil.isNull(event.getStatus())) {
				sql += "and e.`STATUS` = '" + event.getStatus() + "' ";
			}
			if(!StringUtil.isNull(event.getSource())) {
				String strSource = event.getSource().replace(",", "','");
				sql += "and e.SOURCE IN('" + strSource + "') ";
			}
			if(!StringUtil.isNull(event.getSourceId())) {
				sql += "and e.SOURCE_ID like '%" + event.getSourceId() + "%' ";
			}
			if(!StringUtil.isNull(event.getDraftUser())) {
				sql += "and e.DRAFT_USER = '" + event.getDraftUser() + "' ";
			}
			if(!StringUtil.isNull(event.getEventType())) {
				sql += "and e.EVENT_TYPE = '" + event.getEventType() + "' ";
			}
			if(!StringUtil.isNull(event.getLocalDateStart())) {
				sql += "and e.LOCAL_DATE >= STR_TO_DATE('"+ event.getLocalDateStart() +"','%Y-%m-%d') ";
			}
			if(!StringUtil.isNull(event.getLocalDateEnd())) {
				sql += "and e.LOCAL_DATE <= STR_TO_DATE('"+ event.getLocalDateEnd() +"','%Y-%m-%d') ";
			}
			if(!StringUtil.isNull(event.getPhaseFlight())) {
				sql += "and e.PHASE_FLIGHT like '%" + event.getPhaseFlight().toUpperCase() + "%' ";
			}
			//故障模式
			if(!StringUtil.isNull(event.getBugModel())) {
				sql += "and p.BUG_MODEL like '%" + event.getBugModel() + "%' ";
			}
			if(!StringUtil.isNull(event.getEventRemarks())) {
				sql += "and e.EVENT_REMARKS like '%" + event.getEventRemarks() + "%' ";
			}
			if(!StringUtil.isNull(event.getReasonRemarks())) {
				sql += "and e.REASON_REMARKS like '%" + event.getReasonRemarks() + "%' ";
			}
			if(!StringUtil.isNull(event.getPrevent())) {
				sql += "and m.PREVENT like '%" + event.getPrevent() + "%' ";
			}
			if(!StringUtil.isNull(event.getControls())) {
				sql += "and m.CONTROLS like '%" + event.getControls() + "%' ";
			}
			if(!StringUtil.isNull(event.getMaintain())) {
				sql += "and m.MAINTAIN like '%" + event.getMaintain() + "%' ";
			}
			if(!StringUtil.isNull(event.getRelateToSystem())) {
				sql += "and p.RELATE_TO_SYSTEM like '" + event.getRelateToSystem() + "%' ";
			}
		}
		page = findEventPagerListBySQL(sql, pageNo, pageSize);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public Page<EventInfo> findDraftEventList(EventInfo event, int pageNo,
			Integer pageSize) {
		Page<EventInfo> page = new Page<EventInfo>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(EventInfo.class);
		if(event != null) {
			if(!StringUtil.isNull(event.getStatus())) {
				criteria.add(Restrictions.eq("status", event.getStatus()));
			}
			if(!StringUtil.isNull(event.getSource())) {
				criteria.add(Restrictions.eq("source", event.getSource()));
			}
			if(!StringUtil.isNull(event.getSourceId())) {
				criteria.add(Restrictions.like("sourceId", "%"+event.getSourceId()+"%"));
			}
			if(!StringUtil.isNull(event.getDraftUser())) {
				criteria.add(Restrictions.eq("draftUser", event.getDraftUser()));
			}
		}
		criteria.add(Restrictions.ne("status", Constant.EVENT_PUBLISH));
		int count = criteria.list().size();
		List<EventInfo> eventList = (List<EventInfo>)criteria.setFirstResult(pageNo).setMaxResults(pageSize).list();
		page.setCount(count);
		page.setList(eventList);
		return page;
	}

	@Override
	public void add(EventInfo eventInfo) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			session.beginTransaction();
			session.save(eventInfo);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			 if(session!=null){  
	              session.close();  
	          }  
		}
	}

}
