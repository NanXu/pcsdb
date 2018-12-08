package cn.edu.cauc.controller.data;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cauc.controller.base.BaseController;
import cn.edu.cauc.model.po.data.AidsRawData;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.model.vo.SessionUser;
import cn.edu.cauc.service.data.IAidsRawDataService;
import cn.edu.cauc.util.Constant;
import cn.edu.cauc.util.JacksonUtil;

/**
 * 作者： 徐楠
 *
 * 描述：<p>AIDS数据源管理 控制器类</p>
 * 创建时间：2016年10月4日
 */
@Controller
@RequestMapping("/data/aids")
public class AIDSRawDataController extends BaseController {

	private static final Logger logger = Logger.getLogger(AIDSRawDataController.class);
	
	@Resource
	private IAidsRawDataService aidsRawDataService;
	
	@RequestMapping(value="/listRaws")
	public ModelAndView listRaws(@ModelAttribute AidsRawData aidsRawData, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		int startPage = this.startPaging(pageNo, pageSize);
		Page<AidsRawData> page = aidsRawDataService.findRawList(aidsRawData,startPage,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("data/aids/raw_list");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/importRaws", method = RequestMethod.GET)
	public String importRaws() {
		try {
			aidsRawDataService.importAidsRawDatas();
			return JacksonUtil.ajaxJson(true);
		} catch (Exception e) {
			logger.debug("装载AIDS数据异常", e);
			return JacksonUtil.ajaxJson(false);
		}
	}
	
	/**
	 * 抽取数据到Event中
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/extractRaws", method = RequestMethod.POST)
	public String extractRaws() {
		try {
			//aidsRawDataService.importAidsRawDatas();
			SessionUser sessionUser = (SessionUser)getRequest().getSession().getAttribute(Constant.SESSION_USER);
			aidsRawDataService.extractRaws(sessionUser);
			return JacksonUtil.ajaxJson(true);
		} catch (Exception e) {
			logger.debug("装载AIDS数据异常", e);
			return JacksonUtil.ajaxJson(false);
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/spiderRaws", method = RequestMethod.POST)
	public String spiderRaws() {
		try {
			aidsRawDataService.spiderEventRemark();
			return JacksonUtil.ajaxJson(true, "数据抓取完成");
		} catch (Exception e) {
			logger.debug("装载AIDS数据异常", e);
			return JacksonUtil.ajaxJson(false, "数据抓取失败");
		}
	}
	
	@RequestMapping(value="/detailRaws/{reportNumber}")
	public ModelAndView detailRaws(@PathVariable String reportNumber) {
		ModelAndView mv = this.getModelAndView();
		AidsRawData aidsRawData = aidsRawDataService.findRawDataByReportNumber(reportNumber);
		mv.addObject("aidsRawData", aidsRawData);
		mv.setViewName("data/aids/raw_detail");
		return mv;
	}
}
