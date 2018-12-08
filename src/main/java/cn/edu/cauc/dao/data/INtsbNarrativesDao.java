package cn.edu.cauc.dao.data;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.data.NtsbNarratives;

public interface INtsbNarrativesDao extends IBaseDao<NtsbNarratives> {

	public NtsbNarratives findById(String eventId);
}
