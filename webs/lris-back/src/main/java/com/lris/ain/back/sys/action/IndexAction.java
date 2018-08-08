package com.lris.ain.back.sys.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lris.ain.back.common.SessionManager;
import com.lris.ain.back.sys.service.RightService;
import com.lris.ain.core.web.PubController;
import com.lris.ain.core.web.RespResult;

@Controller
public class IndexAction extends PubController {
	

	public IndexAction(){
		logger = LogManager.getLogger(IndexAction.class);
	}
	
	@Resource
	private RightService rightService;
	
	@RequestMapping("/index")
	public String doIndex(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		if (SessionManager.getId(req) == 0) {
			return "sys/login";
		} else {
			// 获取权限
			int uid = SessionManager.getId(req);
			SessionManager.setRights(req, rightService.getUserSecondRights(uid));
			model.addAttribute("rights", rightService.getUserRights(uid));
			return "sys/main";
		}

	}
	
	@RequestMapping("/usercenter")
	public String doUserCenter(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		if (SessionManager.getId(req) == 0) {
			return "sys/login";
		} else {
			
			return "sys/usercenter";
		}

	}
	
	@RequestMapping("/usercenterexpand")
	@ResponseBody
	public String Expand(HttpServletRequest req,HttpServletResponse resp,Model model) throws Exception {
		RespResult rs = new RespResult();
		int year = Integer.parseInt(req.getParameter("year"));
		if (SessionManager.getId(req) == 0) {
			rs.setRs(RespResult.FAIL_OTHER);
			return "sys/login";
		} else {
			rs.setRs(RespResult.SUCESS);
			rs.setObj(null);
			return rs.toJson();
		}
		
	}
}
