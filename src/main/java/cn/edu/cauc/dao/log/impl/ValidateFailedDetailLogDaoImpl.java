package cn.edu.cauc.dao.log.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.log.IValidateFailedDetailLogDao;
import cn.edu.cauc.model.po.log.ValidateFailedDetailLog;

/**
 * 作者： 徐楠
 *
 * 描述：<p>TODO</p>
 * 创建时间：2016年10月4日
 */
@Repository("validateFailedDetailLogDao")
public class ValidateFailedDetailLogDaoImpl extends BaseDaoImpl<ValidateFailedDetailLog> implements
		IValidateFailedDetailLogDao {

	@Override
	public void add(ValidateFailedDetailLog logDetail) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			session.beginTransaction();
			session.save(logDetail);
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
