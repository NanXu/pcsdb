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
 * 描述：<p>伤亡情况</p>
 * 创建时间：2016年10月7日
 */
@Entity
@Table(name="ev_casualties")
public class Casualties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8386733448751699567L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name="EVENT_ID", nullable = false)
	private Integer eventId; 
	
	@Column(name="CREW_FATAL")
	private Integer crewFatal; //机组死亡
	
	@Column(name="CREW_SERIOUS")
	private Integer crewSerious; //机组重伤
	
	@Column(name="CREW_MINOR")
	private Integer crewMinor;//机组轻伤
	
	@Column(name="PASSENGER_FATAL")
	private Integer passengerFatal; //乘客死亡
	
	@Column(name="PASSENGER_SERIOUS")
	private Integer passengerSerious; //乘客重伤
	
	@Column(name="PASSENGER_MINOR")
	private Integer passengerMinor;//乘客轻伤
	
	@Column(name="FATALITIES")
	private String fatalities;
	
	@Column(name="INJURIES")
	private String injuries;

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

	public Integer getCrewFatal() {
		return crewFatal;
	}

	public void setCrewFatal(Integer crewFatal) {
		this.crewFatal = crewFatal;
	}

	public Integer getCrewSerious() {
		return crewSerious;
	}

	public void setCrewSerious(Integer crewSerious) {
		this.crewSerious = crewSerious;
	}

	public Integer getCrewMinor() {
		return crewMinor;
	}

	public void setCrewMinor(Integer crewMinor) {
		this.crewMinor = crewMinor;
	}

	public Integer getPassengerFatal() {
		return passengerFatal;
	}

	public void setPassengerFatal(Integer passengerFatal) {
		this.passengerFatal = passengerFatal;
	}

	public Integer getPassengerSerious() {
		return passengerSerious;
	}

	public void setPassengerSerious(Integer passengerSerious) {
		this.passengerSerious = passengerSerious;
	}

	public Integer getPassengerMinor() {
		return passengerMinor;
	}

	public void setPassengerMinor(Integer passengerMinor) {
		this.passengerMinor = passengerMinor;
	}

	public String getFatalities() {
		return fatalities;
	}

	public void setFatalities(String fatalities) {
		this.fatalities = fatalities;
	}

	public String getInjuries() {
		return injuries;
	}

	public void setInjuries(String injuries) {
		this.injuries = injuries;
	}
	
}
