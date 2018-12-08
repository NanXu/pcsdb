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
 * 描述：<p>菜单</p>
 * 创建时间：2016年2月13日
 */
@Entity
@Table(name="sys_menu")
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2429452055320049795L;

	@GenericGenerator(name = "generator", strategy = "increment")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;		//主键ID
	
	@Column(name="PARENT_ID")
	private Integer parentId;//父ID
	
	@Column(name="NAME")
	private String name;	//菜单名称
	
	@Column(name="URL")
	private String url;		//URL
	
	@Column(name="ICON")
	private String icon;	//图标
	
	@Column(name="ORDER_BY")
	private Integer order;	//排序

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
