package cn.edu.cauc.dao.event.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.event.IMeasuresDao;
import cn.edu.cauc.model.po.event.Measures;

@Repository("measuresDao")
public class MeasuresDaoImpl extends BaseDaoImpl<Measures> implements
		IMeasuresDao {

	@Override
	public Measures findMeasuresByEventId(Integer eventId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Measures.class);
		criteria.add(Restrictions.eq("eventId", eventId));
		return (Measures)criteria.uniqueResult();
	}

	@Override
	public void add(Measures measures) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			session.beginTransaction();
			session.save(measures);
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
