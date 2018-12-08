package cn.edu.cauc.model.po.system;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 作者：徐楠
 *
 * 描述：<p>用户角色关系</p>
 * 创建时间：2016年2月14日
 */
@Entity
@Table(name="sys_user_role")
public class UserRoleRelation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3484137024517599597L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name="USER_ID")
	private Integer userId;
	
	@Column(name="ROLE_ID")
	private Integer roleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
