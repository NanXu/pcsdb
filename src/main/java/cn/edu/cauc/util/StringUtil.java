package cn.edu.cauc.util;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

/**
 * 作者：徐楠
 *
 * 描述：<p>字符串工具类</p>
 * 创建时间：2016年2月14日
 */
public class StringUtil {
	
	private static final Logger logger = Logger.getLogger(StringUtil.class);

	/**
	 * 将逗号分隔的字符串转化成数组
	 * 
	 * @param str
	 * @return
	 */
	public static String[] strSplitArray(String str) {
		if(!isNull(str) && str.indexOf(",") != -1) {
			return str.split(",");
		} else {
			return null;
		}
	}
	
	/**
	 * 字符串是否为空
	 * 
	 * @param str
	 * 			字符串
	 * @return
	 */
	public static boolean isNull(String str) {
		if(str != null && !"".equals(str)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 字符串转码（ISO-8859-1=>UTF-8）
	 * 
	 * @param str
	 * 			原始字符串
	 * @return
	 */
	public static String strCharacterEncoding(String str) {
		try {
			if(!isNull(str)) {
				return new String(str.getBytes("ISO-8859-1"), "UTF-8");
			} else {
				return null;
			}
		} catch (UnsupportedEncodingException e) {
			logger.debug("[字符串转码异常]", e);
			return null;
		}
	}
	
	/**
	 * String数组转化成Integer数组
	 * 
	 * @param ids
	 * @return
	 */
	public static Integer[] strArrToInteger(String[] ids) {
		Integer[] intArr = null; 
		if(ids.length > 0) {
			intArr = new Integer[ids.length];
			for(int i = 0; i < ids.length; i ++) {
				intArr[i] = Integer.parseInt(ids[i]);
			}
		}
		return intArr;
	}
}
