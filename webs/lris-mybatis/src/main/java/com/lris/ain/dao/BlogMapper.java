package com.lris.ain.dao;

import org.apache.ibatis.annotations.Select;

import com.lris.ain.enties.Blog;

public interface BlogMapper {

	Blog selectBlog(int id);
	
	@Select("SELECT * FROM tb_blog WHERE id = #{id}")
	Blog selectBlogB(int id);
}
