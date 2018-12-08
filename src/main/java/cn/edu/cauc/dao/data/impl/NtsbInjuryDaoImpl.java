package cn.edu.cauc.dao.data.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.data.INtsbInjuryDao;
import cn.edu.cauc.model.po.data.NtsbInjury;

@Repository("ntsbInjuryDao")
public class NtsbInjuryDaoImpl extends BaseDaoImpl<NtsbInjury> implements
		INtsbInjuryDao {
	
	private final String[] CREW = {"CABN", "CPLT", "DSTU", "FENG", "FLTI", "KPLT", "OCRW", "PLT"}; //机组条件
	
	private final String PASSENGER = "PAX";//乘客条件
	
	private final String FATL = "FATL";//死亡
	
	private final String SERIOUS = "SERS";//重伤
	
	private final String[] MINR = {"MINR", "NONE"};//轻伤、未受伤

	@Override
	public int findCrewFatals(String eventId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(NtsbInjury.class);
		criteria.add(Restrictions.eq("eventId", eventId));
		criteria.add(Restrictions.in("injPersonCategory", CREW));
		criteria.add(Restrictions.eq("level", FATL));
		criteria.setProjection(Projections.sum("injPersonCount"));
		Long sum = (Long)criteria.uniqueResult();
		if(sum != null) {
			return sum.intValue();
		} else {
			return 0;
		}
	}

	@Override
	public int findCrewSerious(String eventId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(NtsbInjury.class);
		criteria.add(Restrictions.eq("eventId", eventId));
		criteria.add(Restrictions.in("injPersonCategory", CREW));
		criteria.add(Restrictions.eq("level", SERIOUS));
		criteria.setProjection(Projections.sum("injPersonCount"));
		Long sum = (Long)criteria.uniqueResult();
		if(sum != null) {
			return sum.intValue();
		} else {
			return 0;
		}
	}

	@Override
	public int findCrewMinors(String eventId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(NtsbInjury.class);
		criteria.add(Restrictions.eq("eventId", eventId));
		criteria.add(Restrictions.in("injPersonCategory", CREW));
		criteria.add(Restrictions.in("level", MINR));
		criteria.setProjection(Projections.sum("injPersonCount"));
		Long sum = (Long)criteria.uniqueResult();
		if(sum != null) {
			return sum.intValue();
		} else {
			return 0;
		}
	}

	@Override
	public int findPassengerFatals(String eventId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(NtsbInjury.class);
		criteria.add(Restrictions.eq("eventId", eventId));
		criteria.add(Restrictions.eq("injPersonCategory", PASSENGER));
		criteria.add(Restrictions.eq("level", FATL));
		criteria.setProjection(Projections.sum("injPersonCount"));
		Long sum = (Long)criteria.uniqueResult();
		if(sum != null) {
			return sum.intValue();
		} else {
			return 0;
		}
	}

	@Override
	public int findPassengerSerious(String eventId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(NtsbInjury.class);
		criteria.add(Restrictions.eq("eventId", eventId));
		criteria.add(Restrictions.eq("injPersonCategory", PASSENGER));
		criteria.add(Restrictions.eq("level", SERIOUS));
		criteria.setProjection(Projections.sum("injPersonCount"));
		Long sum = (Long)criteria.uniqueResult();
		if(sum != null) {
			return sum.intValue();
		} else {
			return 0;
		}
	}

	@Override
	public int findPassengerMinors(String eventId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(NtsbInjury.class);
		criteria.add(Restrictions.eq("eventId", eventId));
		criteria.add(Restrictions.eq("injPersonCategory", PASSENGER));
		criteria.add(Restrictions.in("level", MINR));
		criteria.setProjection(Projections.sum("injPersonCount"));
		Long sum = (Long)criteria.uniqueResult();
		if(sum != null) {
			return sum.intValue();
		} else {
			return 0;
		}
	}
	
}
