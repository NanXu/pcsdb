package cn.edu.cauc.dao.data;

import java.util.List;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.data.NtsbEvent;
import cn.edu.cauc.model.vo.Page;

public interface INtsbEventDao extends IBaseDao<NtsbEvent> {

	public Page<NtsbEvent> findRawList(NtsbEvent ntsbEvent, int pageNo,
			Integer pageSize);

	/**
	 * 查询待入库的数据
	 * 
	 * @return
	 */
	public List<NtsbEvent> findPendingStorageList();
	
	/**
	 * NTSB数据更新
	 * 
	 * @param ntsbEvent
	 */
	public void modify(NtsbEvent ntsbEvent);

}
