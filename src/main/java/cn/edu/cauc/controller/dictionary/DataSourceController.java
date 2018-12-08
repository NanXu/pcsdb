package cn.edu.cauc.controller.dictionary;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cauc.controller.base.BaseController;
import cn.edu.cauc.model.po.dictionary.DataSource;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.service.dictionary.IDataSourceService;
import cn.edu.cauc.util.Constant;


/**
 * 作者： 徐楠
 *
 * 描述：<p>字典管理->数据来源字典 控制器类</p>
 * 创建时间：2016年9月14日
 */
@Controller
@RequestMapping("/dictionary/dataSource")
public class DataSourceController extends BaseController {

	private static final Logger logger = Logger.getLogger(DataSourceController.class);
	
	@Resource
	private IDataSourceService dataSourceService;
	
	@RequestMapping(value="/listDataSources")
	public ModelAndView listDataSources(@ModelAttribute DataSource dataSource, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		int startPage = this.startPaging(pageNo, pageSize);
		Page<DataSource> page = dataSourceService.findDataSourceList(dataSource,startPage,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("dictionary/dataSource/data_source_list");
		return mv;
	}
	
	@RequestMapping(value="/toEditDataSource/{id}")
	public ModelAndView toEditDataSource(@PathVariable Integer id) {
		ModelAndView mv = this.getModelAndView();
		DataSource dataSource = dataSourceService.findDataSourceById(id);
		mv.addObject("dataSource", dataSource);
		mv.setViewName("dictionary/dataSource/data_source_edit");
		return mv;
	}
}
