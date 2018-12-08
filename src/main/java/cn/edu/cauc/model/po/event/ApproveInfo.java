package cn.edu.cauc.model.po.event;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ev_approve_info")
public class ApproveInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3349400275549230674L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name="EVENT_ID", nullable = false)
	private Integer eventId; //事故ID
	
	@Column(name="PASS")
	private String pass; //是否通过1：通过；0：不通过
	
	@Column(name="REMARK", length=2000)
	private String remark; //审批意见
	
	@Column(name="APPROVER")
	private String approver; //审批人
	
	@Column(name="APPROVE_DATE")
	private Date approveDate; //审批时间

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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	
}
