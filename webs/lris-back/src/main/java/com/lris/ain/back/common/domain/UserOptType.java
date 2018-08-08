package com.lris.ain.back.common.domain;

import org.springframework.util.StringUtils;


/**
 * 用户操作类型
 * @author tom
 *
 */
public enum UserOptType {

	/**
	 * 注册验证用户名是否存在
	 */
	REG_CHECK_USERNAME("注册验证用户名是否存在"),
	/**
	 * 注册下发手机验证码
	 * 包含检测手机号是否存在
	 */
	REG_SEND_TELCODE("注册下发验证手机验证码"),
	/**
	 * 注册基本信息
	 */
	REG_BASE_INFO("注册基本信息"),
	/**
	 * 实名认证
	 */
	REG_CHECK_REALNAME("实名认证"),
	/**
	 * 登录
	 */
	LOGIN("登录"),
	/**
	 * 找回密码下发手机验证码
	 */
	FIND_LOGINPWD_SEND_TELCODE("找回密码下发手机验证码"),
	/**
	 * 通过手机验证码重置登录密码
	 */
	RESET_LOGINPWD_BY_TELCODE("通过手机验证码重置登录密码"),
	/**
	 * 登录后修改登录密码
	 */
	MODIFY_LOGINPWD("登录后修改登录密码"),
	/**
	 * 修改手机号下发原手机验证码
	 * 
	 */
	MODIFY_TEL_SEND_OLD_TELCODE("修改手机号下发原手机验证码"),
	/**
	 * 修改手机号下发新手机验证码
	 * 
	 */
	MODIFY_TEL_SEND_NEW_TELCODE("修改手机号下发新手机验证码"),
	/**
	 * 修改手机号验证新手机号
	 * 
	 */
	MODIFY_TEL_CHECK_NEW_TELCODE("修改手机号验证新手机号");
	
	protected final String cname;

    private UserOptType(String cname){
        this.cname = cname;
    }
    
    public String getCname() {
        return cname;
    }
   
    public static final UserOptType parse(String value) {
        if(StringUtils.isEmpty(value)){
            return null;
        }
        try{
            return UserOptType.valueOf(value);
        }catch(Throwable t){
            return null;
        }
    }
}
