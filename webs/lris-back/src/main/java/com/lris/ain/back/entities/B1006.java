package com.lris.ain.back.entities;

import java.sql.Timestamp;

import com.lris.ain.back.enums.UseState;


/** 
 * 系统账户
 */
public class B1006 {

    private int f01;  //ID
    
    private String f02;  //用户名
    
    private String f03;  //密码
    
    private String f04;  //真实姓名
    
    private String f05;  //状态,QY:启用;TY:停用
    
    private Timestamp f06;  //注册时间
    
    private Timestamp f07;  //最后登陆时间
    
    private String f08;  //最后登陆IP
    
    private String f09;  //手机号
	
    private int f10; //关联的销售ID
    
    private int f11; //是否是审单人员
    
    private String f12;//座机分机号
    
    private int  f13;
    
	public int getF13() {
		return f13;
	}

	public void setF13(int f13) {
		this.f13 = f13;
	}

	public void setF01(int F01){
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
	
	public void setF05(String F05){
		this.f05 = F05;
	}
	
	public void setF06(Timestamp F06){
		this.f06 = F06;
	}
	
	public void setF07(Timestamp F07){
		this.f07 = F07;
	}
	
	public void setF08(String F08){
		this.f08 = F08;
	}
	
	public void setF09(String F09){
		this.f09 = F09;
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
	
	public String getF04(){
		return this.f04;
	}
	
	public String getF05(){
		return this.f05;
	}
	
	public Timestamp getF06(){
		return this.f06;
	}
	
	public Timestamp getF07(){
		return this.f07;
	}
	
	public String getF08(){
		return this.f08;
	}
	
	public String getF09(){
		return this.f09;
	}
	
	public String getF05cname(){
		return UseState.valueOf(this.f05).getCname();
	}

	public int getF10() {
		return f10;
	}

	public void setF10(int f10) {
		this.f10 = f10;
	}

	public int getF11() {
		return f11;
	}

	public void setF11(int f11) {
		this.f11 = f11;
	}

	public String getF12() {
		return f12;
	}

	public void setF12(String F12) {
		this.f12 = F12;
	}

	@Override
	public String toString() {
		return "B1006 [f01=" + f01 + ", f02=" + f02 + ", f03=" + f03 + ", f04=" + f04 + ", f05=" + f05 + ", f06=" + f06
				+ ", f07=" + f07 + ", f08=" + f08 + ", f09=" + f09 + ", f10=" + f10 + ", f11=" + f11 + ", f12=" + f12
				+ ", f13=" + f13 + "]";
	}
	
	
}