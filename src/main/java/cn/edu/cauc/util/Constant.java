package cn.edu.cauc.util;


/**
 * 作者：徐楠
 *
 * 描述：<p>系统常量类</p>
 * 创建时间：2016年2月13日
 */
public class Constant {
	
	public static final String LOGIN_URL = "/toLogin";				//登录地址
	public static final String SESSION_USER = "sessionUser";
	public static final Integer PAGE_SIZE	= 10;			//分页条数配置路径
	
	//------------角色信息---------------------
	public static final String ROLE_ADMIN = "sys_admin";
	public static final String ROLE_NORMAL = "sys_normal";
	
	//-----------用户 启用 禁用状态-----------------
	public static final String NORMAL = "1";
	public static final String FORBIDDEN = "0";
	
	//------------假删除字段---------------------
	public static final String YES = "Y";
	public static final String NO = "N";
	
	//-----------入库状态字段------------------
//	public static final String STATUS_SUCCESS = "1";
//	public static final String STATUS_READY = "0";
	public static final String READY_COMPLETED = "2";
	public static final String READY_SPIDER = "1";
	public static final String READY_IMPORTED = "0";
	
	//-----------数据来源------------------
	public static final String AIDS = "AIDS";
	public static final String SDR = "SDR";
	public static final String NTSB = "NTSB";
	
	//---------------校验状态----------------
	public static final String VALIDATE_STATUS_SUCCESS = "1";
	public static final String VALIDATE_STATUS_FALIARE = "0";
	
	//-------------事故状态0：起草;1：发布------
	public static final String EVENT_DRAFT = "0"; //起草
	public static final String EVENT_PUBLISH = "1"; //发布
	public static final String EVENT_UNPASS = "2"; //审批未通过
	
	//------------aids 抓取日志状态----------
	public static final String SPIDER_SUCCESS = "1"; //抓取成功
	public static final String SPIDER_FAILARE = "0"; //抓取失败
	
}
