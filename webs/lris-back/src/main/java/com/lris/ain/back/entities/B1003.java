package com.lris.ain.back.entities;

import java.sql.Timestamp;

import com.lris.ain.back.enums.UseState;

/** 
 * 系统角色
 */
public class B1003 {

    private int f01;  //ID
    
    private String f02;  //角色名称
    
    private String f03;  //角色描述
    
    private Timestamp f04;  //创建时间
    
    private String f05;  //创建人
    
    private String f06;  //状态,QY:启用;TY:停用
    
	
	public void setF01(int F01){
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
	
	public void setF06(String F06){
		this.f06 = F06;
	}
	
	public int getF01(){
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
	
	public String getF06(){
		return this.f06;
	}
	
	public String getF06cname(){
		return UseState.valueOf(this.f06).getCname();
	}
	
}