package cn.edu.cauc.model.po.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ntsb_narratives")
public class NtsbNarratives implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3100806630293636970L;
	
	@GenericGenerator(name = "generator", strategy = "assigned")
 	@Id
 	@GeneratedValue(generator = "generator")
	@Column(name="ev_id")
	private String eventId;//事故ID
	
	@Column(name="narr_accf")
	private String narrAccf;
	
	@Column(name="narr_cause")
	private String narrCause;
	
	@Column(name="narr_accp")
	private String narrAccp;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getNarrAccf() {
		return narrAccf;
	}

	public void setNarrAccf(String narrAccf) {
		this.narrAccf = narrAccf;
	}

	public String getNarrCause() {
		return narrCause;
	}

	public void setNarrCause(String narrCause) {
		this.narrCause = narrCause;
	}

	public String getNarrAccp() {
		return narrAccp;
	}

	public void setNarrAccp(String narrAccp) {
		this.narrAccp = narrAccp;
	}

}
