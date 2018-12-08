/**
 * 
 */
package cn.edu.cauc.controller.user;


import java.util.Date;

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
import cn.edu.cauc.model.po.system.Role;
import cn.edu.cauc.model.po.system.User;
import cn.edu.cauc.model.po.system.UserRoleRelation;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.model.vo.UserRole;
import cn.edu.cauc.model.vo.ValidateResult;
import cn.edu.cauc.service.system.IRoleService;
import cn.edu.cauc.service.system.IUserService;
import cn.edu.cauc.util.Constant;
import cn.edu.cauc.util.JacksonUtil;
import cn.edu.cauc.util.MD5;

/**
 * 作者：徐楠
 *
 * 描述：<p>用户操作 控制器类</p>
 * 创建时间：2016年2月12日
 */
@Controller
@RequestMapping("/eventUser/user")
public class EventUserController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(EventUserController.class);
	
	@Resource
	private IUserService userService;
	@Resource
	private IRoleService roleService;
	
	@RequestMapping(value="/listUsers")
	public ModelAndView listUsers(@ModelAttribute User user, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		int startPage = this.startPaging(pageNo, pageSize);
		Page<User> page = userService.findEventUserList(user,startPage,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("user/user/user_list");
		return mv;
	}
	
	@RequestMapping(value="/toAddUser")
	public ModelAndView toAddUser() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("user/user/user_add");
		return mv;
	}
	
	@RequestMapping(value="/toEditUser/{id}")
	public ModelAndView toEditUser(@PathVariable Integer id) {
		ModelAndView mv = this.getModelAndView();
		User user = userService.findUserById(id);
		mv.addObject("user", user);
		mv.setViewName("user/user/user_edit");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/forbid/{id}/{status}", method = RequestMethod.POST)
	public String forbidden(@PathVariable Integer id, @PathVariable String status) {
		try {
			userService.modifyStatusById(id, status);
			return JacksonUtil.ajaxJson(true);
		} catch (Exception e) {
			logger.debug("修改用户状态失败", e);
			return JacksonUtil.ajaxJson(false);
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/isUsernameExist", method = RequestMethod.GET)
	public String isUsernameExist(@RequestParam(value="validateValue") String username) {
		ValidateResult validateResult = new ValidateResult();
		boolean valid = true;
		valid = userService.isUsernameExist(username);
		validateResult.setValid(valid);
		return JacksonUtil.ajaxValidateJson(validateResult);
	}
	
	@ResponseBody
	@RequestMapping(value="/isLoginNameExist", method = RequestMethod.GET)
	public String isLoginNameExist(@RequestParam(value="validateValue") String loginName) {
		ValidateResult validateResult = new ValidateResult();
		boolean valid = true;
		valid = userService.isUsernameExist(loginName);
		validateResult.setValid(valid);
		return JacksonUtil.ajaxValidateJson(validateResult);
	}
	
	@ResponseBody
	@RequestMapping(value="/addUser")
	public String addUser(@ModelAttribute User user) {
		//ModelAndView mv = this.getModelAndView();
		user.setIsAdmin("N");
		user.setCreateDate(new Date());
		user.setStatus("1");
		user.setPassword(MD5.md5(user.getPassword()));
		userService.save(user);
		UserRoleRelation relation = new UserRoleRelation();
		relation.setUserId(user.getId());
		relation.setRoleId(3);//事故一般用户
		userService.saveUserRoleRelation(relation);
		return JacksonUtil.ajaxJson(true);
	}
	
	@ResponseBody
	@RequestMapping(value="/editUser")
	public String editUser(@ModelAttribute User user) {
		if(user != null) {
			User u = userService.findUserById(user.getId());
			u.setUsername(user.getUsername());
			u.setLoginName(user.getLoginName());
			u.setSex(user.getSex());
			u.setPhoneNumber(user.getPhoneNumber());
			userService.edit(u);
		}
		return JacksonUtil.ajaxJson(true);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteUser", method = RequestMethod.POST)
	public String deleteUser(@RequestParam(value="deleteIDs") String ids) {
		try {
			userService.deleteUsers(ids);
			return JacksonUtil.ajaxJson(true);
		} catch (Exception e) {
			logger.debug("删除用户失败", e);
			return JacksonUtil.ajaxJson(false);
		}
	}
	
	@RequestMapping(value="/toUserRoleAssign/{id}")
	public ModelAndView toUserRoleAssign(@PathVariable Integer id,
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = userRoleAssign(null, id, pageNo, pageSize);
		return mv;
	}
	
	@RequestMapping(value="/listRoles")
	public ModelAndView listRoles(@ModelAttribute Role role, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize,
			@RequestParam(value="userId") Integer userId) {
		ModelAndView mv = userRoleAssign(role, userId, pageNo, pageSize);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/assignRole", method = RequestMethod.POST)
	public String assignRole(@RequestParam Integer userId, 
			@RequestParam Integer roleId) {
		UserRoleRelation relation = new UserRoleRelation();
		relation.setRoleId(roleId);
		relation.setUserId(userId);
		try {
			userService.saveUserRoleRelation(relation);
			return JacksonUtil.ajaxJson(true, "用户分配角色成功");
		} catch (Exception e) {
			logger.debug("用户分配角色失败", e);
			return JacksonUtil.ajaxJson(false, "用户分配角色失败");
		}
	}
	
	@RequestMapping(value="/listUserRoles")
	public ModelAndView listUserRoles(@ModelAttribute UserRole userRole, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
					@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		pageNo = this.startPaging(pageNo, pageSize);
		Page<UserRole> page = roleService.findUserRoleList(userRole,pageNo,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("system/user/user_role_list");
		return mv;
	}
	
	private ModelAndView userRoleAssign(Role role, Integer id, Integer pageNo,
			Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		pageNo = this.startPaging(pageNo, pageSize);
		//Role role = new Role();
		Page<Role> page = roleService.findRoleList(role,pageNo,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("userId", id);
		mv.setViewName("system/user/user_role_assign");
		return mv;
	}
}
