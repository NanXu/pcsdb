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
 * 描述：<p>飞机信息</p>
 * 创建时间：2016年10月5日
 */
@Entity
@Table(name="ev_plane")
public class Plane implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9134718474467856779L;

	@GenericGenerator(name = "generator", strategy = "increment")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "ID", unique = true, nullable = false)
	private Integer id; //主键ID
	
	@Column(name="EVENT_ID", nullable = false)
	private Integer eventId; //事故ID
	
	@Column(name="AIRCRAFT_MODEL")
	private String aircraftModel; //飞机型号
	
	@Column(name="AIRCRAFT_MAKE")
	private String aircraftMake; //飞机制造商
	
	@Column(name="REGISTRATION_NUMBER")
	private String registrationNumber; //注册号
	
	@Column(name="ENGINE_MANUFACTER")
	private String engineManufactuer; //发动机制造商
	
	@Column(name="ENGINE_MODEL")
	private String engineModel; //发动机型号
	
	@Column(name="NUMBER_OF_ENGINES")
	private String numberOfEngines; //发动机数量
	
	@Column(name="OPERATOR")
	private String operator; //运营商
	
	@Column(name="RUN_RULES")
	private String runRules; //运行规章
	
	@Column(name="RUN_TOTAL_HOURS")
	private String runTotalHours; //总飞行小时数
	
	@Column(name="MAINTAIN_TYPE")
	private String maintainType; //维修类型
	
	@Column(name="MAINTAIN_HOURS")
	private String maintainHours; //维修后运行时间

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

	public String getAircraftModel() {
		return aircraftModel;
	}

	public void setAircraftModel(String aircraftModel) {
		this.aircraftModel = aircraftModel;
	}

	public String getAircraftMake() {
		return aircraftMake;
	}

	public void setAircraftMake(String aircraftMake) {
		this.aircraftMake = aircraftMake;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getEngineManufactuer() {
		return engineManufactuer;
	}

	public void setEngineManufactuer(String engineManufactuer) {
		this.engineManufactuer = engineManufactuer;
	}

	public String getEngineModel() {
		return engineModel;
	}

	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}

	public String getNumberOfEngines() {
		return numberOfEngines;
	}

	public void setNumberOfEngines(String numberOfEngines) {
		this.numberOfEngines = numberOfEngines;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRunRules() {
		return runRules;
	}

	public void setRunRules(String runRules) {
		this.runRules = runRules;
	}

	public String getRunTotalHours() {
		return runTotalHours;
	}

	public void setRunTotalHours(String runTotalHours) {
		this.runTotalHours = runTotalHours;
	}

	public String getMaintainType() {
		return maintainType;
	}

	public void setMaintainType(String maintainType) {
		this.maintainType = maintainType;
	}

	public String getMaintainHours() {
		return maintainHours;
	}

	public void setMaintainHours(String maintainHours) {
		this.maintainHours = maintainHours;
	}
	
	
}
