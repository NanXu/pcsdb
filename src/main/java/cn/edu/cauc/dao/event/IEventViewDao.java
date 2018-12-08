package cn.edu.cauc.dao.event;

import java.util.List;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.event.EventInfo;
import cn.edu.cauc.model.po.event.EventView;
import cn.edu.cauc.model.vo.Page;

/**
 * 作者： 徐楠
 *
 * 描述：<p>事故 视图查询 DAO</p>
 * 创建时间：2017年1月7日
 */
public interface IEventViewDao extends IBaseDao<EventView> {

	/**
	 * 查询事故视图
	 * 
	 * @param event
	 * @return
	 */
	public List<EventView> findEventViewList(EventView event);
	
	/**
	 * 分页查询事故信息
	 * 
	 * @return
	 */
	public Page<EventView> findEventViewPageList(EventView event, int pageNo,
			Integer pageSize);
}
