package cn.edu.cauc.dao.event.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.event.IApproveInfoDao;
import cn.edu.cauc.model.po.event.ApproveInfo;
import cn.edu.cauc.model.vo.Page;

@Repository("approveInfoDao")
public class ApproveInfoDaoImpl extends BaseDaoImpl<ApproveInfo> implements
		IApproveInfoDao {

	@SuppressWarnings("unchecked")
	@Override
	public Page<ApproveInfo> findApproveList(ApproveInfo approveInfo,
			int pageNo, Integer pageSize) {
		Page<ApproveInfo> page = new Page<ApproveInfo>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(ApproveInfo.class);
		if(approveInfo != null) {
			
		}
		int count = criteria.list().size();
		List<ApproveInfo> approveList = (List<ApproveInfo>)criteria.setFirstResult(pageNo).setMaxResults(pageSize).list();
		page.setCount(count);
		page.setList(approveList);
		return page;
	}

}
