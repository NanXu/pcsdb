package cn.edu.cauc.service.data;

import cn.edu.cauc.model.po.data.AidsRawData;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.model.vo.SessionUser;

/**
 * 作者： 徐楠
 *
 * 描述：<p>AIDS原始数据源管理 Service 接口</p>
 * 创建时间：2016年10月4日
 */
public interface IAidsRawDataService {

	/**
	 * 导入AIDS原始数据源
	 * @throws Exception
	 */
	public void importAidsRawDatas() throws Exception;

	/**
	 * 列表查询
	 * 
	 * @param aidsRawData
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<AidsRawData> findRawList(AidsRawData aidsRawData,
			Integer pageNo, Integer pageSize);

	/**
	 * 根据报告号查询数据
	 * 
	 * @param reportNumber
	 * @return
	 */
	public AidsRawData findRawDataByReportNumber(String reportNumber);

	/**
	 * 抽取原始数据到事件数据库中
	 */
	public void extractRaws(SessionUser sessionUser);
	
	/**
	 * 爬虫抓取事故备注信息
	 */
	public void spiderEventRemark();
}
