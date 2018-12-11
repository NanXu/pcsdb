package cn.edu.cauc.dao.statistics;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.vo.EventStatView;
import cn.edu.cauc.model.vo.KeywordsStatView;
import cn.edu.cauc.model.vo.Page;

public interface IEventStatViewDao extends IBaseDao<EventStatView> {

	/**
	 * 事故类型
	 * 
	 * @param type
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventStatView> statEventTypeList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize);

	/**
	 * 飞机损害程度
	 * 
	 * @param type
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventStatView> statAircraftDamageList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize);
	
	/**
	 * 飞行阶段
	 * 
	 * @param type
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventStatView> statPhaseFlightList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize);
	
	/**
	 * 飞机型号
	 * @param type
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventStatView> statAircraftModelList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize);
	
	/**
	 * 飞机制造商
	 * @param type
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventStatView> statAircraftMakeList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize);
	
	/**
	 * 发动机制造商
	 * @param type
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventStatView> statEngineManufactuerList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize);
	
	/**
	 * 发动机型号
	 * @param type
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventStatView> statEngineModelList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize);
	
	/**
	 * 运营商
	 * @param type
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventStatView> statOperatorList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize);
	
	/**
	 * 维修类型
	 * @param type
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventStatView> statMaintainTypeList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize);
	
	/**
	 * 污染物来源
	 * @param type
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventStatView> statSourceList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize);
	
	/**
	 * 涉及系统
	 * @param type
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventStatView> statRelateToSystemList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize);
	
	/**
	 * 故障位置
	 * @param type
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventStatView> statBugLocationList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize);
	
	/**
	 * 故障模式
	 * @param type
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventStatView> statBugModelList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize);

	/**
	 * 预防/紧急措施
	 * @param eventStatView
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventStatView> statPreventList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize);

	/**
	 * 维修措施
	 * @param eventStatView
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<EventStatView> statMaintainList(EventStatView eventStatView,
			Integer pageNo, Integer pageSize);

	public Page<KeywordsStatView> statKeywordsList(KeywordsStatView keywordsStatView,
												   Integer pageNo, Integer pageSize);
}
