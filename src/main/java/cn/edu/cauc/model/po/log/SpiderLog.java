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
 * 描述：<p>爬虫日志</p>
 * 创建时间：2016年10月10日
 */
@Entity
@Table(name="log_spider")
public class SpiderLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6050583348448743786L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "ID", unique = true, nullable = false)
	private Integer id; //主键ID
	
	@Column(name="SOURCE_ID")
	private String sourceId; //源数据ID
	
	@Column(name="STATUS")
	private String status; //抓取状态0：失败;1：成功
	
	@Column(name="CONTENT", length=4000)
	private String content; //内容信息
	
	@Column(name="CREATE_DATE")
	private Date createDate; //创建时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
