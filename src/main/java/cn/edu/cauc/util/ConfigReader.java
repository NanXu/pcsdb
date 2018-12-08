package cn.edu.cauc.util;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * 作者： 徐楠
 *
 * 描述：<p>properties文件加载方法</p>
 * 创建时间：2016年9月19日
 */
public class ConfigReader {
	
	private static final Logger logger = Logger.getLogger(ConfigReader.class);

	public static String getValue(String strKeyName){
		String retValue="";
		try {
			ResourceBundle rBundle=ResourceBundle.getBundle("pcsdb");
			retValue=rBundle.getString(strKeyName);
			return retValue;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("[key="+strKeyName+", error message "+e.getMessage() + "]");
			return retValue;
		}
	}
	
	public static void main(String[] args) {
		String filePath = ConfigReader.getValue("pollutant.source.02");
		
		System.out.println(filePath);
	}
}
