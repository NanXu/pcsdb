package cn.edu.cauc.dao.data.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.data.INtsbAircraftDao;
import cn.edu.cauc.model.po.data.NtsbAircraft;

@Repository("ntsbAircraftDao")
public class NtsbAircraftDaoImpl extends BaseDaoImpl<NtsbAircraft> implements
		INtsbAircraftDao {

	@SuppressWarnings("unchecked")
	@Override
	public NtsbAircraft findById(String eventId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(NtsbAircraft.class);
		criteria.add(Restrictions.eq("eventId", eventId));
		List<NtsbAircraft> list = (List<NtsbAircraft>)criteria.list();
		if(list != null && !list.isEmpty()) {
			return (NtsbAircraft)criteria.list().get(0);
		} else {
			return null;
		}
	}

	
}
