package cn.edu.cauc.dao.data.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.data.IAIDSRawDataDao;
import cn.edu.cauc.model.po.data.AidsRawData;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.util.Constant;
import cn.edu.cauc.util.StringUtil;

/**
 * 作者： 徐楠
 *
 * 描述：<p>原始数据处理 DAO 实现类</p>
 * 创建时间：2016年10月2日
 */
@Repository("aidsRawDataDao")
public class AIDSRawDataDaoImpl extends BaseDaoImpl<AidsRawData> implements
		IAIDSRawDataDao {
	
	private static final Logger logger = Logger.getLogger(AIDSRawDataDaoImpl.class);

	/**
	 * 批量数据入库
	 * 
	 * @param aidsRawDataList
	 */
	public void batchInsert(List<AidsRawData> aidsRawDataList) {
		if(aidsRawDataList != null && !aidsRawDataList.isEmpty()) {
			for(AidsRawData aidsRawData : aidsRawDataList) {
				//每条数据入库记录错误日志
				try {
					getSession().save(aidsRawData);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("reportNumber = " + aidsRawData.getReportNumber() + " 数据入库失败！原因：" + e.getMessage());
					continue;
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<AidsRawData> findRawList(AidsRawData aidsRawData,
			Integer pageNo, Integer pageSize) {
		Page<AidsRawData> page = new Page<AidsRawData>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(AidsRawData.class);
		if(aidsRawData != null) {
			if(!StringUtil.isNull(aidsRawData.getReportNumber())) {
				criteria.add(Restrictions.like("reportNumber", "%"+aidsRawData.getReportNumber()+"%"));
			}
			if(!StringUtil.isNull(aidsRawData.getReady())) {
				criteria.add(Restrictions.eq("ready", aidsRawData.getReady()));
			}
		}
		int count = criteria.list().size();
		List<AidsRawData> rawList = (List<AidsRawData>)criteria.setFirstResult(pageNo).setMaxResults(pageSize).list();
		page.setCount(count);
		page.setList(rawList);
		return page;
	}

	@Override
	public AidsRawData findRawDataByReportNumber(String reportNumber) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(AidsRawData.class);
		criteria.add(Restrictions.eq("reportNumber", reportNumber));
		AidsRawData aidsRawData = (AidsRawData)criteria.uniqueResult();
		return aidsRawData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AidsRawData> findReadyRawsDatas() {
		List<AidsRawData> rawDataList = new ArrayList<AidsRawData>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(AidsRawData.class);
		//criteria.add(Restrictions.eq("status", Constant.STATUS_READY));
		criteria.add(Restrictions.eq("ready", Constant.READY_SPIDER));//爬取完成
		rawDataList = criteria.list();
		return rawDataList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AidsRawData> findImportedRawsData() {
		List<AidsRawData> rawDataList = new ArrayList<AidsRawData>();
		Session session = getSession();
		Criteria criteria = session.createCriteria(AidsRawData.class);
		//criteria.add(Restrictions.eq("status", Constant.STATUS_READY));
		criteria.add(Restrictions.eq("ready", Constant.READY_IMPORTED));//导入完成
		rawDataList = criteria.list();
		return rawDataList;
	}

	@Override
	public void modify(AidsRawData aidsRawData) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			session.beginTransaction();
			session.update(aidsRawData);
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
