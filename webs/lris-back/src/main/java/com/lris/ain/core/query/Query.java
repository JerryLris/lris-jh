package com.lris.ain.core.query;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 查询条件
 */
public class Query {

	/**
	 * 查询条件，为了简单，支持String和数字类型
	 * 可以把orderby的也放在这里面
	 * 数字只需要支持int,long,double,BigDecimal这几种即可
	 */
	private Map<String,String> filter;
	private Page page;
	
	public void setFilter(Map<String,String> filter){
		this.filter = filter;
	}
	
	public Map<String,String> getFilter(){
		return this.filter;
	}
	
	public void setPage(Page page){
		this.page = page;
	}
	
	public Page getPage(){
		return this.page;
	}
	
	public int getPageSize(){
		return page.getPageSize();
	}
	
	public int getPageIndex(){
		return page.getPageIndex();
	}
	
	public void setParam(String key,String value){
		this.filter.put(key, value);
	}
	/**
	 * 判断内容是否为空
	 * @param key
	 * @return
	 */
	public boolean hasStr(String key){
		return !StringUtils.isEmpty(filter.get(key));
	}
	
	/**
	 * 获取整数查询条件
	 * @param key
	 * @return
	 */
	public int getInt(String key){
		int IVal = 0;
		if(hasStr(key)){
			try{
				IVal = Integer.parseInt(getStr(key));
			}catch(Exception ex){}
		}
		return IVal;
	}
	
	/**
	 * 获取长整型查询条件
	 * @param key
	 * @return
	 */
	public long getLong(String key){
		long IVal = 0;
		if(hasStr(key)){
			try{
				IVal = Long.parseLong(getStr(key));
			}catch(Exception ex){}
		}
		return IVal;
	}
	
	/**
	 * 获取BigDecimal查询条件
	 * @param key
	 * @return
	 */
	public BigDecimal getNum(String key){
		BigDecimal val = new BigDecimal(0);
		if(hasStr(key)){
			try{
				val = new BigDecimal(getStr(key));
			}catch(Exception ex){}
		}
		return val;
	}
	
	/**
	 * 获取double型查询条件
	 * @param key
	 * @return
	 */
	public double getDbl(String key){
		double val = 0d;
		if(hasStr(key)){
			try{
				val = Double.parseDouble(getStr(key));
			}catch(Exception ex){}
		}
		return val;
	}
	
	/**
	 * 获取字符串查询条件
	 * @param key
	 * @return
	 */
	public String getStr(String key){
		return filter.get(key);
	}
}
