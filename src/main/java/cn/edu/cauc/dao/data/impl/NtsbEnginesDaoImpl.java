package cn.edu.cauc.dao.data.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.data.INtsbEnginesDao;
import cn.edu.cauc.model.po.data.NtsbEngines;

@Repository("ntsbEnginesDao")
public class NtsbEnginesDaoImpl extends BaseDaoImpl<NtsbEngines> implements
		INtsbEnginesDao {

	@SuppressWarnings("unchecked")
	@Override
	public NtsbEngines findById(String eventId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(NtsbEngines.class);
		criteria.add(Restrictions.eq("eventId", eventId));
		List<NtsbEngines> list = (List<NtsbEngines>)criteria.list();
		if(list != null && !list.isEmpty()) {
			return (NtsbEngines)criteria.list().get(0);
		} else {
			return null;
		}
	}

}
