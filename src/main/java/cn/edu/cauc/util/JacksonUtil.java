package cn.edu.cauc.util;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.log4j.Logger;

import cn.edu.cauc.model.vo.ValidateResult;

/**
 * 
 * 作者：徐楠
 *
 * 描述：<p>JSON工具类</p>
 * 创建时间：2016年2月16日
 */
public class JacksonUtil {
	
	private static final Logger logger = Logger.getLogger(JacksonUtil.class);

	/**
	 * 对象转化成JSON字符串工具类
	 * 
	 * @param obj
	 * @return
	 */
	public static String Object2Json(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			mapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
			json = mapper.writeValueAsString(obj);

			System.out.println(json);
		} catch (Exception e) {
			logger.debug("对象转化成JSON出错", e);
			//e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 页面ajax远程校验返回JSON
	 * 
	 * @param validateResult
	 * 				远程校验对象
	 * @return
	 */
	public static String ajaxValidateJson(ValidateResult validateResult) {
		Map<String, ValidateResult> map = new HashMap<String, ValidateResult>();
		map.put("validateResult", validateResult);
		return Object2Json(map);
	}
	
	/**
	 * 普通Ajax提交返回成功标识
	 * 
	 * @param flag
	 * 			成功标识
	 * @return
	 */
	public static String ajaxJson(boolean flag) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("success", flag);
		return Object2Json(map);
	}
	
	/**
	 * 普通Ajax提交返回成功标识和提示信息
	 * 
	 * @param flag
	 * 			成功标识
	 * @param msg
	 * 			提示信息
	 * @return
	 */
	public static String ajaxJson(boolean flag, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", flag);
		map.put("msg", msg);
		return Object2Json(map);
	}
}
