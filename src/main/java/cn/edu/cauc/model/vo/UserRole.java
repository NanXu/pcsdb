package cn.edu.cauc.model.vo;

import java.io.Serializable;

/**
 * 作者： 徐楠
 *
 * 描述：<p>页面VO 用户名个角色关系对象</>
 * 创建时间：2016年2月22日
 */
public class UserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8319134551030245553L;

	private Integer userId;		//用户ID
	private Integer roleId;		//角色
	private String username;	//用户名
	private String roleName;	//角色名
	private String status;		//用户状态
	
	public UserRole() {
	}
	public UserRole(Integer userId, Integer roleId, String username,
			String roleName, String status) {
		this.userId = userId;
		this.roleId = roleId;
		this.username = username;
		this.roleName = roleName;
		this.status = status;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
