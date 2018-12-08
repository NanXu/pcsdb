package cn.edu.cauc.dao.event.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.event.ICasualtiesDao;
import cn.edu.cauc.model.po.event.Casualties;

/**
 * 作者： 徐楠
 *
 * 描述：<p>伤亡情况 DAO 实现</p>
 * 创建时间：2016年10月7日
 */
@Repository("casualtiesDao")
public class CasualtiesDaoImpl extends BaseDaoImpl<Casualties> implements
		ICasualtiesDao {

	@Override
	public Casualties findCasualtiesByEventId(Integer eventId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Casualties.class);
		criteria.add(Restrictions.eq("eventId", eventId));
		return (Casualties)criteria.uniqueResult();
	}
	
	@Override
	public void add(Casualties casualties) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			session.beginTransaction();
			session.save(casualties);
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
