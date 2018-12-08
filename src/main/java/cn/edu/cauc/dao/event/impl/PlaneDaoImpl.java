package cn.edu.cauc.dao.event.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.event.IPlaneDao;
import cn.edu.cauc.model.po.event.Plane;

/**
 * 作者： 徐楠
 *
 * 描述：<p>飞机信息 DAO 实现</p>
 * 创建时间：2016年10月7日
 */
@Repository("planeDao")
public class PlaneDaoImpl extends BaseDaoImpl<Plane> implements IPlaneDao {

	@Override
	public Plane findPlaneByEventId(Integer eventId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Plane.class);
		criteria.add(Restrictions.eq("eventId", eventId));
		return (Plane)criteria.uniqueResult();
	}

	@Override
	public void add(Plane plane) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			session.beginTransaction();
			session.save(plane);
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
