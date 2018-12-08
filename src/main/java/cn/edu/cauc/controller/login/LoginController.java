package cn.edu.cauc.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cauc.controller.base.BaseController;
import cn.edu.cauc.model.po.system.User;
import cn.edu.cauc.model.vo.SessionUser;
import cn.edu.cauc.service.system.IUserService;
import cn.edu.cauc.util.Constant;

/**
 * 作者：徐楠
 *
 * 描述：<p>系统登录 控制器类</p>
 * 创建时间：2016年2月13日
 */
@Controller
public class LoginController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	/**
	 * 用户service
	 */
	@Resource
	private IUserService userService;
	
	@RequestMapping(value="/toLogin", method = RequestMethod.GET)
	public ModelAndView toLogin() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public Map<String,Object> login( User user){
		Map<String,Object> map = new HashMap<String,Object>();
		//String errorInfo = "";
		String msg = "";
		User u = userService.findUserByLoginNameAndPwd(user.getLoginName(), user.getPassword());
		if(u != null) {
			if(Constant.FORBIDDEN.equals(u.getStatus())) {
				//用户禁用
				map.put("loginResult", false);
				msg = "用户已禁用，请联系管理员！";
			} else {
				//保存登录的session
				SessionUser sessionUser = new SessionUser();
				BeanUtils.copyProperties(u, sessionUser);
				sessionUser.setRole(userService.findRoleByUserId(u.getId()));
				sessionUser.setMenuList(userService.findMenuByUserId(u.getId()));
				getRequest().getSession().setAttribute(Constant.SESSION_USER, sessionUser);
				map.put("loginResult", true);
				msg = "&nbsp;&nbsp;登录成功，正在转到主页...";
			}
		} else {
			logger.info("登录名为【"+user.getLoginName()+"】的用户登录失败！");
			map.put("loginResult", false);
			msg = "用户名或者密码错误，请重新登录！";
			//map.put(key, value)
		}
		map.put("msg", msg);
		return map;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logout() {
		ModelAndView mv = this.getModelAndView();
		
		SessionUser sessionUser = (SessionUser)getRequest().getSession().getAttribute(Constant.SESSION_USER);
		if(sessionUser != null) {
			sessionUser = null;
		}
		mv.setViewName("redirect:/toLogin");
		return mv;
	}
	
	@RequestMapping(value="/system/index")
	public String index() {
		return "system/index/main";
	}
	
	@RequestMapping(value="/system/left")
	public ModelAndView left() {
		ModelAndView mv = this.getModelAndView();
		SessionUser sessionUser = (SessionUser)getRequest().getSession().getAttribute(Constant.SESSION_USER);
		mv.addObject("menuList", sessionUser.getMenuList());
		mv.setViewName("system/index/left");
		return mv;
	}
}
