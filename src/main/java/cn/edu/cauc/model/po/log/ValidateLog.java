package cn.edu.cauc.model.po.log;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 作者： 徐楠
 *
 * 描述：<p>数据校验日志</p>
 * 创建时间：2016年10月4日
 */
@Entity
@Table(name="log_validate")
public class ValidateLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7873748934834289008L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;	//主键ID
	
	@Column(name="DATA_ID")
	private String dataID; //入库数据唯一标识
	
	@Column(name="STATUS")
	private String status; //入库状态  0:失败; 1：成功
	
	@Column(name="CREATE_TIME")
	private Date cteateTime; //入库时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataID() {
		return dataID;
	}

	public void setDataID(String dataID) {
		this.dataID = dataID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCteateTime() {
		return cteateTime;
	}

	public void setCteateTime(Date cteateTime) {
		this.cteateTime = cteateTime;
	}

	
}
