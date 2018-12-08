package cn.edu.cauc.controller.log;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cauc.controller.base.BaseController;
import cn.edu.cauc.model.po.log.ValidateLog;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.service.log.IValidateLogService;
import cn.edu.cauc.util.Constant;

/**
 * 作者： 徐楠
 *
 * 描述：<p>数据入库校验日志 控制器类</p>
 * 创建时间：2016年10月4日
 */
@Controller
@RequestMapping("/log/validate")
public class ValidateLogController extends BaseController {

	private static final Logger logger = Logger.getLogger(ValidateLogController.class);
	
	@Resource
	private IValidateLogService validateLogService;
	
	@RequestMapping(value="/listLogs")
	public ModelAndView listLogs(@ModelAttribute ValidateLog validateLog, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		int startPage = this.startPaging(pageNo, pageSize);
		Page<ValidateLog> page = validateLogService.findValidateLogList(validateLog,startPage,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("log/validate/log_list");
		return mv;
	}
}
