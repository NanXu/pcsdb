package cn.edu.cauc.service.dictionary.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cauc.dao.dictionary.IDataSourceDao;
import cn.edu.cauc.model.po.dictionary.DataSource;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.service.dictionary.IDataSourceService;

/**
 * 作者： 徐楠
 *
 * 描述：<p>数据来源字典维护 Service</p>
 * 创建时间：2016年9月14日
 */
@Transactional
@Service("dataSourceService")
public class DataSourceServiceImpl implements IDataSourceService {

	@Resource
	private IDataSourceDao dataSourceDao;

	@Override
	public Page<DataSource> findDataSourceList(DataSource dataSource, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		return dataSourceDao.findDataSourceList(dataSource, pageNo, pageSize);
	}

	@Override
	public DataSource findDataSourceById(Integer id) {
		// TODO Auto-generated method stub
		return dataSourceDao.getById(id);
	}
	
	
}
