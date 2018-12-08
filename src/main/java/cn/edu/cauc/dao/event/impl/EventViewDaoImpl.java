package cn.edu.cauc.dao.event.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.event.IEventViewDao;
import cn.edu.cauc.model.po.event.EventInfo;
import cn.edu.cauc.model.po.event.EventView;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.util.DateUtil;
import cn.edu.cauc.util.StringUtil;

@Repository("eventViewDao")
public class EventViewDaoImpl extends BaseDaoImpl<EventView> implements
		IEventViewDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<EventView> findEventViewList(EventView event) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(EventView.class);
		
		if(event != null) {
			hqlConditions(event, criteria);
		}
		List<EventView> eventViewList = criteria.list();
		return eventViewList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<EventView> findEventViewPageList(EventView event, int pageNo,
			Integer pageSize) {
		Page<EventView> page = new Page<EventView>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(EventView.class);
		if(event != null) {
			hqlConditions(event, criteria);
		}
		int count = criteria.list().size();
		List<EventView> list = criteria.setFirstResult(pageNo).setMaxResults(pageSize).list();
		page.setCount(count);
		page.setList(list);
		return page;
	}
	
	private void hqlConditions(EventView event, Criteria criteria) {
		if(!StringUtil.isNull(event.getStatus())) {
			criteria.add(Restrictions.eq("status", event.getStatus()));
		}
		if(!StringUtil.isNull(event.getSource())) {
			String[] sourceArr = event.getSource().split(",");
			criteria.add(Restrictions.in("source", sourceArr));
		}
		if(!StringUtil.isNull(event.getSourceId())) {
			criteria.add(Restrictions.eq("sourceId", event.getSourceId()));
		}
		if(!StringUtil.isNull(event.getEventType())) {
			criteria.add(Restrictions.eq("eventType", event.getEventType()));
		}
		if(!StringUtil.isNull(event.getLocalDateStart())) {
			Date startDate = DateUtil.fomatDate(event.getLocalDateStart());
			criteria.add(Restrictions.ge("localDate", startDate));
		}
		if(!StringUtil.isNull(event.getLocalDateEnd())) {
			Date endDate = DateUtil.fomatDate(event.getLocalDateEnd());
			criteria.add(Restrictions.le("localDate", endDate));
		}
		if(!StringUtil.isNull(event.getPhaseFlight())) {
			criteria.add(Restrictions.like("phaseFlight", "%"+event.getPhaseFlight()+"%"));
		}
		//故障模式
		if(!StringUtil.isNull(event.getBugModel())) {
			criteria.add(Restrictions.like("bugModel", "%"+event.getBugModel()+"%"));
			//sql += "and p.BUG_MODEL like '%" + event.getBugModel() + "%' ";
		}
		if(!StringUtil.isNull(event.getEventRemarks())) {
			criteria.add(Restrictions.like("eventRemarks", "%"+event.getEventRemarks()+"%"));
			//sql += "and e.EVENT_REMARKS like '%" + event.getEventRemarks() + "%' ";
		}
		if(!StringUtil.isNull(event.getReasonRemarks())) {
			criteria.add(Restrictions.like("reasonRemarks", "%"+event.getReasonRemarks()+"%"));
			//sql += "and e.REASON_REMARKS like '%" + event.getReasonRemarks() + "%' ";
		}
		if(!StringUtil.isNull(event.getPrevent())) {
			criteria.add(Restrictions.like("prevent", "%"+event.getPrevent()+"%"));
			//sql += "and m.PREVENT like '%" + event.getPrevent() + "%' ";
		}
		if(!StringUtil.isNull(event.getControls())) {
			criteria.add(Restrictions.like("controls", "%"+event.getControls()+"%"));
			//sql += "and m.CONTROLS like '%" + event.getControls() + "%' ";
		}
		if(!StringUtil.isNull(event.getMaintain())) {
			criteria.add(Restrictions.like("maintain", "%"+event.getControls()+"%"));
			//sql += "and m.MAINTAIN like '%" + event.getMaintain() + "%' ";
		}
		if(!StringUtil.isNull(event.getRelateToSystem())) {
			criteria.add(Restrictions.like("maintain", "%"+event.getRelateToSystem()+"%"));
			//sql += "and p.RELATE_TO_SYSTEM like '" + event.getRelateToSystem() + "%' ";
		}
		//飞机制造商
		if(!StringUtil.isNull(event.getAircraftMake())) {
			criteria.add(Restrictions.like("aircraftMake", "%"+event.getAircraftMake()+"%"));
		}
		//飞机型号
		if(!StringUtil.isNull(event.getAircraftModel())) {
			criteria.add(Restrictions.like("aircraftModel", "%"+event.getAircraftModel()+"%"));
		}
		//损伤程度
		if(!StringUtil.isNull(event.getAircraftDamage())) {
			criteria.add(Restrictions.like("aircraftDamage", "%"+event.getAircraftDamage()+"%"));
		}
		//发动机型号
		if(!StringUtil.isNull(event.getEngineModel())) {
			criteria.add(Restrictions.like("engineModel", "%"+event.getEngineModel()+"%"));
		}
		//发动机数量
		if(!StringUtil.isNull(event.getNumberOfEngines())) {
			criteria.add(Restrictions.like("numberOfEngines", "%"+event.getNumberOfEngines()+"%"));
		}
		//运营商
		if(!StringUtil.isNull(event.getOperator())) {
			criteria.add(Restrictions.like("operator", "%"+event.getOperator()+"%"));
		}
		//污染物来源
		if(!StringUtil.isNull(event.getPullutantSource())) {
			criteria.add(Restrictions.like("pullutantSource", "%"+event.getPullutantSource()+"%"));
		}
		//备注
		if(!StringUtil.isNull(event.getRemark())) {
			criteria.add(Restrictions.like("remark", "%"+event.getRemark()+"%"));
		}
		//涉及系统
		if(!StringUtil.isNull(event.getRelateToSystem())) {
			criteria.add(Restrictions.like("relateToSystem", event.getRelateToSystem()+"%"));
		}
	}
	
}
