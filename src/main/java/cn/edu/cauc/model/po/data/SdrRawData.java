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
 * 描述：<p>SDR数据库原始数据（部件编码=1,民航数据，烟雾类）</p>
 * 
 * 创建时间：2016年10月19日
 */
@Entity
@Table(name="sdr_raw_data")
public class SdrRawData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6782859672029392565L;
	
	@GenericGenerator(name = "generator", strategy = "assigned")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "id", unique = true, nullable = false)
	private String id; //运营控制编号c18
	
	@Column()
	private String seqNumber; //日期序列号c5

	@Column()
	private String receiveDate; //接收报告日期c10
	
	@Column()
	private Integer seq; //按日期的序列号c12
	
	@Column()
	private String regionCode; //地区编码 c14
	
	@Column()
	private String occurrenceDate;//事件发生日期c25
	
	@Column()
	private String ataCode; //ATA编码c40
	
	@Column()
	private String partNumber; //制造商部件号c90
	
	@Column()
	private String partName; //部件描述名称c100
	
	@Column()
	private String partManufacturerName;//部件制造商名称c110
	
	@Column()
	private String partManufacturerModal; //部件制造商型号c120
	
	@Column()
	private String aircraftManufacturerName;//飞机制造商名称c130
	
	@Column()
	private String aircraftManufacturerCode;//飞机制造商型号c150
	
	//@Column()
	//private String faaCode;//FAA的飞机识别码c140
	
	@Column()
	private String aircraftRegion; //飞机所在管辖区c160
	
	@Column()
	private String engineManufacturerName;//发动机制造商名称c170
	
	@Column()
	private String engineManufacturerModel;//发动机型号c190
	
	@Column()
	private String failedPartDesc; //失效部分的描述c260
	
	@Column()
	private String operatorCode; //运输机营运编码c300
	
	@Column()
	private String preCodeFirst;//事件1-预防程序编号c310a
	
	@Column()
	private String preCodeSecond;//事件3-预防程序编号c310c
	
	@Column()
	private String preDesc;//事件1-预防程序描述c314a
	
	@Column()
	private String measureRemark;//采取措施备注c320a
	
	@Column()
	private String natureConditionDesc;//事件1-自然环境描述c324a
	
	@Column()
	private String operationCode; //运行阶段编码c330
	
	@Column()
	private String aircraftRegistrationNumber; //注册号c390
	
	@Column()
	private String operationText; //运行阶段描述c332
	
	@Column()
	private String numberOfEngines;//发动机数量c608
	
	@Column(name="remark", length=4000)
	private String remark;//备注c510a
	
	@Column(name="maintain", length=4000)
	private String maintain;//维修措施c510b
	
	/**
	 * 数据入库状态：
	 * -|待入库：0
	 * -|成功入库：1
	 * -|入库失败：2
	 * 每次入库查询待入库的数据进行入库，成功标识成功状态失败标识失败状体，同时记录好成功和失败日志，
	 * 失败数据修改保存之后标识状态为待入库。
	 */
	@Column(name="status", length=1, nullable=false)
	private String status = "0";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(String seqNumber) {
		this.seqNumber = seqNumber;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getOccurrenceDate() {
		return occurrenceDate;
	}

	public void setOccurrenceDate(String occurrenceDate) {
		this.occurrenceDate = occurrenceDate;
	}

	public String getAtaCode() {
		return ataCode;
	}

	public void setAtaCode(String ataCode) {
		this.ataCode = ataCode;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getPartManufacturerName() {
		return partManufacturerName;
	}

	public void setPartManufacturerName(String partManufacturerName) {
		this.partManufacturerName = partManufacturerName;
	}

	public String getPartManufacturerModal() {
		return partManufacturerModal;
	}

	public void setPartManufacturerModal(String partManufacturerModal) {
		this.partManufacturerModal = partManufacturerModal;
	}

	public String getAircraftManufacturerName() {
		return aircraftManufacturerName;
	}

	public void setAircraftManufacturerName(String aircraftManufacturerName) {
		this.aircraftManufacturerName = aircraftManufacturerName;
	}

	public String getAircraftManufacturerCode() {
		return aircraftManufacturerCode;
	}

	public void setAircraftManufacturerCode(String aircraftManufacturerCode) {
		this.aircraftManufacturerCode = aircraftManufacturerCode;
	}

	public String getAircraftRegion() {
		return aircraftRegion;
	}

	public void setAircraftRegion(String aircraftRegion) {
		this.aircraftRegion = aircraftRegion;
	}

	public String getEngineManufacturerName() {
		return engineManufacturerName;
	}

	public void setEngineManufacturerName(String engineManufacturerName) {
		this.engineManufacturerName = engineManufacturerName;
	}

	public String getFailedPartDesc() {
		return failedPartDesc;
	}

	public void setFailedPartDesc(String failedPartDesc) {
		this.failedPartDesc = failedPartDesc;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getPreCodeFirst() {
		return preCodeFirst;
	}

	public void setPreCodeFirst(String preCodeFirst) {
		this.preCodeFirst = preCodeFirst;
	}

	public String getPreCodeSecond() {
		return preCodeSecond;
	}

	public void setPreCodeSecond(String preCodeSecond) {
		this.preCodeSecond = preCodeSecond;
	}

	public String getPreDesc() {
		return preDesc;
	}

	public void setPreDesc(String preDesc) {
		this.preDesc = preDesc;
	}

	public String getNatureConditionDesc() {
		return natureConditionDesc;
	}

	public void setNatureConditionDesc(String natureConditionDesc) {
		this.natureConditionDesc = natureConditionDesc;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getOperationText() {
		return operationText;
	}

	public void setOperationText(String operationText) {
		this.operationText = operationText;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAircraftRegistrationNumber() {
		return aircraftRegistrationNumber;
	}

	public void setAircraftRegistrationNumber(String aircraftRegistrationNumber) {
		this.aircraftRegistrationNumber = aircraftRegistrationNumber;
	}
	
	public String getEngineManufacturerModel() {
		return engineManufacturerModel;
	}

	public void setEngineManufacturerModel(String engineManufacturerModel) {
		this.engineManufacturerModel = engineManufacturerModel;
	}

	public String getNumberOfEngines() {
		return numberOfEngines;
	}

	public void setNumberOfEngines(String numberOfEngines) {
		this.numberOfEngines = numberOfEngines;
	}

	public String getMaintain() {
		return maintain;
	}

	public void setMaintain(String maintain) {
		this.maintain = maintain;
	}
	
	public String getMeasureRemark() {
		return measureRemark;
	}

	public void setMeasureRemark(String measureRemark) {
		this.measureRemark = measureRemark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
