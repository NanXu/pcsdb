package cn.edu.cauc.model.po.log;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 作者： 徐楠
 *
 * 描述：<p>数据校验失败详细日志</p>
 * 创建时间：2016年10月4日
 */
@Entity
@Table(name="log_validate_failed_detail")
public class ValidateFailedDetailLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5948483001182829384L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;	//主键ID
	
	@Column(name="VALIDATE_LOG_ID")
	private Integer validateLogId; //数据校验ID
	
	@Column(name="FIELD", length=64)
	private String field; //字段信息
	
	@Column(name="CONTENT", length=256)
	private String content; //错误描述

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getValidateLogId() {
		return validateLogId;
	}

	public void setValidateLogId(Integer validateLogId) {
		this.validateLogId = validateLogId;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
