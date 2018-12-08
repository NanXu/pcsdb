package cn.edu.cauc.util.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

import org.apache.log4j.Logger;

import cn.edu.cauc.controller.event.EventController;

/**
 * 作者： 徐楠
 * 
 * 描述：
 * <p>
 * 处理SDR的TXT文件读取
 * </p>
 * 创建时间：2016年10月19日
 */
public class TxtUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6205910233750857899L;
	
	private static final Logger logger = Logger.getLogger(TxtUtil.class);

	/**
	 * 按照路径读取文件夹下的所有文件
	 * 
	 * @param pathname
	 *            路径为文件夹目录
	 */
	public void readTxt(String pathname) {
		File file = new File(pathname);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				if(tempString.indexOf("[") != -1) {
					tempString = tempString.replaceAll("[", "");
				}
				if(tempString.indexOf("]") != -1) {
					tempString = tempString.replaceAll("]", "");
				}
				logger.info(tempString);
				System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}
	
	public static void main(String[] args) {
		TxtUtil util = new TxtUtil();
		String pathname = "E:/12.sql";
		util.readTxt(pathname);
	}
}
