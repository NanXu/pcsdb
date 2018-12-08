package cn.edu.cauc.dao.event;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.event.Casualties;

/**
 * 作者： 徐楠
 *
 * 描述：<p>受伤情况</p>
 * 创建时间：2016年10月7日
 */
public interface ICasualtiesDao extends IBaseDao<Casualties> {

	/**
	 * 通过事故ID查询伤亡信息
	 * 
	 * @param eventId
	 * @return
	 */
	public Casualties findCasualtiesByEventId(Integer eventId);
	
	public void add(Casualties casualties);

}
