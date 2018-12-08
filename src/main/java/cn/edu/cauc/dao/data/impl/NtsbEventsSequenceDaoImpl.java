package cn.edu.cauc.dao.data.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.data.INtsbEventsSequenceDao;
import cn.edu.cauc.model.po.data.NtsbEventsSequence;

@Repository("ntsbEventsSequenceDao")
public class NtsbEventsSequenceDaoImpl extends BaseDaoImpl<NtsbEventsSequence>
		implements INtsbEventsSequenceDao {

	@SuppressWarnings("unchecked")
	@Override
	public NtsbEventsSequence findById(String eventId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(NtsbEventsSequence.class);
		criteria.add(Restrictions.eq("eventId", eventId));
		List<NtsbEventsSequence> list = (List<NtsbEventsSequence>)criteria.list();
		if(list != null && !list.isEmpty()) {
			return (NtsbEventsSequence)criteria.list().get(0);
		} else {
			return null;
		}
	}

}
