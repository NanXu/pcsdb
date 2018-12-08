package cn.edu.cauc.dao.data;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.data.NtsbAircraft;
import cn.edu.cauc.model.po.data.NtsbEngines;

public interface INtsbEnginesDao extends IBaseDao<NtsbEngines> {

	public NtsbEngines findById(String eventId);
}
