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
 * 描述：<p>事件基本信息 </p>
 * 创建时间：2016年10月5日
 */
@Entity
@Table(name="ev_event_info")
public class EventInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7656874941112451809L;
	
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
	
	@Column(name="LOCAL_TIME")
	private Date localTime;	//当地时间
	
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
	
	@Column(name="DRAFT_USER")
	private String draftUser;
	
	@Column(name="DRAFT_TIME")
	private Date draftTime;
	
	@Column(name="APPROVE_USER")
	private String approveUser;
	
	@Column(name="APPROVE_TIME")
	private Date approveTime;

	@Column(name="flight_properties")
	private String flightProperties;//飞行特性

	@Column(name="is_weather_factor")
	private String isWeatherFactor;//是否天气因素影响到人

	@Column(name="is_affect_aircraft_system")
	private String isAffectAircraftSystem;//是否影响到飞机系统

	@Column(name="is_artificial_factor")
	private String isArtificialFactor;//是否存在人为因素
	
	@Column(name="STATUS", length=1)
	private String status; //状态0：起草;1：发布;2:审批不通过
	
	@Column(name="IS_DELETE", length=1)
	private String isDelete;
	
	@Transient
	private String phaseDesc;
	
	@Transient
	private String weather;
	
	@Transient
	private String damage;
	
	@Transient
	private String localDateStart;
	
	@Transient
	private String localDateEnd;
	
	//涉及系统 查询条件
	@Transient
	private String relateToSystem;
	
	//故障模式
	@Transient
	private String bugModel;
	
	@Transient
	private String prevent; //预防/紧急措施
	
	@Transient
	private String controls; //其他机组控制措施
	
	@Transient
	private String maintain; //维修措施

	public EventInfo() {
	}

	/**
	 * 构造函数
	 * 
	 * @param id
	 * @param source
	 * @param sourceId
	 * @param eventType
	 * @param localDate
	 * @param localTime
	 * @param lastDeparturePoint
	 * @param destinationLocal
	 * @param eventLocation
	 * @param weatherConditions
	 * @param aircraftDamage
	 * @param phaseFlight
	 * @param eventRemarks
	 * @param reasonRemarks
	 * @param relateToSystem
	 * @param bugModel
	 * @param prevent
	 * @param controls
	 * @param maintain
	 */
	public EventInfo(Integer id, String source, String sourceId,
			String eventType, Date localDate, Date localTime,
			String lastDeparturePoint, String destinationLocal,
			String eventLocation, String weatherConditions,
			String aircraftDamage, String phaseFlight, String eventRemarks,
			String reasonRemarks, String relateToSystem,
			String bugModel, String prevent, String controls, String maintain) {
		this.id = id;
		this.source = source;
		this.sourceId = sourceId;
		this.eventType = eventType;
		this.localDate = localDate;
		this.localTime = localTime;
		this.lastDeparturePoint = lastDeparturePoint;
		this.destinationLocal = destinationLocal;
		this.eventLocation = eventLocation;
		this.weatherConditions = weatherConditions;
		this.aircraftDamage = aircraftDamage;
		this.phaseFlight = phaseFlight;
		this.eventRemarks = eventRemarks;
		this.reasonRemarks = reasonRemarks;
		this.relateToSystem = relateToSystem;
		this.bugModel = bugModel;
		this.prevent = prevent;
		this.controls = controls;
		this.maintain = maintain;
	}

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

	public Date getLocalTime() {
		return localTime;
	}

	public void setLocalTime(Date localTime) {
		this.localTime = localTime;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDraftUser() {
		return draftUser;
	}

	public void setDraftUser(String draftUser) {
		this.draftUser = draftUser;
	}

	public Date getDraftTime() {
		return draftTime;
	}

	public void setDraftTime(Date draftTime) {
		this.draftTime = draftTime;
	}

	public String getApproveUser() {
		return approveUser;
	}

	public void setApproveUser(String approveUser) {
		this.approveUser = approveUser;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
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

	public String getRelateToSystem() {
		return relateToSystem;
	}

	public void setRelateToSystem(String relateToSystem) {
		this.relateToSystem = relateToSystem;
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

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getFlightProperties() {
		return flightProperties;
	}

	public void setFlightProperties(String flightProperties) {
		this.flightProperties = flightProperties;
	}

	public String getIsWeatherFactor() {
		return isWeatherFactor;
	}

	public void setIsWeatherFactor(String isWeatherFactor) {
		this.isWeatherFactor = isWeatherFactor;
	}

	public String getIsAffectAircraftSystem() {
		return isAffectAircraftSystem;
	}

	public void setIsAffectAircraftSystem(String isAffectAircraftSystem) {
		this.isAffectAircraftSystem = isAffectAircraftSystem;
	}

	public String getIsArtificialFactor() {
		return isArtificialFactor;
	}

	public void setIsArtificialFactor(String isArtificialFactor) {
		this.isArtificialFactor = isArtificialFactor;
	}
}
