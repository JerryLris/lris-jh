package com.lris.ain.core.variables;

import java.io.InputStreamReader;

import com.lris.ain.core.variables.VariableBean;
import com.lris.ain.core.variables.VariableTypeAnnotation;

@VariableTypeAnnotation(id = "SAFEAUTH", name = "操作验证及认证")
public enum SafeAuthVariable implements VariableBean{
	/**
	 * 同一IP注册验证用户名是否存在
	 */
	REG_CHECK_USERNAME_IP("同一IP注册验证用户名是否存在"){
		@Override
        public String getValue()
        {
            return "100";
        }
	},
	/**
	 * 同一IP注册手机号下发验证码
	 * 包含检测手机号是否存在，不管成功与否都算一次
	 */
	REG_SEND_TELCODE_IP("同一IP注册下发手机验证码"){
		@Override
        public String getValue()
        {
            return "20";
        }
	},
	/**
	 * 同一IP注册手机号下发验证码
	 * 包含检测手机号是否存在，成功下发算一次
	 */
	REG_SEND_TELCODE_TEL("同一手机号注册下发手机验证码"){
		@Override
        public String getValue()
        {
            return "3";
        }
	},
	/**
	 * 手机验证码下发有效期(秒)
	 * 
	 */
	SEND_TELCODE_LIMITTIME("手机验证码下发有效期(秒)"){
		@Override
        public String getValue()
        {
            return "180";
        }
	},
	/**
	 * 同一IP每天可允许登录密码错误次数
	 */
	LOGIN_ERROR_TIMES_IP("同一IP每天可允许登录密码错误次数"){
		@Override
        public String getValue()
        {
            return "20";
        }
	},
	/**
	 * 同一IP连续几次密码错误后需要验证码
	 */
	LOGIN_ERROR_TIMES_NEED_AUTHCODE_IP("同一IP连续几次密码错误后需要验证码"){
		@Override
        public String getValue()
        {
            return "10";
        }
	},
	/**
	 * 同一用户每天可允许登录密码错误次数
	 */
	LOGIN_ERROR_TIMES_USER("同一用户每天可允许登录密码错误次数"){
		@Override
        public String getValue()
        {
            return "5";
        }
	},
	/**
	 * 同一用户连续几次密码错误后需要验证码
	 */
	LOGIN_ERROR_TIMES_NEED_AUTHCODE_USER("同一用户连续几次密码错误后需要验证码"){
		@Override
        public String getValue()
        {
            return "3";
        }
	},
	/**
	 * 同一手机号可找回登录密码次数
	 */
	FIND_LOGINPWD_SEND_TELCODE_TEL("同一手机号可找回登录密码次数"){
		@Override
        public String getValue()
        {
            return "2";
        }
	},
	/**
	 * 同一IP可找回登录密码次数
	 */
	FIND_LOGINPWD_SEND_TELCODE_IP("同一IP可找回登录密码次数"){
		@Override
        public String getValue()
        {
            return "10";
        }
	},
	/**
	 * 同一IP可重置登录密码次数
	 */
	RESET_LOGINPWD_BY_TELCODE_IP("同一IP可重置登录密码次数"){
		@Override
        public String getValue()
        {
            return "10";
        }
	},
	/**
	 * 同一手机号可重置登录密码次数
	 */
	RESET_LOGINPWD_BY_TELCODE_TEL("同一手机号可重置登录密码次数"){
		@Override
        public String getValue()
        {
            return "2";
        }
	},
	/**
	 * 同一用户可修改登录密码次数
	 */
	MODIFY_LOGINPWD_USER("同一用户可修改登录密码次数"){
		@Override
        public String getValue()
        {
            return "3";
        }
	},
	/**
	 * 同一用户修改手机号下发原手机验证码
	 */
	MODIFY_TEL_SEND_OLD_TELCODE_USER("同一用户修改手机号下发原手机验证码"){
		@Override
        public String getValue()
        {
            return "2";
        }
	},
	/**
	 * 同一用户修改手机号下发新手机验证码
	 */
	MODIFY_TEL_SEND_NEW_TELCODE_USER("同一用户修改手机号下发新手机验证码"){
		@Override
        public String getValue()
        {
            return "2";
        }
	},
	/**
	 * 同一手机号修改手机号下发新手机验证码
	 */
	MODIFY_TEL_SEND_NEW_TELCODE_TEL("同一手机号修改手机号下发新手机验证码"){
		@Override
        public String getValue()
        {
            return "10";
        }
	},
	/**
	 * 同一用户修改手机号
	 */
	MODIFY_TEL_CHECK_NEW_TELCODE_USER("同一用户修改手机号"){
		@Override
        public String getValue()
        {
            return "2";
        }
	};

	protected final String key;
	protected final String description;
	
	SafeAuthVariable(String description) {
		StringBuilder builder = new StringBuilder(getType());
		builder.append('.').append(name());
		key = builder.toString();
		this.description = description;
	}
	
	@Override
	public String getType() {
		return SafeAuthVariable.class.getAnnotation(VariableTypeAnnotation.class).id();
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getValue() {
		try (InputStreamReader reader = new InputStreamReader(
				SafeAuthVariable.class.getResourceAsStream(getKey()), "UTF-8")) {
			StringBuilder builder = new StringBuilder();
			char[] cbuf = new char[1024];
			int len = reader.read(cbuf);
			while (len > 0) {
				builder.append(cbuf, 0, len);
				len = reader.read(cbuf);
			}
			return builder.toString();
		} catch (Throwable t) {
		}
		return null;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
