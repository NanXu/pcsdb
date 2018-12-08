package cn.edu.cauc.controller.system;

import java.util.Date;
import java.util.List;

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
import cn.edu.cauc.model.po.system.Role;
import cn.edu.cauc.model.po.system.RoleMenuRelation;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.model.vo.RoleMenu;
import cn.edu.cauc.model.vo.TreeNode;
import cn.edu.cauc.model.vo.ValidateResult;
import cn.edu.cauc.service.system.IMenuService;
import cn.edu.cauc.service.system.IRoleService;
import cn.edu.cauc.util.Constant;
import cn.edu.cauc.util.JacksonUtil;
import cn.edu.cauc.util.StringUtil;

/**
 * 作者：徐楠
 *
 * 描述：<p>角色操作 控制器类</p>
 * 创建时间：2016年2月17日
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {

	private static final Logger logger = Logger.getLogger(RoleController.class);
	
	@Resource
	private IRoleService roleService;
	@Resource
	private IMenuService menuService;
	
	@RequestMapping(value="/listRoles")
	public ModelAndView listUsers(@ModelAttribute Role role, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		int startPage = this.startPaging(pageNo, pageSize);
		Page<Role> page = roleService.findRoleList(role,startPage,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("system/role/role_list");
		return mv;
	}
	
	@RequestMapping(value="/toAddRole")
	public ModelAndView toAddRole() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/role/role_add");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/isRoleNameExist", method = RequestMethod.GET)
	public String isRoleNameExist(@RequestParam(value="validateValue") String name) {
		//name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		name = StringUtil.strCharacterEncoding(name);
		ValidateResult validateResult = new ValidateResult();
		boolean valid = true;
		valid = roleService.isRoleNameExist(name);
		validateResult.setValid(valid);
		return JacksonUtil.ajaxValidateJson(validateResult);
	}
	
	@ResponseBody
	@RequestMapping(value="/addRole")
	public String addRole(@ModelAttribute Role role) {
		//ModelAndView mv = this.getModelAndView();
		role.setCreateDate(new Date());
		try {
			roleService.save(role);
			return JacksonUtil.ajaxJson(true);
		} catch (Exception e) {
			logger.debug("添加角色失败", e);
			return JacksonUtil.ajaxJson(false);
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteRole", method = RequestMethod.POST)
	public String deleteRole(@RequestParam(value="deleteIDs") String ids) {
		try {
			//ids角色是否包含用户信息
			String roleNames = roleService.validateRoleHasUsers(ids);
			if("".equals(roleNames)) {
				roleService.deleteRoles(ids);
				return JacksonUtil.ajaxJson(true, "删除角色成功");
			} else {
				return JacksonUtil.ajaxJson(false, "角色已分配用户不能删除");
			}
			
		} catch (Exception e) {
			logger.debug("删除用户失败", e);
			return JacksonUtil.ajaxJson(false, "删除角色失败");
		}
	}
	
	@RequestMapping(value="/toRoleMenuAssociation/{id}")
	public ModelAndView toRoleMenuAssociation(@PathVariable Integer id,
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize) {
		//ModelAndView mv = roleMenuAssociation(null, id, pageNo, pageSize);
		ModelAndView mv = this.getModelAndView();
		List<TreeNode> menuTreeNodeList = menuService.findRoleMenuTree(id);
		String json = JacksonUtil.Object2Json(menuTreeNodeList);
		//System.out.println(json);
		mv.addObject("menuList", json);
		mv.addObject("roleId", id);
		mv.setViewName("system/role/menu_tree");
		return mv;
	}
	
	@Deprecated
	@RequestMapping(value="/listMenus")
	public ModelAndView listMenus(@ModelAttribute Menu menu, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize,
			@RequestParam(value="roleId") Integer roleId) {
		ModelAndView mv = roleMenuAssociation(menu, roleId, pageNo, pageSize);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/associationMenu", method = RequestMethod.POST)
	public String associationMenu(@RequestParam Integer roleId, 
			@RequestParam String menuIds) {
		try {
			//查询角色是否分配菜单；如果已分配就更新
			RoleMenuRelation relation = roleService.findRoleMenuRelationByRoleId(roleId);
			if(relation != null) {
				//menuIds = menuService.addRootMenuIds(menuIds.substring(1));
				relation.setMenuIds(menuIds);
				roleService.modifyRoleMenuRelation(relation);
				return JacksonUtil.ajaxJson(true, "角色关联菜单成功");
			} else {
				//menuIds = menuService.addRootMenuIds(menuIds.substring(1));
				RoleMenuRelation rmRelation = new RoleMenuRelation();
				rmRelation.setRoleId(roleId);
				rmRelation.setMenuIds(menuIds);
				roleService.saveRoleMenuRelation(rmRelation);
				return JacksonUtil.ajaxJson(true, "角色关联菜单成功");
			}
		} catch (Exception e) {
			logger.debug("角色关联菜单失败", e);
			return JacksonUtil.ajaxJson(false, "角色关联菜单失败");
		}
	}
	
	@RequestMapping(value="/listRoleMenus")
	public ModelAndView listRoleMenus(@ModelAttribute RoleMenu roleMenu, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
					@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		pageNo = this.startPaging(pageNo, pageSize);
		Page<RoleMenu> page = roleService.findRoleMenuList(roleMenu,pageNo,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("system/role/role_menu_list");
		return mv;
	}
	
	@Deprecated
	private ModelAndView roleMenuAssociation(Menu menu, Integer id, Integer pageNo,
			Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		pageNo = this.startPaging(pageNo, pageSize);
		//Menu menu = new Menu();
		Page<Menu> page = menuService.findMenuList(menu, pageNo, pageSize);
		//Page<Role> page = roleService.findRoleList(role,pageNo,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("roleId", id);
		mv.setViewName("system/role/role_menu_association");
		return mv;
	}
}
