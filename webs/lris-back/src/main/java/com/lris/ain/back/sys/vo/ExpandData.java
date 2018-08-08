package com.lris.ain.back.sys.vo;

import java.math.BigDecimal;
import java.util.List;

public class ExpandData {
	/**
	 * 12个月的每月的贷款成功金额
	 */
	private List<BigDecimal> eachmonthsuccess;
	
	/**
	 * 12个月的每月的贷款失败金额
	 */
	private List<BigDecimal> eachmonthfail;
	
	/**
	 * 12个月的每月的贷款总金额
	 */
	private List<BigDecimal> eachmonthtotal;
	
	/**
	 * 12个月月份
	 */
	private String months;
	
	/**
	 * 12个月每月应还金额
	 */
	private List<BigDecimal> shouldrepays;
	
	/**
	 * 12个月每月已还金额
	 */
	private List<BigDecimal> repayeds ;
	
	/**
	 * 12个月每月还款率
	 */
	private List<BigDecimal> repayrates ;

	public List<BigDecimal> getEachmonthsuccess() {
		return eachmonthsuccess;
	}

	public void setEachmonthsuccess(List<BigDecimal> eachmonthsuccess) {
		this.eachmonthsuccess = eachmonthsuccess;
	}

	public List<BigDecimal> getEachmonthfail() {
		return eachmonthfail;
	}

	public void setEachmonthfail(List<BigDecimal> eachmonthfail) {
		this.eachmonthfail = eachmonthfail;
	}

	public List<BigDecimal> getEachmonthtotal() {
		return eachmonthtotal;
	}

	public void setEachmonthtotal(List<BigDecimal> eachmonthtotal) {
		this.eachmonthtotal = eachmonthtotal;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public List<BigDecimal> getShouldrepays() {
		return shouldrepays;
	}

	public void setShouldrepays(List<BigDecimal> shouldrepays) {
		this.shouldrepays = shouldrepays;
	}

	public List<BigDecimal> getRepayeds() {
		return repayeds;
	}

	public void setRepayeds(List<BigDecimal> repayeds) {
		this.repayeds = repayeds;
	}

	public List<BigDecimal> getRepayrates() {
		return repayrates;
	}

	public void setRepayrates(List<BigDecimal> repayrates) {
		this.repayrates = repayrates;
	}
	
	
}
