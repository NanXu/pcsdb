package cn.edu.cauc.service.dictionary;

import cn.edu.cauc.model.po.dictionary.DataSource;
import cn.edu.cauc.model.vo.Page;

/**
 * 作者： 徐楠
 *
 * 描述：<p>数据来源字典 Service</p>
 * 创建时间：2016年9月14日
 */
public interface IDataSourceService {

	/**
	 * 分页查询数据来源
	 * 
	 * @param dataSource
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<DataSource> findDataSourceList(DataSource dataSource, Integer pageNo,
			Integer pageSize);
	
	/**
	 * 通过主键ID加载数据来源
	 * 
	 * @param id
	 * 		主键ID
	 * @return
	 */
	public DataSource findDataSourceById(Integer id);

}
