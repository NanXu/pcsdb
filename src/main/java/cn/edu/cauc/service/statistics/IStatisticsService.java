package cn.edu.cauc.service.statistics;

import cn.edu.cauc.model.vo.EventStatView;
import cn.edu.cauc.model.vo.Page;

/**
 * 作者： 徐楠
 *
 * 描述：<p>TODO</p>
 * 创建时间：2017年4月19日
 */
public interface IStatisticsService {

	public Page<EventStatView> statEventInfo(EventStatView eventStatView, Integer pageNo, Integer pageSize);
}
