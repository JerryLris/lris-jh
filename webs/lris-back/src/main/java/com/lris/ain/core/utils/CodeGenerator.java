package com.lris.ain.core.utils;

import java.security.SecureRandom;

public class CodeGenerator {
	
	private SecureRandom random = new SecureRandom();
	
	/**
	 * 生成6位验证码
	 * @return
	 */
	public String genCode(){
		int len = 6;
		char[] val = new char[len];
		for(int i=0;i<len;i++){
			val[i] = (char)(random.nextInt(10)+48);
		}
		return new String(val);
	}
}
