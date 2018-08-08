package com.lris.ain.core.utils;

import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.util.StringUtils;


/**
 * 判断form是否重复提交
 */

public class TokenHandler {
	
	public static final String TOKEN_NAME = "cc894a14-9399-41cb-da23-c440819b4565";
	
	public static boolean verify(HttpSession session, String currentTokenValue) {
		if(session == null) {
			return false;
		}
		String token = (String)session.getAttribute(TOKEN_NAME);
		if(StringUtils.isEmpty(currentTokenValue) || !currentTokenValue.equals(token)) {
			return false;
		}
		session.removeAttribute(TOKEN_NAME);
		return true;
	}
	
	private static String generate(HttpSession session) {
		String token = null;
		if(session == null) {
			return token;
		}
		token = UUID.randomUUID().toString();
		session.setAttribute(TOKEN_NAME, token);
		return token;
	}
	
	
	public static String hidden(HttpSession session) {
		return "<input type=\"hidden\" id=\"" + TOKEN_NAME +  "\" name=\"" + TOKEN_NAME +  "\" value=\"" + generate(session) + "\">";
	}
	
	public static String urlparam(HttpSession session) {
		return generate(session);
	}
}
