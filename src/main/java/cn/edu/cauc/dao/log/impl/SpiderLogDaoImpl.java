package cn.edu.cauc.dao.log.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.log.ISpiderLogDao;
import cn.edu.cauc.model.po.log.SpiderLog;

@Repository("spiderLogDao")
public class SpiderLogDaoImpl extends BaseDaoImpl<SpiderLog> implements
		ISpiderLogDao {

	@Override
	public void save(SpiderLog log) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			session.beginTransaction();
			session.save(log);
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
