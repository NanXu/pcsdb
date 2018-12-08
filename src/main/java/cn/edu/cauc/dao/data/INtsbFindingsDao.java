package cn.edu.cauc.dao.data;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.data.NtsbFindings;

public interface INtsbFindingsDao extends IBaseDao<NtsbFindings> {

	public NtsbFindings findById(String eventId);
}
