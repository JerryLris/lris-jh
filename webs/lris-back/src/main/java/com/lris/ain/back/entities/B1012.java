package com.lris.ain.back.entities;

import java.io.Serializable;
import java.util.Date;

public class B1012  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int F01; // 部门ID
	private String F02; // 部门名称
	private Date F03; // 更新时间
	private String F04; // 更新人
	private int F05; // 状态 0 启用 1 停用

	public int getF01() {
		return F01;
	}

	public void setF01(int f01) {
		F01 = f01;
	}

	public String getF02() {
		return F02;
	}

	public void setF02(String f02) {
		F02 = f02;
	}

	public Date getF03() {
		return F03;
	}

	public void setF03(Date f03) {
		F03 = f03;
	}

	public String getF04() {
		return F04;
	}

	public void setF04(String f04) {
		F04 = f04;
	}

	public int getF05() {
		return F05;
	}

	public void setF05(int f05) {
		F05 = f05;
	}

}
