package com.lris.ain.pachong.test;

import java.math.BigDecimal;

public class Testss {

	public static void main(String[] args) {
		//System.out.println(new BigDecimal("75.4949999999"));
		//System.out.println(new BigDecimal("75.4949999999").setScale(2, BigDecimal.ROUND_HALF_UP));
		
		BigDecimal aBigDecimal = new BigDecimal("0.015");
		BigDecimal money = new BigDecimal(5033);
		System.out.println(aBigDecimal.multiply(money));
		
	}
}
