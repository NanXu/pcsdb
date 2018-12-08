package cn.edu.cauc.service.data.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cauc.dao.data.INtsbEventDao;
import cn.edu.cauc.model.po.data.NtsbEvent;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.service.data.INtsbRawDataService;

@Transactional
@Service("ntsbRawDataService")
public class NtsbRawDataServiceImpl implements INtsbRawDataService {

	@Resource
	private INtsbEventDao ntsbEventDao;

	@Override
	public Page<NtsbEvent> findRawList(NtsbEvent ntsbEvent, int pageNo,
			Integer pageSize) {
		return ntsbEventDao.findRawList(ntsbEvent, pageNo, pageSize);
	}
	
	
}
