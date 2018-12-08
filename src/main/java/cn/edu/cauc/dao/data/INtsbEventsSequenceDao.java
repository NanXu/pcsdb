package cn.edu.cauc.dao.data;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.data.NtsbEventsSequence;

public interface INtsbEventsSequenceDao extends IBaseDao<NtsbEventsSequence> {

	public NtsbEventsSequence findById(String eventId);
}
