package cn.edu.cauc.model.po.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ntsb_findings")
public class NtsbFindings implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -234596201108344006L;

	@GenericGenerator(name = "generator", strategy = "assigned")
 	@Id
 	@GeneratedValue(generator = "generator")
	@Column(name="ev_id")
	private String eventId; //事故ID
	
	@Column(name="category_no")
	private String categoryNo;//污染物来源
	
	@Column(name="subcategory_no")
	private String subCategoryNo;//污染物来源
	
	@Column(name="section_no")
	private String sectionNo;//涉及系统
	
	@Column(name="subsection_no")
	private String subSectionNo;//涉及系统
	
	@Column(name="modifier_no")
	private String modifierNo;//系统故障模式	

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getSubCategoryNo() {
		return subCategoryNo;
	}

	public void setSubCategoryNo(String subCategoryNo) {
		this.subCategoryNo = subCategoryNo;
	}

	public String getSectionNo() {
		return sectionNo;
	}

	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}

	public String getSubSectionNo() {
		return subSectionNo;
	}

	public void setSubSectionNo(String subSectionNo) {
		this.subSectionNo = subSectionNo;
	}

	public String getModifierNo() {
		return modifierNo;
	}

	public void setModifierNo(String modifierNo) {
		this.modifierNo = modifierNo;
	}
	
}
