package cn.edu.cauc.model.vo;

import java.io.Serializable;
import java.util.Date;

public class EventStatView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241076890889333752L;
	
	public String name;//名称
	private long total;//数量
	private String type;//类型
	private String startDate;
	private String endDate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
