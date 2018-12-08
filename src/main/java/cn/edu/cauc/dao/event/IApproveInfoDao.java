package cn.edu.cauc.dao.event;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.event.ApproveInfo;
import cn.edu.cauc.model.vo.Page;

public interface IApproveInfoDao extends IBaseDao<ApproveInfo> {

	public Page<ApproveInfo> findApproveList(ApproveInfo approveInfo, int pageNo,
			Integer pageSize);

}
