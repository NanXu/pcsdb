package cn.edu.cauc.dao.log.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.log.IValidateLogDao;
import cn.edu.cauc.model.po.log.ValidateLog;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.util.StringUtil;

/**
 * 作者： 徐楠
 *
 * 描述：<p>TODO</p>
 * 创建时间：2016年10月4日
 */
@Repository("validateLogDao")
public class ValidateLogDaoImpl extends BaseDaoImpl<ValidateLog> implements
		IValidateLogDao {

	@SuppressWarnings("unchecked")
	@Override
	public Page<ValidateLog> findValidateLogList(ValidateLog validateLog,
			Integer pageNo, Integer pageSize) {
		Page<ValidateLog> page = new Page<ValidateLog>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(ValidateLog.class);
		if(validateLog != null) {
			if(!StringUtil.isNull(validateLog.getDataID())) {
				criteria.add(Restrictions.like("dataID", "%"+validateLog.getDataID()+"%"));
			}
			if(!StringUtil.isNull(validateLog.getStatus())) {
				criteria.add(Restrictions.eq("status", validateLog.getStatus()));
			}
		}
		criteria.addOrder(Order.desc("cteateTime"));
		int count = criteria.list().size();
		List<ValidateLog> validateLogList = (List<ValidateLog>)criteria.setFirstResult(pageNo).setMaxResults(pageSize).list();
		page.setCount(count);
		page.setList(validateLogList);
		return page;
	}

	@Override
	public void add(ValidateLog log) {
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

	@Override
	public void modify(ValidateLog log) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			session.beginTransaction();
			session.update(log);
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
