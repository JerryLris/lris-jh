package com.lris.ain.back.common.variable;

import java.io.InputStreamReader;

import com.lris.ain.core.variables.VariableBean;
import com.lris.ain.core.variables.VariableTypeAnnotation;

@VariableTypeAnnotation(id = "LETTER", name = "信息模版")
public enum LetterVariable implements VariableBean {
	/**
	 * 
	 * 短信下发地址
	 */
	SEND_CL_URL("短信下发地址") {

		@Override
		public String getValue() {
			return "http://222.73.117.156:80/msg/HttpBatchSendSM";
		}
	},
	/**
	 * 短信下发用户名
	 */
	SEND_CL_USERNAME("短信下发用户名") {

		@Override
		public String getValue() {
			return "";
		}
	},
	/**
	 * 短信下发密码
	 */
	SEND_CL_PWD("短信下发密码") {

		@Override
		public String getValue() {
			return "";
		}
	},
	/**
	 * 启用短信下发功能
	 */
	QY_SEND_MSG("启用短信下发功能") {

		@Override
		public String getValue() {
			return "false";
		}
	},
	/**
	 * 验证码短信下发次数
	 */
	AUTH_SEND_COUNT("验证码短信下发次数") {

		@Override
		public String getValue() {
			return "2";
		}
	},
	/**
	 * 下发注册短信验证码
	 */
	REG_NEWTEL_CODE("下发注册短信验证码") {

		@Override
		public String getValue() {
			return "尊敬的用户：您好！您正在注册hahaha，验证码：{code}，有效时间{limit}秒。";
		}
	},
	/**
	 * 注册成功
	 */
	REG_OK("注册成功") {

		@Override
		public String getValue() {
			return "尊敬的用户：您好！您已成功注册hahaha账号，欢迎您踏上快速贷款之旅。如有疑问，请致电：1234567。";
		}
	},
	/**
	 * 找回登录密码
	 */
	FIND_LOGIN_PWD("找回登录密码") {
		@Override
		public String getValue() {
			return "尊敬的用户：您好！您正在找回您的hahaha账号登录密码，验证码：{code}。如非本人操作，无需担心，对方无法操作您的账户。";
		}
	},
	/**
	 * 重置登录密码
	 */
	RESET_LOGIN_PWD("重置登录密码") {
		@Override
		public String getValue() {
			return "尊敬的用户：您好！您已经重置您的hahaha账号登录密码。如非本人操作，请致电：1234567。";
		}
	},
	/**
	 * 修改登录密码
	 */
	UPDATE_LOGIN_PWD("修改登录密码") {
		@Override
		public String getValue() {
			return "尊敬的用户：您好！您已经修改了您的hahaha账号登录密码。如非本人操作，请致电：1234567。";
		}
	},
	/**
	 * 修改手机下发原手机验证码
	 */
	MODIFY_TEL_SEND_OLDTELCODE("修改手机下发原手机验证码") {

		@Override
		public String getValue() {
			return "尊敬的用户：您好！您正在修改手机号，验证码：{code}，有效时间{limit}秒。如非本人操作，请致电：1234567。";
		}
	},
	/**
	 * 修改手机下发新手机验证码
	 */
	MODIFY_TEL_SEND_NEWTELCODE("修改手机下发新手机验证码") {

		@Override
		public String getValue() {
			return "尊敬的用户：您好！您正在绑定新手机号，验证码：{code}，有效时间{limit}秒。如非本人操作，无需担心，对方无法使用。";
		}
	},
	/**
	 * 绑定新手机号成功
	 */
	MODIFY_TEL_SUCESS("绑定新手机号成功") {

		@Override
		public String getValue() {
			return "尊敬的用户：您好！您已成功绑定新手机号。如非本人操作，请致电：1234567。";
		}
	},
	/**
	 * 下发微信绑定用户短信验证码
	 */
	BIND_USER_CODE("下发微信绑定用户短信验证码") {

		@Override
		public String getValue() {
			return "尊敬的用户：您好！您正在绑定会员，验证码：{code}。如非本人操作，无需担心，对方无法操作您的账户。";
		}
	};

	protected final String key;
	protected final String description;

	LetterVariable(String description) {
		StringBuilder builder = new StringBuilder(getType());
		builder.append('.').append(name());
		key = builder.toString();
		this.description = description;
	}

	@Override
	public String getType() {
		return LetterVariable.class.getAnnotation(VariableTypeAnnotation.class).id();
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getValue() {
		try (InputStreamReader reader = new InputStreamReader(LetterVariable.class.getResourceAsStream(getKey()),
				"UTF-8")) {
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
}
