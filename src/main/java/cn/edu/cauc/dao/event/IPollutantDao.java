package cn.edu.cauc.dao.event;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.event.Pollutant;

public interface IPollutantDao extends IBaseDao<Pollutant> {

	public Pollutant findPollutantByEventId(Integer eventId);

	/**
	 * 添加污染物信息(单独事务)
	 * 
	 * @param pollutant
	 */
	public void add(Pollutant pollutant);
}
