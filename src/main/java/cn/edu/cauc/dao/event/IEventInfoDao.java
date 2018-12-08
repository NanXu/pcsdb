package cn.edu.cauc.dao.event;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.event.EventInfo;
import cn.edu.cauc.model.vo.Page;

/**
 * 作者： 徐楠
 *
 * 描述：<p>事件基本信息 DAO</p>
 * 创建时间：2016年10月6日
 */
public interface IEventInfoDao extends IBaseDao<EventInfo> {

	/**
	 * 查询最大的ID
	 * 
	 * @param id
	 * @return
	 */
	public String findDistinctMaxID(String id);

	/**
	 * 分页查询
	 * 
	 * @param event
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventInfo> findEventList(EventInfo event, int pageNo,
			Integer pageSize);

	public Page<EventInfo> findDraftEventList(EventInfo event, int pageNo,
			Integer pageSize);
	
	/**
	 * 添加事件基本信息(单独事务)
	 * 
	 * @param eventInfo
	 */
	public void add(EventInfo eventInfo);
}
