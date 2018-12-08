package cn.edu.cauc.model.po.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ntsb_injury")
public class NtsbInjury implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9082467561144759341L;

	@GenericGenerator(name = "generator", strategy = "assigned")
 	@Id
 	@GeneratedValue(generator = "generator")
	@Column(name="ev_id")
	private String eventId;//事故ID
	
	@Column(name="inj_person_category")
	private String injPersonCategory;//伤亡人员类型
	
	@Column(name="inj_person_count")
	private Integer injPersonCount;//伤亡人数
	
	@Column(name="injury_level")
	private String level;//伤亡级别

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getInjPersonCategory() {
		return injPersonCategory;
	}

	public void setInjPersonCategory(String injPersonCategory) {
		this.injPersonCategory = injPersonCategory;
	}

	public Integer getInjPersonCount() {
		return injPersonCount;
	}

	public void setInjPersonCount(Integer injPersonCount) {
		this.injPersonCount = injPersonCount;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	
}
