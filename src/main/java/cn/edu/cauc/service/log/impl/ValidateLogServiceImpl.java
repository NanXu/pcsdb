package cn.edu.cauc.service.log.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cauc.dao.log.IValidateLogDao;
import cn.edu.cauc.model.po.log.ValidateLog;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.service.log.IValidateLogService;

/**
 * 作者： 徐楠
 *
 * 描述：<p>数据入库日志</p>
 * 创建时间：2016年10月4日
 */
@Transactional
@Service("validateLogService")
public class ValidateLogServiceImpl implements IValidateLogService {
	
	@Resource
	private IValidateLogDao validateLogDao;

	@Override
	public Page<ValidateLog> findValidateLogList(ValidateLog validateLog,
			Integer pageNo, Integer pageSize) {
		return validateLogDao.findValidateLogList(validateLog, pageNo, pageSize);
	}

	
}
