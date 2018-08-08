package com.lris.ain.core.utils;

import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

/**
 * 验证码工具
 * @author tom
 *
 */
public class CaptchaUtil {
	
	private final static SecureRandom random = new SecureRandom();
		
	/**
	 * 生成验证码
	 * @param len
	 * @return
	 */
	public static String genCode(int len,int type){
	    char[] value = new char[len];
	    for (int i = 0; i < len; i++){
	      value[i] = (char)(random.nextInt(10) + 48);
	    }
	    return new String(value);
	}
	
	/**
	 * 设置下发验证码
	 * @param request
	 * @param keyprefix 前缀
	 * @param codelen 验证码长度
	 * @param tel 手机号或者邮箱
	 * @param overtimes 超时时间 秒
	 * @return
	 */
	public static String setCode(HttpServletRequest request,String keyprefix,int codelen,String tel,int overtimes){
		String code = null;
		try{
			code = CaptchaUtil.genCode(6,0);
			long times = new DateTime().getMillis()+overtimes*1000L;
			request.getSession().setAttribute(keyprefix+tel, code+","+times);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return code;
	}
	
	/**
	 * 验证下发的验证码
	 * 只要验证一次就失效，不管正确或者错误
	 * @param request
	 * @param keyprefix
	 * @param code
	 * @param tel
	 * @return
	 */
	public static boolean checkCode(HttpServletRequest request,String keyprefix,String code,String tel){
		try{
			long times = new DateTime().getMillis();
			String realcode = (String)request.getSession().getAttribute(keyprefix+tel);
			if(StringUtils.isBlank(realcode)){
				return false;
			}
			String[] cols = realcode.split(",");
			if(cols==null||cols.length!=2){
				return false;
			}
			long realtime = Long.parseLong(cols[1]);
			if(times>realtime || !code.equals(cols[0])){
				return false;
			}
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			//request.getSession().removeAttribute(keyprefix+tel);
		}finally{
			request.getSession().removeAttribute(keyprefix+tel);
		}
		return false;
	}
	
}
