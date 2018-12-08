package cn.edu.cauc.model.po.system;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 作者：徐楠
 *
 * 描述：<p>系统用户信息</p>
 * 创建时间：2016年2月10日
 */
@Entity
@Table(name="sys_user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5988396449412163315L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;	//主键ID
	
	@Column(name="LOGIN_NAME")
	private String loginName;	//系统登录名
	
	@Column(name="USER_NAME")
	private String username;	//用户姓名
	
	@Column(name="PASSWORD")
	private String password;	//用户密码
	
	@Column(name="PHONE_NUMBER", length=32)
	private String phoneNumber;	//电话号码
	
	@Column(name="SEX", length=1)
	private String sex;			//性别
	
	@Column(name="IS_ADMIN")
	private String isAdmin;		//是否为超级管理员
	
	@Column(name="STATUS")
	private String status;		//用户状态：1：正常；0：已冻结；
	
	@Column(name="CREATE_DATE")
	private Date createDate;	//创建时间
	
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	

}
