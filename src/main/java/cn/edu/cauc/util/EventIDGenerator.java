package cn.edu.cauc.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventIDGenerator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3257182129120296614L;
	
	public static String generator() {
		String id = "";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		id = sdf.format(date);
		return id;
	}

}
