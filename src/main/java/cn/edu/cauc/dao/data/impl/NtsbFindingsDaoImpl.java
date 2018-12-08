package cn.edu.cauc.dao.data.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.data.INtsbFindingsDao;
import cn.edu.cauc.model.po.data.NtsbFindings;

@Repository("ntsbFindingsDao")
public class NtsbFindingsDaoImpl extends BaseDaoImpl<NtsbFindings> implements
		INtsbFindingsDao {

	@SuppressWarnings("unchecked")
	@Override
	public NtsbFindings findById(String eventId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(NtsbFindings.class);
		criteria.add(Restrictions.eq("eventId", eventId));
		List<NtsbFindings> list = (List<NtsbFindings>)criteria.list();
		if(list != null && !list.isEmpty()) {
			return (NtsbFindings)criteria.list().get(0);
		} else {
			return null;
		}
	}

}
