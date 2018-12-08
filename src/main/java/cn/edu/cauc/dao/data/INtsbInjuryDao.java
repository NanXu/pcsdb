package cn.edu.cauc.dao.data;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.data.NtsbInjury;

public interface INtsbInjuryDao extends IBaseDao<NtsbInjury>{

	/**
	 * 查询机组死亡人数
	 * 
	 * @param eventId
	 * @return
	 */
	public int findCrewFatals(String eventId);
	
	/**
	 * 查询机组重伤人数
	 * 
	 * @param eventId
	 * @return
	 */
	public int findCrewSerious(String eventId);
	
	/**
	 * 查询机组轻伤人数
	 * 
	 * @param eventId
	 * @return
	 */
	public int findCrewMinors(String eventId);
	
	/**
	 * 查询乘客死亡人数
	 * 
	 * @param eventId
	 * @return
	 */
	public int findPassengerFatals(String eventId);
	
	/**
	 * 查询乘客重伤人数
	 * 
	 * @param eventId
	 * @return
	 */
	public int findPassengerSerious(String eventId);
	
	/**
	 * 查询乘客轻伤人数
	 * 
	 * @param eventId
	 * @return
	 */
	public int findPassengerMinors(String eventId);
}
