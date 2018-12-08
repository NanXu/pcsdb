package cn.edu.cauc.dao.data;

import java.util.List;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.data.SdrRawData;
import cn.edu.cauc.model.vo.Page;

/**
 * 
 * 作者： 徐楠
 *
 * 描述：<p>sdr原始数据处理DAO</p>
 * 创建时间：2016年11月11日
 */
public interface ISdrRawDataDao extends IBaseDao<SdrRawData> {

	public Page<SdrRawData> findRawList(SdrRawData sdrRawData, int pageNo,
			Integer pageSize);

	/**
	 * 查询待入库的SDR数据
	 * 
	 * @return
	 */
	public List<SdrRawData> findPendingStorageList();
	
	/**
	 * 修改原始数据(单独事务)
	 * 
	 * @param sdrRawData
	 */
	public void modify(SdrRawData sdrRawData);

	/**
	 * 原始的Sdr的文本数据入库
	 * @param arrTxt
	 */
	public void sdrTxtInsert(String table, String[] arrTxt);

}
