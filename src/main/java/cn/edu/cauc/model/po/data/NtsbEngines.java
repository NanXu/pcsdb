package cn.edu.cauc.model.po.data;

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
 * 描述：<p>TODO</p>
 * 创建时间：2016年11月20日
 */
@Entity
@Table(name="ntsb_engines")
public class NtsbEngines implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1374181299965273591L;

	@GenericGenerator(name = "generator", strategy = "assigned")
 	@Id
 	@GeneratedValue(generator = "generator")
	@Column(name="ev_id")
	private String eventId;//事故ID
	
	@Column(name="eng_mfgr")
	private String engMfgr;//发动机制造商
	
	@Column(name="eng_model")
	private String engModel;//发动机型号
	
	@Column(name="eng_no")
	private Integer engNumber;//发动机数量

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEngMfgr() {
		return engMfgr;
	}

	public void setEngMfgr(String engMfgr) {
		this.engMfgr = engMfgr;
	}

	public String getEngModel() {
		return engModel;
	}

	public void setEngModel(String engModel) {
		this.engModel = engModel;
	}

	public Integer getEngNumber() {
		return engNumber;
	}

	public void setEngNumber(Integer engNumber) {
		this.engNumber = engNumber;
	}
	
}
