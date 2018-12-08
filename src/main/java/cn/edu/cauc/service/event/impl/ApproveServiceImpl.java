package cn.edu.cauc.service.event.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cauc.dao.event.IApproveInfoDao;
import cn.edu.cauc.model.po.event.ApproveInfo;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.service.event.IApproveService;

@Transactional
@Service("approveService")
public class ApproveServiceImpl implements IApproveService {
	
	@Resource
	private IApproveInfoDao approveInfoDao;

	@Override
	public Page<ApproveInfo> findApproveList(ApproveInfo approveInfo,
			int pageNo, Integer pageSize) {
		return approveInfoDao.findApproveList(approveInfo, pageNo, pageSize);
	}

}
