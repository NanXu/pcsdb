package cn.edu.cauc.controller.data;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cauc.controller.base.BaseController;
import cn.edu.cauc.model.po.data.SdrRawData;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.service.data.ISdrRawDataService;
import cn.edu.cauc.util.Constant;

import java.io.File;
import java.io.IOException;

/**
 * 作者： 徐楠
 *
 * 描述：<p>SDR原始数据管理控制器</p>
 * 创建时间：2016年11月11日
 */
@Controller
@RequestMapping("/data/sdr")
public class SdrRawDataController extends BaseController {



	private static final Logger logger = Logger.getLogger(SdrRawDataController.class);
	
	@Resource
	private ISdrRawDataService sdrRawDataService;
	
	@RequestMapping(value="/listRaws")
	public ModelAndView listRaws(@ModelAttribute SdrRawData sdrRawData, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		int startPage = this.startPaging(pageNo, pageSize);
		Page<SdrRawData> page = sdrRawDataService.findRawList(sdrRawData,startPage,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("data/sdr/raw_list");
		return mv;
	}

	@RequestMapping(value="/addSdrData", method = RequestMethod.POST)
	public void addSdrData() {
		sdrRawDataService.addSdrTxtData();
	}

}
