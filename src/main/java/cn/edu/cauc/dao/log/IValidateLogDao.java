package cn.edu.cauc.dao.log;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.log.ValidateLog;
import cn.edu.cauc.model.vo.Page;

/**
 * 作者： 徐楠
 *
 * 描述：<p>数据校验日志</p>
 * 创建时间：2016年10月4日
 */
public interface IValidateLogDao extends IBaseDao<ValidateLog> {

	public Page<ValidateLog> findValidateLogList(ValidateLog validateLog,
			Integer pageNo, Integer pageSize);
	
	/**
	 * 添加数据(单独事务)
	 */
	public void add(ValidateLog log);
	
	/**
	 * 修改日志(单独事务)
	 * @param log
	 */
	public void modify(ValidateLog log);
}
