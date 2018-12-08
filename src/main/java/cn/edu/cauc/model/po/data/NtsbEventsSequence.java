package cn.edu.cauc.model.po.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ntsb_events_sequence")
public class NtsbEventsSequence implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2788966791309945813L;

	@GenericGenerator(name = "generator", strategy = "assigned")
 	@Id
 	@GeneratedValue(generator = "generator")
	@Column(name="ev_id")
	private String eventId;//事故ID
	
	@Column(name="phase_no")
	private String phaseNo;//飞行阶段
	
	@Column(name="eventsoe_no")
	private String eventsoeNo;//造成的后果

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getPhaseNo() {
		return phaseNo;
	}

	public void setPhaseNo(String phaseNo) {
		this.phaseNo = phaseNo;
	}

	public String getEventsoeNo() {
		return eventsoeNo;
	}

	public void setEventsoeNo(String eventsoeNo) {
		this.eventsoeNo = eventsoeNo;
	}
	
	
}
