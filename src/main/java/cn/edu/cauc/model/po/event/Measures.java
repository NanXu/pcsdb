package cn.edu.cauc.model.po.event;

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
 * 描述：<p>采取措施</p>
 * 创建时间：2016年10月5日
 */
@Entity
@Table(name="ev_measures")
public class Measures implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2379602222836164362L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "ID", unique = true, nullable = false)
	private Integer id; //主键ID
	
	@Column(name="EVENT_ID", nullable = false)
	private Integer eventId; //事故ID
	
	@Column(name="PREVENT")
	private String prevent; //预防/紧急措施
	
	@Column(name="CONTROLS")
	private String controls; //其他机组控制措施
	
	@Column(name="MAINTAIN")
	private String maintain; //维修措施
	
	@Column(name="REMARK", length=2000)
	private String remark; //备注

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

	public String getPrevent() {
		return prevent;
	}

	public void setPrevent(String prevent) {
		this.prevent = prevent;
	}

	public String getControls() {
		return controls;
	}

	public void setControls(String controls) {
		this.controls = controls;
	}

	public String getMaintain() {
		return maintain;
	}

	public void setMaintain(String maintain) {
		this.maintain = maintain;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
