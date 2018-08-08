package com.lris.ain.back.entities;


/** 
 * 系统角色权限
 */
public class B1004 {

    private int f01;  //角色ID
    
    private String f02;  //功能
    
	public void setF01(int F01){
		this.f01 = F01;
	}
	
	public void setF02(String F02){
		this.f02 = F02;
	}
	
	public int getF01(){
		return this.f01;
	}
	
	public String getF02(){
		return this.f02;
	}
	
}