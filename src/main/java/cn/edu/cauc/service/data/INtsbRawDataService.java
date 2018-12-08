package cn.edu.cauc.service.data;

import cn.edu.cauc.model.po.data.NtsbEvent;
import cn.edu.cauc.model.vo.Page;

public interface INtsbRawDataService {

	public Page<NtsbEvent> findRawList(NtsbEvent ntsbEvent, int startPage,
			Integer pageSize);

}
