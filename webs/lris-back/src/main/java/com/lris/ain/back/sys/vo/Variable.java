package com.lris.ain.back.sys.vo;

import com.lris.ain.core.utils.StringHelper;

public class Variable {

	private String key; // 关键值 F01
	
	private String val; // 变量值 F02

	private String type; // 类型 F03
	
	private String desc; // 变量名称F04

	public Variable() {

	}

	public Variable(String key, String desc, String val, String type) {
		super();
		this.key = key;
		this.desc = desc;
		this.val = val;
		this.type = type;
	}

	public String getKeyLimit() {
		return StringHelper.limit(key, 40);
	}

	public String getDescLimit() {
		return StringHelper.limit(desc, 15);
	}

	public String getValLimit() {
		return StringHelper.limit(val, 15);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
