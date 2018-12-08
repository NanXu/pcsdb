package cn.edu.cauc.model.vo;

import java.io.Serializable;

import cn.edu.cauc.model.po.event.Casualties;
import cn.edu.cauc.model.po.event.EventInfo;
import cn.edu.cauc.model.po.event.Measures;
import cn.edu.cauc.model.po.event.Plane;
import cn.edu.cauc.model.po.event.Pollutant;

/**
 * 作者： 徐楠
 *
 * 描述：<p>事件信息 表单提交Form</p>
 * 创建时间：2016年10月9日
 */
public class EventForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7408234451113543386L;

	private EventInfo event; //事件信息
	
	private Plane plane; //飞机信息
	
	private Casualties casualties; //伤亡信息
	
	private Pollutant pollutant; //污染物信息
	
	private Measures measures; //采取措施

	public EventInfo getEvent() {
		return event;
	}

	public void setEvent(EventInfo event) {
		this.event = event;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public Casualties getCasualties() {
		return casualties;
	}

	public void setCasualties(Casualties casualties) {
		this.casualties = casualties;
	}

	public Pollutant getPollutant() {
		return pollutant;
	}

	public void setPollutant(Pollutant pollutant) {
		this.pollutant = pollutant;
	}

	public Measures getMeasures() {
		return measures;
	}

	public void setMeasures(Measures measures) {
		this.measures = measures;
	}
	
	
}
