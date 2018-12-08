package cn.edu.cauc.dao.log;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.log.ValidateFailedDetailLog;

/**
 * 作者： 徐楠
 *
 * 描述：<p>数据校验失败详细日志</p>
 * 创建时间：2016年10月4日
 */
public interface IValidateFailedDetailLogDao extends IBaseDao<ValidateFailedDetailLog> {

	/**
	 * 添加详细日志（单独事务）
	 * 
	 * @param logDetail
	 */
	public void add(ValidateFailedDetailLog logDetail);
}
