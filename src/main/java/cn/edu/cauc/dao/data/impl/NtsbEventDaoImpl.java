package cn.edu.cauc.dao.data.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.data.INtsbEventDao;
import cn.edu.cauc.model.po.data.NtsbEvent;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.util.StringUtil;

@Repository("ntsbEventDao")
public class NtsbEventDaoImpl extends BaseDaoImpl<NtsbEvent> implements
		INtsbEventDao {
	
	private final String PENDING_STORAGE = "0";//待入库

	@SuppressWarnings("unchecked")
	@Override
	public Page<NtsbEvent> findRawList(NtsbEvent ntsbEvent, int pageNo,
			Integer pageSize) {
		Page<NtsbEvent> page = new Page<NtsbEvent>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(NtsbEvent.class);
		if(ntsbEvent != null) {
			if(!StringUtil.isNull(ntsbEvent.getEventId())) {
				criteria.add(Restrictions.like("eventId", "%"+ntsbEvent.getEventId()+"%"));
			}
			if(!StringUtil.isNull(ntsbEvent.getEventType())) {
				criteria.add(Restrictions.like("eventType", "%"+ntsbEvent.getEventType()+"%"));
			}
		}
		int count = criteria.list().size();
		List<NtsbEvent> rawList = (List<NtsbEvent>)criteria.setFirstResult(pageNo).setMaxResults(pageSize).list();
		page.setCount(count);
		page.setList(rawList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NtsbEvent> findPendingStorageList() {
		List<NtsbEvent> ntsbEventList = new ArrayList<NtsbEvent>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(NtsbEvent.class);
		criteria.add(Restrictions.eq("status", PENDING_STORAGE));
		ntsbEventList = criteria.list();
		return ntsbEventList;
	}

	@Override
	public void modify(NtsbEvent ntsbEvent) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			session.beginTransaction();
			session.update(ntsbEvent);
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
