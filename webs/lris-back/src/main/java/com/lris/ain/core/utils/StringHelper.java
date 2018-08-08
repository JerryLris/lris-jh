package com.lris.ain.core.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.lris.ain.core.exception.ParamException;

public class StringHelper {
	/**
	 * 字符串限长输出
	 */
	static public String limit(String str, int length) {
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		if (str.length() <= length) {
			return str;
		}
		return str.substring(0, length) + "...";
	}

	/**
	 * 根据文件编码获取图片地址
	 */
	static public String decode(String code)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		String[] strs = code.split("-");
		if (strs == null || strs.length < 5) {
			throw new ParamException("附件编码格式有误");
		}
		String s = "/";
		sb.append(s).append("fileStore").append(s).append(Integer.parseInt(strs[3], 16)).append(s)
				.append(Integer.parseInt(strs[0], 16)).append(s)
				.append(Integer.parseInt(strs[1], 16)).append(s)
				.append(Integer.parseInt(strs[2], 16)).append(s)
				.append(Integer.parseInt(strs[4].split("\\.")[0], 16))
				.append(".").append(strs[4].split("\\.")[1]);
		return sb.toString();
	}
	
	/**
	 * 保存ueditor编辑器内容时转义特殊字符
	 */
	static public String htmlDecode(String code)
			throws Exception {
		return code
				.replaceAll("& lt;", "<")
				.replaceAll("& gt;", ">")
				.replaceAll("&nbsp;", " ")
				.replaceAll("br/", "/n")
				.replaceAll("& #40;", "(")
				.replaceAll("& #41;", ")")
				.replaceAll("&#39;", "'")
				.replaceAll("& #39;", "'");
	}
	
	/**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    
    /**
     * 替换中文
     * @param source
     * @param replacement
     * @return
     */
    public static String replaceChinese(String source, String replacement){  
        String reg = "[\u4e00-\u9fa5]";  
        Pattern pat = Pattern.compile(reg);    
        Matcher mat=pat.matcher(source);   
        String repickStr = mat.replaceAll(replacement);  
        return repickStr;  
    }
}
