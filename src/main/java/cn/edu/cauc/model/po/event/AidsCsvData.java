package cn.edu.cauc.model.po.event;

import javax.persistence.Column;
import java.util.Date;

public class AidsCsvData {
    private String source; //数据来源
    private String sourceId; //源编号
    private String eventType; //事故类型
    private String localDate; //当地日期
    private Date localTime;	//当地时间
    private String lastDeparturePoint; //最后起飞地
    private String destinationLocal; //目的地
    private String eventLocation; //事件发生地
    private String weatherConditions; //天气状况
    private String aircraftDamage; //飞机损害程度
    private String phaseFlight; //飞行阶段
    private String eventRemarks; //事件描述
    private String reasonRemarks; //原因描述
    private String aircraftModel; //飞机型号
    private String aircraftMake; //飞机制造商
    private String registrationNumber; //注册号
    private String engineManufactuer; //发动机制造商
    private String engineModel; //发动机型号
    private String numberOfEngines; //发动机数量
    private String operator; //运营商
    private String runRules; //运行规章
    private String runTotalHours; //总飞行小时数
    private String maintainType; //维修类型
    private String maintainHours; //维修后运行时间
    private String pollutantSource; //污染物来源
    private String relateToSystem; //涉及系统
    private String bugLocation; //故障位置
    private String bugModel; //故障模式
    private String prevent; //预防/紧急措施
    private String controls; //其他机组控制措施
    private String maintain; //维修措施
    private String remark; //备注
    private Integer crewFatal; //机组死亡
    private Integer crewSerious; //机组重伤
    private Integer crewMinor;//机组轻伤
    private Integer passengerFatal; //乘客死亡
    private Integer passengerSerious; //乘客重伤
    private Integer passengerMinor;//乘客轻伤
    private String flightProperties;//飞行特性
    private String isWeatherFactor;//是否天气因素影响到人
    private String isAffectAircraftSystem;//是否影响到飞机系统
    private String isArtificialFactor;//是否存在人为因素

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

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
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

    public String getPollutantSource() {
        return pollutantSource;
    }

    public void setPollutantSource(String pollutantSource) {
        this.pollutantSource = pollutantSource;
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

    @Override
    public String toString() {
        return "AidsCsvData{" +
                "source='" + source + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", localDate='" + localDate + '\'' +
                ", localTime=" + localTime +
                ", lastDeparturePoint='" + lastDeparturePoint + '\'' +
                ", destinationLocal='" + destinationLocal + '\'' +
                ", eventLocation='" + eventLocation + '\'' +
                ", weatherConditions='" + weatherConditions + '\'' +
                ", aircraftDamage='" + aircraftDamage + '\'' +
                ", phaseFlight='" + phaseFlight + '\'' +
                ", eventRemarks='" + eventRemarks + '\'' +
                ", reasonRemarks='" + reasonRemarks + '\'' +
                ", aircraftModel='" + aircraftModel + '\'' +
                ", aircraftMake='" + aircraftMake + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", engineManufactuer='" + engineManufactuer + '\'' +
                ", engineModel='" + engineModel + '\'' +
                ", numberOfEngines='" + numberOfEngines + '\'' +
                ", operator='" + operator + '\'' +
                ", runRules='" + runRules + '\'' +
                ", runTotalHours='" + runTotalHours + '\'' +
                ", maintainType='" + maintainType + '\'' +
                ", maintainHours='" + maintainHours + '\'' +
                ", pollutantSource='" + pollutantSource + '\'' +
                ", relateToSystem='" + relateToSystem + '\'' +
                ", bugLocation='" + bugLocation + '\'' +
                ", bugModel='" + bugModel + '\'' +
                ", prevent='" + prevent + '\'' +
                ", controls='" + controls + '\'' +
                ", maintain='" + maintain + '\'' +
                ", remark='" + remark + '\'' +
                ", crewFatal=" + crewFatal +
                ", crewSerious=" + crewSerious +
                ", crewMinor=" + crewMinor +
                ", passengerFatal=" + passengerFatal +
                ", passengerSerious=" + passengerSerious +
                ", passengerMinor=" + passengerMinor +
                ", flightProperties='" + flightProperties + '\'' +
                ", isWeatherFactor='" + isWeatherFactor + '\'' +
                ", isAffectAircraftSystem='" + isAffectAircraftSystem + '\'' +
                ", isArtificialFactor='" + isArtificialFactor + '\'' +
                '}';
    }
}
