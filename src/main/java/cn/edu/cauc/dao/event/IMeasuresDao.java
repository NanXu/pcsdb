package cn.edu.cauc.dao.event;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.event.Measures;

public interface IMeasuresDao extends IBaseDao<Measures> {

	/**
	 * 通过事件ID查询采取措施信息
	 * 
	 * @param eventId
	 * @return
	 */
	public Measures findMeasuresByEventId(Integer eventId);

	/**
	 * 
	 * @param measures
	 */
	public void add(Measures measures);
}
