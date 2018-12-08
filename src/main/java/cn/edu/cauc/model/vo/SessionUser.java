package cn.edu.cauc.model.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.edu.cauc.model.po.system.Menu;
import cn.edu.cauc.model.po.system.Role;

/**
 * 作者：徐楠
 *
 * 描述：<p>用户Session信息</p>
 * 创建时间：2016年2月14日
 */
public class SessionUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5030463222074452234L;

	private Integer id;
	private String loginName;
	private String username;
	private String password;
	private String isAdmin;
	private String status;
	private Date createDate;
	private Role role;
	private List<Menu> menuList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	
}
