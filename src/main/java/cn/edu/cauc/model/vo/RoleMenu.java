package cn.edu.cauc.model.vo;

import java.io.Serializable;

public class RoleMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2167817009479093019L;
	
	private String roleName;
	private String menuIds;
	private String menuNames;
	
	public RoleMenu() {
	}

	public RoleMenu(String roleName, String menuIds) {
		super();
		this.roleName = roleName;
		this.menuIds = menuIds;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getMenuNames() {
		return menuNames;
	}

	public void setMenuNames(String menuNames) {
		this.menuNames = menuNames;
	}

}
