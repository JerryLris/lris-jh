package com.lris.ain.back.sys.vo;

import java.math.BigDecimal;
import java.util.List;

public class PlantData {
	
	/**
	 * 分期成功总金额（元） 数据总汇
	 */
	private BigDecimal totalSuccessMoney;
	
	/**
	 * 合同金额（元）  数据总汇
	 */
	private BigDecimal totalContractMoney;
	
	/**
	 * 分期成功笔数 数据总汇
	 */
	private int totalSuccessCount;
	
	/**
	 * 分期失败总金额（元） 数据总汇
	 */
	private BigDecimal totalFailMoney;
	
	/**
	 * 分期失败笔数 数据总汇
	 */
	private int totalFailCount;
	
	/**
	 * 总分期金额（元）数据总汇
	 */
	private BigDecimal totalMoney;
	
	/**
	 * 总分期笔数 数据总汇
	 */
	private int totalCount;
	
	/**
	 * 申请分期总人数 数据总汇
	 */
	private int totalPerson;
	
	/**
	 * 总分期成功率：分期成功笔数/分期总笔数 数据总汇
	 */
	private BigDecimal totalSuccessRate;
	
	/**
	 * 分期成功总金额（元）当月分期数据
	 */
	private BigDecimal monthSuccessMoney;
	
	/**
	 *合同金额（元）当月分期数据 
	 */
	private BigDecimal monthContractMoney;
	
	/**
	 * 分期成功笔数 当月分期数据
	 */
	private int monthSuccessCount;
	
	/**
	 * 分期失败总金额（元） 当月分期数据
	 */
	private BigDecimal monthFailMoney;
	
	/**
	 * 分期失败笔数 当月分期数据
	 */
	private int monthFailCount;
	
	/**
	 * 总分期金额（元）当月分期数据
	 */
	private BigDecimal monthMoney;
	
	/**
	 * 总分期笔数 当月分期数据
	 */
	private int monthCount;
	
	/**
	 * 申请分期总人数 当月分期数据
	 */
	private int monthPerson;
	
	/**
	 * 总分期成功率：分期成功笔数/分期总笔数 当月分期数据
	 */
	private BigDecimal monthSuccessRate;
	
	/**
	 * 过去12个月的每月的贷款成功金额
	 */
	private List<BigDecimal> eachmonthsuccess;
	
	/**
	 * 过去12个月的每月的贷款失败金额
	 */
	private List<BigDecimal> eachmonthfail;
	
	/**
	 * 过去12个月的每月的贷款总金额
	 */
	private List<BigDecimal> eachmonthtotal;
	
	/**
	 * 过去12个月月份
	 */
	private String months;
	
	/**
	 * 过去12个月每月应还金额
	 */
	private List<BigDecimal> shouldrepays;
	
	/**
	 * 过去12个月每月已还金额
	 */
	private List<BigDecimal> repayeds ;
	
	/**
	 * 过去12个月每月还款率
	 */
	private List<BigDecimal> repayrates ;

	/**
	 * 所有单的平均审核用时
	 */
	private String allcosttime;
	
	/**
	 * 当月单的平均审核用时
	 */
	private String monthcosttime;
	
	/**
	 * 所有的审核异常单数
	 */
	private int allunusual;
	
	/**
	 * 当月的审核异常单数
	 */
	private int monthunusual;
	
	public BigDecimal getTotalSuccessMoney() {
		return totalSuccessMoney;
	}

	public void setTotalSuccessMoney(BigDecimal totalSuccessMoney) {
		this.totalSuccessMoney = totalSuccessMoney;
	}

	public int getTotalSuccessCount() {
		return totalSuccessCount;
	}

	public void setTotalSuccessCount(int totalSuccessCount) {
		this.totalSuccessCount = totalSuccessCount;
	}

	public BigDecimal getTotalFailMoney() {
		return totalFailMoney;
	}

	public void setTotalFailMoney(BigDecimal totalFailMoney) {
		this.totalFailMoney = totalFailMoney;
	}

	public int getTotalFailCount() {
		return totalFailCount;
	}

	public void setTotalFailCount(int totalFailCount) {
		this.totalFailCount = totalFailCount;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPerson() {
		return totalPerson;
	}

	public void setTotalPerson(int totalPerson) {
		this.totalPerson = totalPerson;
	}

	public BigDecimal getTotalSuccessRate() {
		return totalSuccessRate;
	}

	public void setTotalSuccessRate(BigDecimal totalSuccessRate) {
		this.totalSuccessRate = totalSuccessRate;
	}

	public BigDecimal getMonthSuccessMoney() {
		return monthSuccessMoney;
	}

	public void setMonthSuccessMoney(BigDecimal monthSuccessMoney) {
		this.monthSuccessMoney = monthSuccessMoney;
	}

	public int getMonthSuccessCount() {
		return monthSuccessCount;
	}

	public void setMonthSuccessCount(int monthSuccessCount) {
		this.monthSuccessCount = monthSuccessCount;
	}

	public BigDecimal getMonthFailMoney() {
		return monthFailMoney;
	}

	public void setMonthFailMoney(BigDecimal monthFailMoney) {
		this.monthFailMoney = monthFailMoney;
	}

	public int getMonthFailCount() {
		return monthFailCount;
	}

	public void setMonthFailCount(int monthFailCount) {
		this.monthFailCount = monthFailCount;
	}

	public BigDecimal getMonthMoney() {
		return monthMoney;
	}

	public void setMonthMoney(BigDecimal monthMoney) {
		this.monthMoney = monthMoney;
	}

	public int getMonthCount() {
		return monthCount;
	}

	public void setMonthCount(int monthCount) {
		this.monthCount = monthCount;
	}

	public int getMonthPerson() {
		return monthPerson;
	}

	public void setMonthPerson(int monthPerson) {
		this.monthPerson = monthPerson;
	}

	public BigDecimal getMonthSuccessRate() {
		return monthSuccessRate;
	}

	public void setMonthSuccessRate(BigDecimal monthSuccessRate) {
		this.monthSuccessRate = monthSuccessRate;
	}


	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

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

	public String getAllcosttime() {
		return allcosttime;
	}

	public void setAllcosttime(String allcosttime) {
		this.allcosttime = allcosttime;
	}

	public String getMonthcosttime() {
		return monthcosttime;
	}

	public void setMonthcosttime(String monthcosttime) {
		this.monthcosttime = monthcosttime;
	}

	public int getAllunusual() {
		return allunusual;
	}

	public void setAllunusual(int allunusual) {
		this.allunusual = allunusual;
	}

	public int getMonthunusual() {
		return monthunusual;
	}

	public void setMonthunusual(int monthunusual) {
		this.monthunusual = monthunusual;
	}

	public BigDecimal getTotalContractMoney() {
		return totalContractMoney;
	}

	public void setTotalContractMoney(BigDecimal totalContractMoney) {
		this.totalContractMoney = totalContractMoney;
	}

	public BigDecimal getMonthContractMoney() {
		return monthContractMoney;
	}

	public void setMonthContractMoney(BigDecimal monthContractMoney) {
		this.monthContractMoney = monthContractMoney;
	}

	
}
