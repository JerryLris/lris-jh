package com.lris.ain.back.entities;

import java.sql.Timestamp;


/** 
 * 关键信息修改验证及操作日志
 */
public class B1302 {

    private long f01;  //
    
    private String f02;  //操作类型、方式及步骤 
    
    private String f03;  //验证依据 手机号
    
    private Timestamp f04;  //创建时间
    
    private String f05;  //请求IP
    
    private int f06;  //操作结果 0失败 1成功
    
    private String f07;  //历史数据
    
    private int f08;  //用户ID
    
    private String f09;  //agent
    
    private String f10;  //source
    
    private String f11;  //失败原因
    
	public void setF01(long F01){
		this.f01 = F01;
	}
	
	public void setF02(String F02){
		this.f02 = F02;
	}
	
	public void setF03(String F03){
		this.f03 = F03;
	}
	
	public void setF04(Timestamp F04){
		this.f04 = F04;
	}
	
	public void setF05(String F05){
		this.f05 = F05;
	}
	
	public void setF06(int F06){
		this.f06 = F06;
	}
	
	public void setF07(String F07){
		this.f07 = F07;
	}
	
	public void setF08(int F08){
		this.f08 = F08;
	}
	
	public void setF09(String F09){
		this.f09 = F09;
	}
	
	public void setF10(String F10){
		this.f10 = F10;
	}
	
	public void setF11(String F11){
		this.f11 = F11;
	}
	
	public long getF01(){
		return this.f01;
	}
	
	public String getF02(){
		return this.f02;
	}
	
	public String getF03(){
		return this.f03;
	}
	
	public Timestamp getF04(){
		return this.f04;
	}
	
	public String getF05(){
		return this.f05;
	}
	
	public int getF06(){
		return this.f06;
	}
	
	public String getF07(){
		return this.f07;
	}
	
	public int getF08(){
		return this.f08;
	}
	
	public String getF09(){
		return this.f09;
	}
	
	public String getF10(){
		return this.f10;
	}
	
	public String getF11(){
		return this.f11;
	}
	
}