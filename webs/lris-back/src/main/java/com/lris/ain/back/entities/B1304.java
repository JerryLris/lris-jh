package com.lris.ain.back.entities;

import java.sql.Date;
import java.sql.Timestamp;


/** 
 * 后台请求日志
 */
public class B1304 {

    private long f01;  //
    
    private String f02;  //请求IP
    
    private int f03;  //用户ID
    
    private Timestamp f04;  //请求时间
    
    private Date f05;  //请求日期
    
    private String f06;  //请求url
    
    private String f07;  //refer
    
    private String f08;  //请求方式GET、POST
    
    private int f09;  //是否ajax请求
    
    private String f10;  //访问系统
    
    private String f11;  //agent
    
    private String f12;  //source
    
	public void setF01(long F01){
		this.f01 = F01;
	}
	
	public void setF02(String F02){
		this.f02 = F02;
	}
	
	public void setF03(int F03){
		this.f03 = F03;
	}
	
	public void setF04(Timestamp F04){
		this.f04 = F04;
	}
	
	public void setF05(Date F05){
		this.f05 = F05;
	}
	
	public void setF06(String F06){
		this.f06 = F06;
	}
	
	public void setF07(String F07){
		this.f07 = F07;
	}
	
	public void setF08(String F08){
		this.f08 = F08;
	}
	
	public void setF09(int F09){
		this.f09 = F09;
	}
	
	public void setF10(String F10){
		this.f10 = F10;
	}
	
	public void setF11(String F11){
		this.f11 = F11;
	}
	
	public void setF12(String F12){
		this.f12 = F12;
	}
	
	public long getF01(){
		return this.f01;
	}
	
	public String getF02(){
		return this.f02;
	}
	
	public int getF03(){
		return this.f03;
	}
	
	public Timestamp getF04(){
		return this.f04;
	}
	
	public Date getF05(){
		return this.f05;
	}
	
	public String getF06(){
		return this.f06;
	}
	
	public String getF07(){
		return this.f07;
	}
	
	public String getF08(){
		return this.f08;
	}
	
	public int getF09(){
		return this.f09;
	}
	
	public String getF10(){
		return this.f10;
	}
	
	public String getF11(){
		return this.f11;
	}
	
	public String getF12(){
		return this.f12;
	}
	
}