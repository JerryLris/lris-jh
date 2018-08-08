package com.lris.ain.back.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import com.lris.ain.back.entities.B1006;
import com.lris.ain.core.exception.ParamException;

public class SessionManager {

	public static void setSession(HttpServletRequest req, B1006 user) {
		req.getSession().invalidate();
		req.getSession().setAttribute("accountid", user.getF01());
		req.getSession().setAttribute("accountname", user.getF02());
		req.getSession().setAttribute("realname", user.getF04());
		req.getSession().setAttribute("extensionnumber", user.getF12());
		//新增一个部门催收id
		req.getSession().setAttribute("collectionemp", user.getF13());
	}	
	
	public static String getExtensionNumber(HttpServletRequest req) {
		if (req.getSession() != null && req.getSession().getAttribute("extensionnumber") != null) {
			return req.getSession().getAttribute("extensionnumber").toString();
		} else {
			return null;
		}
	}
	
	public static int getId(HttpServletRequest req) {
		if (req.getSession() != null && req.getSession().getAttribute("accountid") != null) {
			return (int) req.getSession().getAttribute("accountid");
		} else {
			return 0;
		}
	}

	public static String getName(HttpServletRequest req) {
		if (req.getSession() != null && req.getSession().getAttribute("realname") != null) {
			return req.getSession().getAttribute("realname").toString();
		} else {
			return null;
		}
	}

	public static String getUserName(HttpServletRequest req) {
		if (req.getSession() != null && req.getSession().getAttribute("accountname") != null) {
			return req.getSession().getAttribute("accountname").toString();
		} else {
			return null;
		}
	}

	public static void clearSession(HttpServletRequest req) {
		req.getSession().removeAttribute("accountid");
		req.getSession().removeAttribute("realname");
		req.getSession().removeAttribute("accountname");
		req.getSession().removeAttribute("extensionnumber");
	}

	public static Object getAttribute(HttpServletRequest req, String key, boolean remove) {
		if (req.getSession() != null && req.getSession().getAttribute(key) != null) {
			if (req.getSession().getAttribute(key + "times") != null) {
				long times = Long.parseLong(req.getSession().getAttribute(key + "times").toString());
				long nowtimes = new DateTime().getMillis();
				if (remove) {
					req.getSession().removeAttribute(key + "times");
				}
				if (nowtimes > times) {
					req.getSession().removeAttribute(key);
					return null;
				}
			}

			Object val = req.getSession().getAttribute(key);
			if (remove) {
				req.getSession().removeAttribute(key);
			}
			return val;
		}
		return null;
	}

	public static void setAttribute(HttpServletRequest req, String key, String val, int limit) {
		req.getSession().setAttribute(key, val);
		if (limit > 0) {
			long times = new DateTime().getMillis() + limit * 1000L;
			req.getSession().setAttribute(key + "times", times);
		}
	}

	public static void setRights(HttpServletRequest req, List<String> rights) throws Exception {
		if (req == null) {
			throw new ParamException();
		}
		req.getSession().setAttribute("WTW_RIGHTS", rights);
	}

	/**
	 * 是否有权限
	 * 
	 * @param rightid
	 *            权限id，_1024.F01
	 */
	public static boolean hasRight(HttpServletRequest req, String rightid) {
		Object obj = req.getSession().getAttribute("WTW_RIGHTS");
		if (obj != null) {
			@SuppressWarnings("unchecked")
			List<String> rights = (List<String>) obj;
			return rights.contains(rightid);
		} else {
			return false;
		}
	}
}
