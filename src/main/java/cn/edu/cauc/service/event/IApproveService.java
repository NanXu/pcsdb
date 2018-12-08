package cn.edu.cauc.service.event;

import cn.edu.cauc.model.po.event.ApproveInfo;
import cn.edu.cauc.model.vo.Page;

/**
 * 
 * 作者： 徐楠
 *
 * 描述：<p>审批信息 Service</p>
 * 创建时间：2016年10月14日
 */
public interface IApproveService {

	public Page<ApproveInfo> findApproveList(ApproveInfo approveInfo, int pageNo,
			Integer pageSize);

}
