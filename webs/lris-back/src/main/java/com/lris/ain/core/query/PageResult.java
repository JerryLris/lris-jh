package com.lris.ain.core.query;

/**
 * 分页查询结果
 * @param <T>
 */
public class PageResult<T> {
	
	private Page page;
	private T[] items;

	public T[] getItems() {
		return items;
	}

	public void setItems(T[] items) {
		this.items = items;
	}
	
	public void setPage(Page page){
		this.page = page;
	}
	
	public Page getPage(){
		return this.page;
	}
}
