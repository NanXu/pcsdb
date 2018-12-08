package cn.edu.cauc.model.vo;

import java.util.List;

public class Page<T> {
	
	private int count;
	private List<T> list;
	
	public Page(){
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
