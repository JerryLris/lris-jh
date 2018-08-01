package com.lris.ain.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionFactoryConfiguration {
	
	private static SqlSessionFactory sqlSessionFactory;

	static {
		
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		
		return sqlSessionFactory;
	}
	
	public static void main(String[] args) {
		
		SqlSessionFactory sqlSessionFactory = SessionFactoryConfiguration.getSqlSessionFactory();
		System.out.println("sqlSessionFactory:"+sqlSessionFactory);
	}
	
}
