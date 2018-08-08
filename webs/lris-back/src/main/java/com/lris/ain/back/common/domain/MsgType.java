package com.lris.ain.back.common.domain;

public class MsgType {

	public static int AUTH = 1;  //验证码短信
	
	public static int INFO = 0;  //通知短信
	
	public static int SIGN_CODE = 2; //注册用户验证码短信
	
	public static int BIND_CODE = 3; //绑定微信/支付宝等第三方由用户发起的快捷支付验证码短信
}
