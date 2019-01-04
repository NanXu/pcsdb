package cn.edu.cauc.service.event;

import java.io.IOException;
import java.util.List;

import cn.edu.cauc.model.po.event.ApproveInfo;
import cn.edu.cauc.model.po.event.Casualties;
import cn.edu.cauc.model.po.event.EventInfo;
import cn.edu.cauc.model.po.event.EventView;
import cn.edu.cauc.model.po.event.Measures;
import cn.edu.cauc.model.po.event.Plane;
import cn.edu.cauc.model.po.event.Pollutant;
import cn.edu.cauc.model.vo.Page;

/**
 * 作者： 徐楠
 *
 * 描述：<p>事故管理 Service</p>
 * 创建时间：2016年10月7日
 */
public interface IEventService {
	
	public Page<EventView> findEventList(EventView event, int startPage,
			Integer pageSize);
	
	public Page<EventInfo> findDraftEventList(EventInfo event, int pageNo,
			Integer pageSize);
	
	/**
	 * 查询需要导出的数据
	 * 
	 * @param event
	 * @return
	 */
	public List<EventView> findEventViewList(EventView event);
	
	/**
	 * 查询事故视图
	 * 
	 * @param event
	 * @return
	 */
	public EventView findEventViewById(Integer id);


	/**
	 * 根据ID查询详细信息
	 * 
	 * @param id
	 * @return
	 */
	public EventInfo findEventById(Integer id);

	/**
	 * 通过事件ID查询飞机信息
	 * 
	 * @param id
	 * @return
	 */
	public Plane findPlaneByEventId(Integer id);

	/**
	 * 通过事件ID查询伤亡信息
	 * 
	 * @param id
	 * @return
	 */
	public Casualties findCasualtiesByEventId(Integer eventId);

	/**
	 * 通过事件ID查询措施信息
	 * 
	 * @param id
	 * @return
	 */
	public Measures findMeasuresByEventId(Integer eventId);

	/**
	 * 通过事件ID查询污染物信息
	 * 
	 * @param id
	 * @return
	 */
	public Pollutant findPollutantByEventId(Integer id);
	
	/**
	 * 保存事件信息
	 * 
	 * @param event
	 * @return
	 */
	public Integer saveEventInfo(EventInfo event);

	/**
	 * 保存飞机信息
	 * 
	 * @param plane
	 */
	public void savePlane(Plane plane);

	/**
	 * 保存伤亡情况信息
	 * 
	 * @param casualties
	 */
	public void saveCasualties(Casualties casualties);

	/**
	 * 保存污染物信息
	 * 
	 * @param pollutant
	 */
	public void savePollutant(Pollutant pollutant);

	/**
	 * 保存采取措施信息
	 * 
	 * @param measures
	 */
	public void saveMeasures(Measures measures);

	/**
	 * 审批事故信息
	 * 
	 * @param approveInfo
	 */
	public void approveEvent(ApproveInfo approveInfo);
	
	/**
	 * 修改事故信息
	 * 
	 * @param approveInfo
	 */
	public void modifyEventInfo(EventInfo eventInfo);

	/**
	 * 编辑事故信息
	 * 
	 * @param view
	 */
	public void edit(EventView view);

	/**
	 * 编辑飞机信息
	 * 
	 * @param p
	 */
	public void modifyPlane(Plane p);

	public void modifyCasualties(Casualties c);

	public void modifyPollutant(Pollutant po);

	public void modifyMeasures(Measures m);

	public void deleteEvents(String ids);

}
