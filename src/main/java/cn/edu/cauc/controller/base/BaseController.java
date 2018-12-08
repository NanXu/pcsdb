package cn.edu.cauc.controller.base;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.util.editor.DateEditor;

public class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	protected String msg;	//ajax提交返回提示信息
	
	@InitBinder  
	protected void initBinder(HttpServletRequest request,  
	                              ServletRequestDataBinder binder) throws Exception {  
	    //对于需要转换为Date类型的属性，使用DateEditor进行处理  
	    binder.registerCustomEditor(Date.class, new DateEditor());  
	}  
	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		return request;
	}

	/**
	 * 得到32位的uuid
	 * @return
	 *//*
	public String get32UUID(){
		
		return UuidUtil.get32UUID();
	}*/
	
	/**
	 * 得到分页列表的信息 
	 */
	@SuppressWarnings("rawtypes")
	public Page getPage(){
		return new Page();
	}
	
	/**
	 * 获取分页的起始值
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Integer startPaging(Integer pageNo, Integer pageSize) {
		return (pageNo - 1) * pageSize;
		//return (pageNo - 1) * pageSize;
	}
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	
}
