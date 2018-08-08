package com.lris.ain.back.entities;

import java.sql.Timestamp;


/** 
 * 关键信息修改及操作日志
 */
public class B1305 {

    private long f01;  //
    
    private int f02;  //用户ID
    
    private Timestamp f03;  //操作时间
    
    private String f06;  //描述
    
	public void setF01(long F01){
		this.f01 = F01;
	}
	
	public void setF02(int F02){
		this.f02 = F02;
	}
	
	public void setF03(Timestamp F03){
		this.f03 = F03;
	}
	
	public void setF06(String F06){
		this.f06 = F06;
	}
	
	public long getF01(){
		return this.f01;
	}
	
	public int getF02(){
		return this.f02;
	}
	
	public Timestamp getF03(){
		return this.f03;
	}
	
	public String getF06(){
		return this.f06;
	}
	
}