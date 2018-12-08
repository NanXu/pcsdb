package cn.edu.cauc.model.vo;

import java.io.Serializable;

/**
 * 作者： 徐楠
 *
 * 描述：<p>TODO</>
 * 创建时间：2016年2月25日
 */
public class SelectModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1679888833646499643L;
	private String key;		//下拉菜单显示
	private String value;	//下拉菜单value
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	
}
