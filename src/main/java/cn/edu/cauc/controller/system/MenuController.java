package cn.edu.cauc.controller.system;

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
import cn.edu.cauc.model.po.system.Menu;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.model.vo.ValidateResult;
import cn.edu.cauc.service.system.IMenuService;
import cn.edu.cauc.util.Constant;
import cn.edu.cauc.util.JacksonUtil;
import cn.edu.cauc.util.StringUtil;

/**
 * 作者：徐楠
 *
 * 描述：<p>菜单管理 控制器类</p>
 * 创建时间：2016年2月20日
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(MenuController.class);

	@Resource
	private IMenuService menuService;
	
	@RequestMapping(value="/listMenus")
	public ModelAndView listUsers(@ModelAttribute Menu menu, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		int startPage = this.startPaging(pageNo, pageSize);
		Page<Menu> page = menuService.findMenuList(menu,startPage,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("system/menu/menu_list");
		return mv;
	}
	
	@RequestMapping(value="/toAddMenu/{id}")
	public ModelAndView toAddMenu(@PathVariable Integer id) {
		ModelAndView mv = this.getModelAndView();
		mv.addObject("parentId", id);
		mv.setViewName("system/menu/menu_add");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/isMenuNameExist", method = RequestMethod.GET)
	public String isMenuNameExist(@RequestParam(value="validateValue") String name) {
		//name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		name = StringUtil.strCharacterEncoding(name);
		ValidateResult validateResult = new ValidateResult();
		boolean valid = true;
		valid = menuService.isMenuNameExist(name);
		validateResult.setValid(valid);
		return JacksonUtil.ajaxValidateJson(validateResult);
	}
	
	@ResponseBody
	@RequestMapping(value="/addMenu")
	public String addMenu(@ModelAttribute Menu menu) {
		try {
			menuService.save(menu);
			return JacksonUtil.ajaxJson(true, "添加菜单成功");
		} catch (Exception e) {
			logger.debug("添加角色失败", e);
			return JacksonUtil.ajaxJson(false, "添加菜单失败");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/validateMenuHasAssociationRole", method = RequestMethod.POST)
	public String validateMenuHasAssociationRole(@RequestParam(value="deleteIDs") String ids) {
		boolean flag = menuService.validateMenuHasAssociationRole(ids);
		if(!flag) {
			return JacksonUtil.ajaxJson(true, "");
		} else {
			return JacksonUtil.ajaxJson(false, "菜单已关联角色不能删除");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/hasRootMenu", method = RequestMethod.POST)
	public String hasRootMenu(@RequestParam(value="deleteIDs") String ids) {
		boolean flag = menuService.hasRootMenu(ids);
		if(flag) {
			return JacksonUtil.ajaxJson(true, "菜单中包含根节点菜单，确定级联删除吗？");
		} else {
			return JacksonUtil.ajaxJson(false, "");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteMenu", method = RequestMethod.POST)
	public String deleteMenu(@RequestParam(value="deleteIDs") String ids) {
		try {
			menuService.deleteMenus(ids);
			return JacksonUtil.ajaxJson(true, "删除菜单成功");
		} catch (Exception e) {
			logger.debug("删除用户失败", e);
			return JacksonUtil.ajaxJson(false, "删除菜单失败");
		}
	}
}
