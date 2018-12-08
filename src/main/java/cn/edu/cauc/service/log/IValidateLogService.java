package cn.edu.cauc.service.log;

import cn.edu.cauc.model.po.log.ValidateLog;
import cn.edu.cauc.model.vo.Page;

/**
 * 作者： 徐楠
 *
 * 描述：<p>数据入库校验日志</p>
 * 创建时间：2016年10月4日
 */
public interface IValidateLogService {

	public Page<ValidateLog> findValidateLogList(ValidateLog validateLog,
			Integer pageNo, Integer pageSize);

}
