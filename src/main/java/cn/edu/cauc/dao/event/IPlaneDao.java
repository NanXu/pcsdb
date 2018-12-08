package cn.edu.cauc.dao.event;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.event.Plane;

/**
 * 作者： 徐楠
 *
 * 描述：<p>飞机信息 DAO</p>
 * 创建时间：2016年10月7日
 */
public interface IPlaneDao extends IBaseDao<Plane> {

	/**
	 * 通过事件ID查询飞机信息
	 * 
	 * @param id
	 * @return
	 */
	public Plane findPlaneByEventId(Integer eventId);
	
	/**
	 * 添加飞机信息（单独事务）
	 * 
	 * @param plane
	 */
	public void add(Plane plane);

}
