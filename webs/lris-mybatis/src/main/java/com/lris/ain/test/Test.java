package com.lris.ain.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lris.ain.config.SessionFactoryConfiguration;
import com.lris.ain.dao.BlogMapper;
import com.lris.ain.enties.Blog;

public class Test {

	public static void main(String[] args) {
		SqlSessionFactory sqlSessionFactory = SessionFactoryConfiguration.getSqlSessionFactory();
		System.out.println("sqlSessionFactory:"+sqlSessionFactory);
		SqlSession session = sqlSessionFactory.openSession();
		System.out.println("session:"+session);
		
		try {
			  Blog blog = session.selectOne("com.lris.ain.dao.BlogMapper.selectBlog", 101);
			  System.out.println(JSONObject.toJSONString(blog));
			} finally {
			  session.close();
			}
		
		try {
			  BlogMapper mapper1 = session.getMapper(BlogMapper.class);
			  Blog blog1 = mapper1.selectBlog(101);
			  System.out.println(JSONObject.toJSONString(blog1));
			} finally {
			  session.close();
			}
	}
}
