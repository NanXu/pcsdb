package cn.edu.cauc.util;

import java.util.List;

public class Pager<T> {  
	
    private List<T> entityList;//分页对象集合  
    private int totalCounts;//总条数  
    private int currentPage;//当前页数  
    private int nextPage;   //下一页  
    private int prePage;    //上一页  
    private int pageCount;  //总页数  
	public List<T> getEntityList() {
		return entityList;
	}
	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}
	public int getTotalCounts() {
		return totalCounts;
	}
	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

    
}  