package cn.edu.cauc.model.po.event;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 作者： 徐楠
 *
 * 描述：<p>污染物信息</p>
 * 创建时间：2016年10月5日
 */
@Entity
@Table(name="ev_pollutant")
public class Pollutant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8975732894185543569L;

	@GenericGenerator(name = "generator", strategy = "increment")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "ID", unique = true, nullable = false)
	private Integer id; //主键ID
	
	@Column(name="EVENT_ID", nullable = false)
	private Integer eventId; //事件信息ID
	
	@Column(name="SOURCE")
	private String source; //污染物来源
	
	@Column(name="RELATE_TO_SYSTEM")
	private String relateToSystem; //涉及系统
	
	@Column(name="BUG_LOCATION")
	private String bugLocation; //故障位置
	
	@Column(name="BUG_MODEL")
	private String bugModel; //故障模式
	
	@Transient
	private String sourceName;
	
	@Transient
	private String bugModelName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getRelateToSystem() {
		return relateToSystem;
	}

	public void setRelateToSystem(String relateToSystem) {
		this.relateToSystem = relateToSystem;
	}

	public String getBugLocation() {
		return bugLocation;
	}

	public void setBugLocation(String bugLocation) {
		this.bugLocation = bugLocation;
	}

	public String getBugModel() {
		return bugModel;
	}

	public void setBugModel(String bugModel) {
		this.bugModel = bugModel;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getBugModelName() {
		return bugModelName;
	}

	public void setBugModelName(String bugModelName) {
		this.bugModelName = bugModelName;
	}
	
}
