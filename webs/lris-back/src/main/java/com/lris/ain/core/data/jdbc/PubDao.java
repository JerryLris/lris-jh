package com.lris.ain.core.data.jdbc;

import java.util.Collection;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public abstract class PubDao extends JdbcDaoSupport{

	/**
	 * 根据key获取系统参数的值
	 * @param key
	 * @return
	 */
	public String getSysVal(String key) throws Exception{
		String sql = "select F02 from lris.B1001 where F01=?";
		String val = this.getJdbcTemplate().queryForObject(sql, String.class, key);
		return val;
	}
	
	public  <T> T singleResult(Collection<T> results){
		int size = (results != null ? results.size() : 0);
		if (size == 0) {
			return null;
		}
		return results.iterator().next();
	}
	
	
	public int getPTId() throws Exception{
		return 1;
	}
	
}
