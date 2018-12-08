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
 * 描述：<p>AIDS 原始数据对象</p>
 * 创建时间：2016年9月19日
 */
@Entity
@Table(name="aids_raw_data")
public class AidsRawData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7043371862777527655L;
	
	@GenericGenerator(name = "generator", strategy = "assigned")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "reportNumber", unique = true, nullable = false)
	private String reportNumber;
	
	@Column(name="localEventDate", length=128)
	private String localEventDate;
	
	@Column(name="eventCity", length=128)
	private String eventCity;
	
	@Column(name="eventState", length=128)
	private String eventState;
	
	@Column(name="eventAirport", length=128)
	private String eventAirport;
	
	@Column()
	private String aircraftDamage;
	
	@Column(length=64)
	private String eventType;
	
	@Column()
	private String flightPhase;
	
	@Column()
	private String aircraftMake;
	
	@Column()
	private String aircraftModel;
	
	@Column()
	private String aircraftSeries;
	
	@Column()
	private String operator;
	
	@Column()
	private String primaryFlightType;
	
	@Column()
	private String flightConductCode;
	
	@Column()
	private String flightPlanFiledCode;
	
	@Column()
	private String aircraftRegistrationNbr;
	
	@Column()
	private String totalFatalities;
	
	@Column()
	private String totalInjuries;
	
	@Column()
	private String aircraftEngineMake;
	
	@Column()
	private String aircraftEngineModel;
	
	@Column()
	private String engineGroupCode;
	
	@Column()
	private String nbrofEngines;
	
	@Column()
	private String picCertificateType;
	
	@Column()
	private String picFlightTimeTotalHrs;
	
	@Column()
	private String picFlightTimeTotalMakeModel;
	
	@Column()
	private String picFlightTime90DTotalTime;
	
	@Column()
	private String picFlightTime90DTotalMakeModel;
	
	@Column(name="eventRemarks", length=4000)
	private String eventRemarks;
	
//	/**
//	 * 原始数据状态  0：未入库；1：已入库
//	 */
//	@Column(name="status", length=1, nullable=false)
//	private String status="0";
	
	/**
	 * 数据准备状态 0：未准备好(待修正状态);1：准备完成
	 * 默认装载完成数据为 0
	 * 爬虫爬取完成后状态修改为 1
	 * modify:
	 * 0:导入CSV完成
	 * 1：爬取数据完成<---|
	 * 2：入库完成-------|(入库失败转到上一个状态)
	 */
	@Column(name="ready", length=1, nullable=false)
	private String ready = "0";
	
	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	public String getEventState() {
		return eventState;
	}

	public void setEventState(String eventState) {
		this.eventState = eventState;
	}

	public String getLocalEventDate() {
		return localEventDate;
	}

	public void setLocalEventDate(String localEventDate) {
		this.localEventDate = localEventDate;
	}

	public String getAircraftDamage() {
		return aircraftDamage;
	}

	public void setAircraftDamage(String aircraftDamage) {
		this.aircraftDamage = aircraftDamage;
	}

	public String getEventCity() {
		return eventCity;
	}

	public void setEventCity(String eventCity) {
		this.eventCity = eventCity;
	}

	public String getEventAirport() {
		return eventAirport;
	}

	public void setEventAirport(String eventAirport) {
		this.eventAirport = eventAirport;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}


	public String getFlightPhase() {
		return flightPhase;
	}

	public void setFlightPhase(String flightPhase) {
		this.flightPhase = flightPhase;
	}

	public String getAircraftMake() {
		return aircraftMake;
	}

	public void setAircraftMake(String aircraftMake) {
		this.aircraftMake = aircraftMake;
	}

	public String getAircraftModel() {
		return aircraftModel;
	}

	public void setAircraftModel(String aircraftModel) {
		this.aircraftModel = aircraftModel;
	}

	public String getAircraftSeries() {
		return aircraftSeries;
	}

	public void setAircraftSeries(String aircraftSeries) {
		this.aircraftSeries = aircraftSeries;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getPrimaryFlightType() {
		return primaryFlightType;
	}

	public void setPrimaryFlightType(String primaryFlightType) {
		this.primaryFlightType = primaryFlightType;
	}

	public String getFlightConductCode() {
		return flightConductCode;
	}

	public void setFlightConductCode(String flightConductCode) {
		this.flightConductCode = flightConductCode;
	}

	public String getFlightPlanFiledCode() {
		return flightPlanFiledCode;
	}

	public void setFlightPlanFiledCode(String flightPlanFiledCode) {
		this.flightPlanFiledCode = flightPlanFiledCode;
	}

	public String getAircraftRegistrationNbr() {
		return aircraftRegistrationNbr;
	}

	public void setAircraftRegistrationNbr(String aircraftRegistrationNbr) {
		this.aircraftRegistrationNbr = aircraftRegistrationNbr;
	}

	public String getTotalFatalities() {
		return totalFatalities;
	}

	public void setTotalFatalities(String totalFatalities) {
		this.totalFatalities = totalFatalities;
	}

	public String getTotalInjuries() {
		return totalInjuries;
	}

	public void setTotalInjuries(String totalInjuries) {
		this.totalInjuries = totalInjuries;
	}

	public String getAircraftEngineMake() {
		return aircraftEngineMake;
	}

	public void setAircraftEngineMake(String aircraftEngineMake) {
		this.aircraftEngineMake = aircraftEngineMake;
	}

	public String getAircraftEngineModel() {
		return aircraftEngineModel;
	}

	public void setAircraftEngineModel(String aircraftEngineModel) {
		this.aircraftEngineModel = aircraftEngineModel;
	}

	public String getEngineGroupCode() {
		return engineGroupCode;
	}

	public void setEngineGroupCode(String engineGroupCode) {
		this.engineGroupCode = engineGroupCode;
	}

	public String getNbrofEngines() {
		return nbrofEngines;
	}

	public void setNbrofEngines(String nbrofEngines) {
		this.nbrofEngines = nbrofEngines;
	}

	public String getPicCertificateType() {
		return picCertificateType;
	}

	public void setPicCertificateType(String picCertificateType) {
		this.picCertificateType = picCertificateType;
	}

	public String getPicFlightTimeTotalHrs() {
		return picFlightTimeTotalHrs;
	}

	public void setPicFlightTimeTotalHrs(String picFlightTimeTotalHrs) {
		this.picFlightTimeTotalHrs = picFlightTimeTotalHrs;
	}

	public String getPicFlightTimeTotalMakeModel() {
		return picFlightTimeTotalMakeModel;
	}

	public void setPicFlightTimeTotalMakeModel(String picFlightTimeTotalMakeModel) {
		this.picFlightTimeTotalMakeModel = picFlightTimeTotalMakeModel;
	}

	public String getPicFlightTime90DTotalTime() {
		return picFlightTime90DTotalTime;
	}

	public void setPicFlightTime90DTotalTime(String picFlightTime90DTotalTime) {
		this.picFlightTime90DTotalTime = picFlightTime90DTotalTime;
	}

	public String getPicFlightTime90DTotalMakeModel() {
		return picFlightTime90DTotalMakeModel;
	}

	public void setPicFlightTime90DTotalMakeModel(
			String picFlightTime90DTotalMakeModel) {
		this.picFlightTime90DTotalMakeModel = picFlightTime90DTotalMakeModel;
	}

	public String getEventRemarks() {
		return eventRemarks;
	}

	public void setEventRemarks(String eventRemarks) {
		this.eventRemarks = eventRemarks;
	}

	public String getReady() {
		return ready;
	}

	public void setReady(String ready) {
		this.ready = ready;
	}
	
}
