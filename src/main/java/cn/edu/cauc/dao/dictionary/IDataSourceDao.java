package cn.edu.cauc.dao.dictionary;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.dictionary.DataSource;
import cn.edu.cauc.model.vo.Page;

/**
 * 作者： 徐楠
 *
 * 描述：<p>数据来源字典表 DAO 接口</p>
 * 创建时间：2016年9月14日
 */
public interface IDataSourceDao extends IBaseDao<DataSource> {

	/**
	 * 分页查询数据来源字典
	 * 
	 * @param dataSource
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<DataSource> findDataSourceList(DataSource dataSource, Integer pageNo,
			Integer pageSize);

}
