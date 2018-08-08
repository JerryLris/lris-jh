package com.lris.ain.back.sys.action;


import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lris.ain.back.common.SessionManager;
import com.lris.ain.back.common.domain.SessionKey;
import com.lris.ain.back.common.service.LogService;
import com.lris.ain.back.entities.B1006;
import com.lris.ain.back.sys.service.SysUserService;
import com.lris.ain.core.utils.CodeGenerator;
import com.lris.ain.core.utils.KaptchaUtils;
import com.lris.ain.core.web.PubController;

/**
 * 登录action
 *
 */
@Controller
@RequestMapping("/login")
public class LoginAction extends PubController {
	
	@Resource
	private SysUserService userService;
	@Resource
	private LogService logService;

	public LoginAction() {
		logger = LogManager.getLogger(LoginAction.class);
		returnurl = "/login/tologin";
	}

	@RequestMapping("/tologin")
	public String toLogin(HttpServletRequest req, HttpServletResponse resp, Model model) {
		if (StringUtils.isEmpty(req.getSession().getAttribute("userid"))) {
			return "sys/login";
		} else {
			return "sys/main";
		}
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String doLogin(HttpServletRequest req, HttpServletResponse resp, Model model) throws Throwable {
		B1006 user = userService.doLogin(req);
		SessionManager.setSession(req, user);
		logService.logLogin(req, true, null, 1, 0);
		return "redirect:/index";
	}

	@RequestMapping(value = "/loginout")
	public String loginOut(HttpServletRequest req, HttpServletResponse resp, Model model) throws Throwable {
		logService.logLogin(req, true, null, 1, 1);
		SessionManager.clearSession(req);
		return "redirect:/login/tologin";
	}

	/**
	 * 获取验证码图片
	 */
	@RequestMapping("/getcode")
	public void genCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code = new CodeGenerator().genCode();
		
		response.setContentType("image/jpeg");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		try {
			final BufferedImage bi = KaptchaUtils.genCode(code);
			ImageIO.write(bi, "jpeg", response.getOutputStream());
			SessionManager.setAttribute(request, SessionKey.BAP_LOGIN_CODE, code, 60);
			
			response.getWriter().flush();
		} catch (Exception e) {
		}
	}
}
