package com.lris.ain.back.entities;

import java.sql.Timestamp;

public class B1091 {

	private int f01;  //ID
    
    private int f02;  //发送类型 1 验证码 0 通知
    
    private String f03;  //短信内容
    
    private String f04;  //手机号,多个以,号隔开，每次最多发送260个
    
    private int f05;  //手机号数量
    
    private Timestamp f06;  //发送时间
    
    private String f07;  //短信返回参数
    
    private String f08;  //短信反馈发送结果
    
    private String f09;  //发送接口 JC、CL
    
    private String f10; //msgid

	public int getF01() {
		return f01;
	}

	public void setF01(int f01) {
		this.f01 = f01;
	}

	public int getF02() {
		return f02;
	}

	public void setF02(int f02) {
		this.f02 = f02;
	}

	public String getF03() {
		return f03;
	}

	public void setF03(String f03) {
		this.f03 = f03;
	}

	public String getF04() {
		return f04;
	}

	public void setF04(String f04) {
		this.f04 = f04;
	}

	public int getF05() {
		return f05;
	}

	public void setF05(int f05) {
		this.f05 = f05;
	}

	public Timestamp getF06() {
		return f06;
	}

	public void setF06(Timestamp f06) {
		this.f06 = f06;
	}

	public String getF07() {
		return f07;
	}

	public void setF07(String f07) {
		this.f07 = f07;
	}

	public String getF08() {
		return f08;
	}

	public void setF08(String f08) {
		this.f08 = f08;
	}

	public String getF09() {
		return f09;
	}

	public void setF09(String f09) {
		this.f09 = f09;
	}

	public String getF10() {
		return f10;
	}

	public void setF10(String f10) {
		this.f10 = f10;
	}
    
}
