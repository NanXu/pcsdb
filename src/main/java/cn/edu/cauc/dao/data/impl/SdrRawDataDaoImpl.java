package cn.edu.cauc.dao.data.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.data.ISdrRawDataDao;
import cn.edu.cauc.model.po.data.SdrRawData;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.util.StringUtil;

@Repository("sdrRawDataDao")
public class SdrRawDataDaoImpl extends BaseDaoImpl<SdrRawData> implements
		ISdrRawDataDao {
	
	private final String PENDING_STORAGE = "0";//待入库

	@SuppressWarnings("unchecked")
	@Override
	public Page<SdrRawData> findRawList(SdrRawData sdrRawData, int pageNo,
			Integer pageSize) {
		Page<SdrRawData> page = new Page<SdrRawData>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(SdrRawData.class);
		if(sdrRawData != null) {
			if(!StringUtil.isNull(sdrRawData.getSeqNumber())) {
				criteria.add(Restrictions.like("seqNumber", "%"+sdrRawData.getSeqNumber()+"%"));
			}
			if(!StringUtil.isNull(sdrRawData.getAtaCode())) {
				criteria.add(Restrictions.like("ataCode", "%"+sdrRawData.getAtaCode()+"%"));
			}
		}
		int count = criteria.list().size();
		List<SdrRawData> rawList = (List<SdrRawData>)criteria.setFirstResult(pageNo).setMaxResults(pageSize).list();
		page.setCount(count);
		page.setList(rawList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SdrRawData> findPendingStorageList() {
		List<SdrRawData> rawDataList = new ArrayList<SdrRawData>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(SdrRawData.class);
		criteria.add(Restrictions.eq("status", PENDING_STORAGE));
		rawDataList = criteria.list();
		return rawDataList;
	}

	@Override
	public void modify(SdrRawData sdrRawData) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			session.beginTransaction();
			session.update(sdrRawData);
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
	public void sdrTxtInsert(String table,String[] arrTxt) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			session.beginTransaction();
			String sql = "insert into sdr_source_"+table+" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
					"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
					"?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Query query = session.createSQLQuery(sql);
			for (int i = 0; i< arrTxt.length; i++ ) {
				query.setParameter(i, arrTxt[i]);
			}
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			tx = null;
			if(session!=null){
				session.close();
			}
		}
	}

}
