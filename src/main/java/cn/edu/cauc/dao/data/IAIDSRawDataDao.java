package cn.edu.cauc.dao.data;

import java.util.List;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.data.AidsRawData;
import cn.edu.cauc.model.vo.Page;

/**
 * 作者： 徐楠
 *
 * 描述：<p>原始数据处理 DAO</p>
 * 创建时间：2016年10月2日
 */
public interface IAIDSRawDataDao extends IBaseDao<AidsRawData>{

	public void batchInsert(List<AidsRawData> aidsRawDataList);

	/**
	 * Aids原始列表查询方法
	 * 
	 * @param aidsRawData
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<AidsRawData> findRawList(AidsRawData aidsRawData,
			Integer pageNo, Integer pageSize);

	/**
	 * 通过报告号查询AIDS数据
	 * @param reportNumber
	 * @return
	 */
	public AidsRawData findRawDataByReportNumber(String reportNumber);
	
	/**
	 * 查询准备入库的数据
	 * 
	 * @return
	 */
	public List<AidsRawData> findReadyRawsDatas();
	
	/**
	 * 查询导入完成的数据
	 * 
	 * @return
	 */
	public List<AidsRawData> findImportedRawsData();
	
	/**
	 * 修改数据
	 * @param aidsRawData
	 */
	public void modify(AidsRawData aidsRawData);
}
