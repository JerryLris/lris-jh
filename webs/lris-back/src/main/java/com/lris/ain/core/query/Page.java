package com.lris.ain.core.query;

/**
 * 分页相关
 */
public class Page {

	/**
	 * 每页显示条数,-1表示查询所有
	 */
	private int pageSize = 10;
	
	/**
	 * 当前页码，从1开始
	 */
	private int pageIndex = 1;
	
	/**
	 * 总条数
	 */
	private int totalCount;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	public void setPageSize(int size){
		pageSize = size==0?10:size;
	}
	
	public int getPageSize(){
		return pageSize;
	}
	
	public void setPageIndex(int index){
		pageIndex = index<=0?1:index;
	}
	
	public int getPageIndex(){
		return pageIndex;
	}
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		if(this.totalCount%this.pageSize==0){
			return this.totalCount/this.pageSize;
		}else{
			return this.totalCount/this.pageSize+1;
		}
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
