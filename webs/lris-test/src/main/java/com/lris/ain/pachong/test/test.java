package com.lris.ain.pachong.test;

import java.util.Set;

import com.lris.ain.pachong.core.Pachong;

public class test {

	public static void main(String[] args) throws Exception {
		
		pachong();
		pachong();
	}
	
	public static void pachong() throws Exception {
		Pachong p = new Pachong();
		String regex = "[a-zA-z]+://www.ugirls.com/Models/[^\\s]*.html";
		for (int i=2;i<10;i++){
			String url = "https://www.ugirls.com/Content/Page-"+ i +".html";
			Set<String> set = p.getDowmLoadImageUrl(url, regex);
			System.out.println("本网页下:"+url+"有"+set.size()+"个网页");
            String regex2 = "[a-zA-z]+://img.ugirls.tv/uploads/magazine/content[^\\s]*.jpg";
            for (String ul :set){
            	p.doDowmLoadImageUrl(ul, regex2);
            }
		}
	}
}
