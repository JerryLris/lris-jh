package com.lris.ain.pachong.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadParam {

	public static Properties loadPropertyFromClasspath() {
		Properties p = new Properties();
		InputStream is = null;
		
		try {
			is = LoadParam.class.getClassLoader().getResourceAsStream("config.properties");
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return p;
	}
	
	public static String getPropertiesByName(String name) {
		
		Properties properties = LoadParam.loadPropertyFromClasspath();
		
		if(properties==null) {
			System.out.println("初始化config.properties失败！");
		}
		return properties.getProperty(name);
	}
	
	public static void main(String[] args) {
		
		System.out.println(LoadParam.class);
		System.out.println(LoadParam.class.getClassLoader());
		System.out.println(LoadParam.class.getClassLoader().getResource("config.properties"));
		
		Properties properties = LoadParam.loadPropertyFromClasspath();
		System.out.println(properties);
	}
}
