package cn.edu.cauc.model.vo;

import java.io.Serializable;

/**
 * 作者： 徐楠
 *
 * 描述：<p>树形结构对象</p>
 * 创建时间：2016年3月17日
 */
public class TreeNode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3698992406022400887L;
	
	private String id;
	private String parentId;
	private String name;
	private boolean open;
	private boolean checked;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
}
