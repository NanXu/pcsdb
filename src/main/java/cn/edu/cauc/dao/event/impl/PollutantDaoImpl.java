package cn.edu.cauc.dao.event.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.event.IPollutantDao;
import cn.edu.cauc.model.po.event.Pollutant;

@Repository("pollutantDao")
public class PollutantDaoImpl extends BaseDaoImpl<Pollutant> implements
		IPollutantDao {

	@Override
	public Pollutant findPollutantByEventId(Integer eventId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Pollutant.class);
		criteria.add(Restrictions.eq("eventId", eventId));
		return (Pollutant)criteria.uniqueResult();
	}

	@Override
	public void add(Pollutant pollutant) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			session.beginTransaction();
			session.save(pollutant);
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
