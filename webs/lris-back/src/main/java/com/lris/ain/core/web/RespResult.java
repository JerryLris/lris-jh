package com.lris.ain.core.web;

import com.google.gson.Gson;

public class RespResult {

	public static String SUCESS = "00";
	public static String FAIL_AUTH = "01"; //无权限
	public static String FAIL_PARAM = "02"; //参数错误
	public static String FAIL_SERVICE = "03"; //业务逻辑错误
	public static String FAIL_OTHER = "04"; //其他未知错误
	public static String FAIL_FAST_FREQ = "05"; //频率过快
	
	private String rs = "00"; // 00表示成功  其他失败，常用01
	private String msg ;  //失败原因
	private Object obj ; //数据
	
	public RespResult(){}
	
	public RespResult(String rs,String msg){
		this.rs = rs;
		this.msg = msg;
	}
	
	public String getRs() {
		return rs;
	}
	
	public void setRs(String rs) {
		this.rs = rs;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}
