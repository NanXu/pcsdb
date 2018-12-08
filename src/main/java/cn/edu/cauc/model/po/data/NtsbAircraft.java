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
 * 创建时间：2016年11月17日
 */
@Entity
@Table(name="ntsb_aircraft")
public class NtsbAircraft implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7871588369076923468L;
	
	@GenericGenerator(name = "generator", strategy = "assigned")
 	@Id
 	@GeneratedValue(generator = "generator")
	@Column(name="ev_id")
	private String eventId;//事故ID
	
	@Column(name="ntsb_no")
	private String ntsbNo;//报告编号
	
	@Column(name="dprt_apt_id")
	private String dprtAptId;//最后起飞机场
	
	@Column(name="dest_apt_id")
	private String destAptId;//目的地机场
	
	@Column(name="damage")
	private String damage;//飞机损害程度
	
	@Column(name="acft_fire")
	private String acftFire;//异常现象
	
	@Column(name="acft_model")
	private String acftModel;//飞机型号
	
	@Column(name="acft_make")
	private String acftMake;//飞机制造商
	
	@Column(name="regis_no")
	private String registNo;//注册号
	
	@Column(name="num_eng")
	private Integer engineNumber;//发动机数量
	
	@Column(name="oper_name")
	private String operatorName;//运营商
	
	@Column(name="far_part")
	private String farPart;//遵照的运行章程
	
	@Column(name="afm_hrs")
	private Float armHrs;//总飞行的小时数
	
	@Column(name="type_last_insp")
	private String typeLastInsp;//上次维修类型
	
	@Column(name="afm_hrs_last_insp")
	private Float armHrsLastInsp;//上次维修后飞行的小时数
	
	@Column(name="cert_max_gr_wt")
	private Integer certMaxGrWt;//最大起飞重量
	
	@Column(name="evacuation")
	private String evacuation;//预防或紧急措施

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getNtsbNo() {
		return ntsbNo;
	}

	public void setNtsbNo(String ntsbNo) {
		this.ntsbNo = ntsbNo;
	}

	public String getDprtAptId() {
		return dprtAptId;
	}

	public void setDprtAptId(String dprtAptId) {
		this.dprtAptId = dprtAptId;
	}

	public String getDestAptId() {
		return destAptId;
	}

	public void setDestAptId(String destAptId) {
		this.destAptId = destAptId;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public String getAcftFire() {
		return acftFire;
	}

	public void setAcftFire(String acftFire) {
		this.acftFire = acftFire;
	}

	public String getAcftModel() {
		return acftModel;
	}

	public void setAcftModel(String acftModel) {
		this.acftModel = acftModel;
	}

	public String getAcftMake() {
		return acftMake;
	}

	public void setAcftMake(String acftMake) {
		this.acftMake = acftMake;
	}

	public String getRegistNo() {
		return registNo;
	}

	public void setRegistNo(String registNo) {
		this.registNo = registNo;
	}

	public Integer getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(Integer engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getFarPart() {
		return farPart;
	}

	public void setFarPart(String farPart) {
		this.farPart = farPart;
	}

	public Float getArmHrs() {
		return armHrs;
	}

	public void setArmHrs(Float armHrs) {
		this.armHrs = armHrs;
	}

	public String getTypeLastInsp() {
		return typeLastInsp;
	}

	public void setTypeLastInsp(String typeLastInsp) {
		this.typeLastInsp = typeLastInsp;
	}

	public Float getArmHrsLastInsp() {
		return armHrsLastInsp;
	}

	public void setArmHrsLastInsp(Float armHrsLastInsp) {
		this.armHrsLastInsp = armHrsLastInsp;
	}

	public Integer getCertMaxGrWt() {
		return certMaxGrWt;
	}

	public void setCertMaxGrWt(Integer certMaxGrWt) {
		this.certMaxGrWt = certMaxGrWt;
	}

	public String getEvacuation() {
		return evacuation;
	}

	public void setEvacuation(String evacuation) {
		this.evacuation = evacuation;
	}

}
