package cn.edu.cauc.controller.data;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cauc.controller.base.BaseController;
import cn.edu.cauc.model.po.data.NtsbEvent;
import cn.edu.cauc.model.po.data.SdrRawData;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.service.data.INtsbRawDataService;
import cn.edu.cauc.util.Constant;

@Controller
@RequestMapping("/data/ntsb")
public class NtsbRawDataController extends BaseController {

	private static final Logger logger = Logger.getLogger(NtsbRawDataController.class);
	
	@Resource
	private INtsbRawDataService ntsbRawDataSerrvice;
	
	@RequestMapping(value="/listRaws")
	public ModelAndView listRaws(@ModelAttribute NtsbEvent ntsbEvent, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		int startPage = this.startPaging(pageNo, pageSize);
		Page<NtsbEvent> page = ntsbRawDataSerrvice.findRawList(ntsbEvent,startPage,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("data/ntsb/raw_list");
		return mv;
	}
}
