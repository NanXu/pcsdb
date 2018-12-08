package cn.edu.cauc.dao.data;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.data.NtsbAircraft;

public interface INtsbAircraftDao extends IBaseDao<NtsbAircraft> {

	
	public NtsbAircraft findById(String eventId);
}
