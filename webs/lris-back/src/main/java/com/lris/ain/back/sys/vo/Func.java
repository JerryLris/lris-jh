package com.lris.ain.back.sys.vo;

public class Func {

	private String id;
	
	private String pkey;
	
	private String key;
	
	private String name;
	
	private int level;

	public Func(String id, String pkey, String key, String name, int level) {
		super();
		this.id = id;
		this.pkey = pkey;
		this.key = key;
		this.name = name;
		this.level = level;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
