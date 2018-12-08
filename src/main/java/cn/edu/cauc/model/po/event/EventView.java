package cn.edu.cauc.model.po.event;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 作者： 徐楠
 *
 * 描述：<p>事故视图信息</p>
 * 创建时间：2017年1月7日
 */
@Entity
@Table(name="v_event_info")
public class EventView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4318122898450850180L;

	@GenericGenerator(name = "generator", strategy = "increment")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "ID", unique = true, nullable = false)
	private Integer id; //事件编号(规则：按照日期排序，同一日期的在后面依次按字母排序)
	
	@Column(name="SOURCE")
	private String source; //数据来源
	
	@Column(name="SOURCE_ID")
	private String sourceId; //源编号
	
	@Column(name="EVENT_TYPE")
	private String eventType; //事故类型
	
	@Column(name="LOCAL_DATE")
	private Date localDate; //当地日期
	
	@Column(name="LAST_DEPARTURE_POINT")
	private String lastDeparturePoint; //最后起飞地
	
	@Column(name="DESTINATION_LOCAL")
	private String destinationLocal; //目的地
	
	@Column(name="EVENT_LOCATION")
	private String eventLocation; //事件发生地
	
	@Column(name="WEATHER_CONDITIONS")
	private String weatherConditions; //天气状况
	
	@Column(name="AIRCRAFT_DAMAGE")
	private String aircraftDamage; //飞机损害程度
	
	@Column(name="PHASE_FLIGHT")
	private String phaseFlight; //飞行阶段
	
	@Column(name="EVENT_REMARKS", length=2000)
	private String eventRemarks; //事件描述
	
	@Column(name="REASON_REMARKS", length=2000)
	private String reasonRemarks; //原因描述
	
	@Column(name="STATUS")
	private String status;
	
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
	
	@Column(name="POLLUTANT_SOURCE")
	private String pullutantSource; //污染物来源
	
	@Column(name="RELATE_TO_SYSTEM")
	private String relateToSystem; //涉及系统
	
	@Column(name="BUG_LOCATION")
	private String bugLocation; //故障位置
	
	@Column(name="BUG_MODEL")
	private String bugModel; //故障模式
	
	@Column(name="PREVENT")
	private String prevent; //预防/紧急措施
	
	@Column(name="CONTROLS")
	private String controls; //其他机组控制措施
	
	@Column(name="MAINTAIN")
	private String maintain; //维修措施
	
	@Column(name="REMARK", length=2000)
	private String remark; //备注
	
	@Column(name="FATALITIES")
	private String fatalities;
	
	@Column(name="INJURIES")
	private String injuries;
	
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
	
	@Transient
	private String phaseDesc;
	
	@Transient
	private String weather;
	
	@Transient
	private String damage;
	
	@Transient
	private String sourceName;
	
	@Transient
	private String bugModelName;
	
	@Transient
	private String localDateStart;
	
	@Transient
	private String localDateEnd;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Date getLocalDate() {
		return localDate;
	}

	public void setLocalDate(Date localDate) {
		this.localDate = localDate;
	}

	public String getLastDeparturePoint() {
		return lastDeparturePoint;
	}

	public void setLastDeparturePoint(String lastDeparturePoint) {
		this.lastDeparturePoint = lastDeparturePoint;
	}

	public String getDestinationLocal() {
		return destinationLocal;
	}

	public void setDestinationLocal(String destinationLocal) {
		this.destinationLocal = destinationLocal;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getWeatherConditions() {
		return weatherConditions;
	}

	public void setWeatherConditions(String weatherConditions) {
		this.weatherConditions = weatherConditions;
	}

	public String getAircraftDamage() {
		return aircraftDamage;
	}

	public void setAircraftDamage(String aircraftDamage) {
		this.aircraftDamage = aircraftDamage;
	}

	public String getPhaseFlight() {
		return phaseFlight;
	}

	public void setPhaseFlight(String phaseFlight) {
		this.phaseFlight = phaseFlight;
	}

	public String getEventRemarks() {
		return eventRemarks;
	}

	public void setEventRemarks(String eventRemarks) {
		this.eventRemarks = eventRemarks;
	}

	public String getReasonRemarks() {
		return reasonRemarks;
	}

	public void setReasonRemarks(String reasonRemarks) {
		this.reasonRemarks = reasonRemarks;
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

	public String getPullutantSource() {
		return pullutantSource;
	}

	public void setPullutantSource(String pullutantSource) {
		this.pullutantSource = pullutantSource;
	}

	public String getRelateToSystem() {
		return relateToSystem;
	}

	public void setRelateToSystem(String relateToSystem) {
		this.relateToSystem = relateToSystem;
	}

	public String getBugLocation() {
		return bugLocation;
	}

	public void setBugLocation(String bugLocation) {
		this.bugLocation = bugLocation;
	}

	public String getBugModel() {
		return bugModel;
	}

	public void setBugModel(String bugModel) {
		this.bugModel = bugModel;
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

	public String getPhaseDesc() {
		return phaseDesc;
	}

	public void setPhaseDesc(String phaseDesc) {
		this.phaseDesc = phaseDesc;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
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

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getBugModelName() {
		return bugModelName;
	}

	public void setBugModelName(String bugModelName) {
		this.bugModelName = bugModelName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocalDateStart() {
		return localDateStart;
	}

	public void setLocalDateStart(String localDateStart) {
		this.localDateStart = localDateStart;
	}

	public String getLocalDateEnd() {
		return localDateEnd;
	}

	public void setLocalDateEnd(String localDateEnd) {
		this.localDateEnd = localDateEnd;
	}
	
	
}
