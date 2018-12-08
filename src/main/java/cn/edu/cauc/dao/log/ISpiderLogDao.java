package cn.edu.cauc.dao.log;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.log.SpiderLog;

/**
 * 作者： 徐楠
 *
 * 描述：<p>数据抓取日志 DAO</p>
 * 创建时间：2016年10月10日
 */
public interface ISpiderLogDao extends IBaseDao<SpiderLog> {

	public void save(SpiderLog log);
}
