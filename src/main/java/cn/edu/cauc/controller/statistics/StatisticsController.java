package cn.edu.cauc.controller.statistics;

import javax.annotation.Resource;

import cn.edu.cauc.model.vo.KeywordsStatView;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cauc.controller.base.BaseController;
import cn.edu.cauc.model.vo.EventStatView;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.service.statistics.IStatisticsService;
import cn.edu.cauc.util.Constant;

@Controller
@RequestMapping("/statistics")
public class StatisticsController extends BaseController {

	private static final Logger logger = Logger.getLogger(StatisticsController.class);
	
	@Resource
	public IStatisticsService statisticsService;
	
	@RequestMapping(value="/index")
	public ModelAndView inedx() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("statistics/index");
		return mv;
	}
	
	@RequestMapping(value="/list")
	public ModelAndView list(@ModelAttribute EventStatView eventStatView,
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		if(eventStatView == null) {
			eventStatView = new EventStatView();
		}
		int startPage = this.startPaging(pageNo, pageSize);
		Page<EventStatView> page  = statisticsService.statEventInfo(eventStatView, startPage, pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("statistics/index");
		return mv;
	}

	public ModelAndView statByKewords(@ModelAttribute KeywordsStatView keywordsStatView,
									  @RequestParam(value="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		if(keywordsStatView == null) {
			keywordsStatView = new KeywordsStatView();
		}
		int startPage = this.startPaging(pageNo, pageSize);
		Page<KeywordsStatView> page  = statisticsService.statEventInfoByKeywords(keywordsStatView, startPage, pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("statistics/keywords");
		return mv;
	}
}
