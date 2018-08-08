package com.lris.ain.back.entities;


/** 
 * 系统功能
 */
public class B1002 {

    private String f01;  //ID
    
    private String f02;  //父功能代号
    
    private String f03;  //功能代号
    
    private String f04;  //功能名称
    
    private int f05;  //级别 1 菜单 2操作
    
	public void setF01(String F01){
		this.f01 = F01;
	}
	
	public void setF02(String F02){
		this.f02 = F02;
	}
	
	public void setF03(String F03){
		this.f03 = F03;
	}
	
	public void setF04(String F04){
		this.f04 = F04;
	}
	
	public void setF05(int F05){
		this.f05 = F05;
	}
	
	public String getF01(){
		return this.f01;
	}
	
	public String getF02(){
		return this.f02;
	}
	
	public String getF03(){
		return this.f03;
	}
	
	public String getF04(){
		return this.f04;
	}
	
	public int getF05(){
		return this.f05;
	}
	
}