package cn.edu.cauc.dao.data.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.data.INtsbNarrativesDao;
import cn.edu.cauc.model.po.data.NtsbNarratives;

@Repository("ntsbNarrativesDao")
public class NtsbNarrativesDaoimpl extends BaseDaoImpl<NtsbNarratives>
		implements INtsbNarrativesDao {

	@SuppressWarnings("unchecked")
	@Override
	public NtsbNarratives findById(String eventId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(NtsbNarratives.class);
		criteria.add(Restrictions.eq("eventId", eventId));
		List<NtsbNarratives> list = (List<NtsbNarratives>)criteria.list();
		if(list != null && !list.isEmpty()) {
			return (NtsbNarratives)criteria.list().get(0);
		} else {
			return null;
		}
	}

}
