package com.lris.ain.back.entities;

import java.sql.Timestamp;


/** 
 * 后台用户登录或者退出日志
 */
public class B1306 {

    private long f01;  //
    
    private int f02;  //用户ID
    
    private String f03;  //请求IP
    
    private Timestamp f04;  //请求时间
    
    private int f05;  //结果 0 成功 1失败
    
    private String f06;  //失败原因
    
    private int f07;  //来源 1 PC 2 mobile 3 微信  4 androidAPP 5 IOSAPP 
    
    private int f08;  //0 登录 1退出
    
	public void setF01(long F01){
		this.f01 = F01;
	}
	
	public void setF02(int F02){
		this.f02 = F02;
	}
	
	public void setF03(String F03){
		this.f03 = F03;
	}
	
	public void setF04(Timestamp F04){
		this.f04 = F04;
	}
	
	public void setF05(int F05){
		this.f05 = F05;
	}
	
	public void setF06(String F06){
		this.f06 = F06;
	}
	
	public void setF07(int F07){
		this.f07 = F07;
	}
	
	public void setF08(int F08){
		this.f08 = F08;
	}
	
	public long getF01(){
		return this.f01;
	}
	
	public int getF02(){
		return this.f02;
	}
	
	public String getF03(){
		return this.f03;
	}
	
	public Timestamp getF04(){
		return this.f04;
	}
	
	public int getF05(){
		return this.f05;
	}
	
	public String getF06(){
		return this.f06;
	}
	
	public int getF07(){
		return this.f07;
	}
	
	public int getF08(){
		return this.f08;
	}
	
}