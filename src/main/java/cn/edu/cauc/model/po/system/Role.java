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
 * 描述：<p>TODO</p>
 * 创建时间：2016年2月14日
 */
@Entity
@Table(name="sys_role")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3141802442160805328L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;		//主键ID
	
	@Column(name="NAME")
	private String name;	//角色名称
	
	@Column(name="DESCRIPTION")
	private String description;//描述信息

	@Column(name="CREATE_DATE")
	private Date createDate; //创建时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
