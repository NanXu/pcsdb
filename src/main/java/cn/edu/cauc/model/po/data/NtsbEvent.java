package cn.edu.cauc.model.po.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 作者： 徐楠
 *
 * 描述：<p>NTSB 事故信息</p>
 * 创建时间：2016年11月17日
 */
@Entity
@Table(name="ntsb_events")
public class NtsbEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4440038401687559335L;

	@GenericGenerator(name = "generator", strategy = "assigned")
 	@Id
 	@GeneratedValue(generator = "generator")
	@Column(name="ev_id")
	private String eventId; //事件编号
	
	@Column(name="ev_type")
	private String eventType; //事故类型
	
	@Column(name="ev_date")
	private Date localDate; //当地时间
	
	@Column(name="ev_time")
	private Integer localTime;//当地时间
	
	@Column(name="ev_city")
	private String city;//事故发生城市
	
	@Column(name="wx_cond_basic")
	private String wxCondBasic;//天气状况
	
	@Column(name="inj_tot_f")
	private Integer injuryTotalF;//死亡人数
	
	@Column(name="inj_tot_s")
	private Integer injuryTotalS;//重伤
	
	@Column(name="inj_tot_m")
	private Integer injuryTotalM;//轻伤
	
	@Column(name="inj_tot_n")
	private Integer injuryTotalN;//未受伤
	
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

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
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

	public Integer getLocalTime() {
		return localTime;
	}

	public void setLocalTime(Integer localTime) {
		this.localTime = localTime;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWxCondBasic() {
		return wxCondBasic;
	}

	public void setWxCondBasic(String wxCondBasic) {
		this.wxCondBasic = wxCondBasic;
	}

	public Integer getInjuryTotalF() {
		return injuryTotalF;
	}

	public void setInjuryTotalF(Integer injuryTotalF) {
		this.injuryTotalF = injuryTotalF;
	}

	public Integer getInjuryTotalS() {
		return injuryTotalS;
	}

	public void setInjuryTotalS(Integer injuryTotalS) {
		this.injuryTotalS = injuryTotalS;
	}

	public Integer getInjuryTotalM() {
		return injuryTotalM;
	}

	public void setInjuryTotalM(Integer injuryTotalM) {
		this.injuryTotalM = injuryTotalM;
	}

	public Integer getInjuryTotalN() {
		return injuryTotalN;
	}

	public void setInjuryTotalN(Integer injuryTotalN) {
		this.injuryTotalN = injuryTotalN;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
