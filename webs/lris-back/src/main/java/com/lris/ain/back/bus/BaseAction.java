package com.lris.ain.back.bus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lris.ain.back.auth.Module;
import com.lris.ain.back.auth.Right;
import com.lris.ain.core.web.PubController;

/**
 * 
 */
@Controller
@RequestMapping("/Base")
@Module(pkey = "Product", key = "AppVersionManagement", name = "base模块")
public class BaseAction extends PubController {

	

	@RequestMapping("/list")
	@Right(key = "list", name = "查询")
	public String doList(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		
		return null;
	}

	@RequestMapping("/toadd")
	@Right(key = "add", name = "新增")
	public String toAdd(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		
		return null;
	}

	@RequestMapping("/add")
	@Right(key = "add", name = "新增")
	public String doAdd(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		return null;
	}

	@RequestMapping("/toupdate")
	@Right(key = "update", name = "修改")
	public String toUpdate(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		return null;
	}

	@RequestMapping("/update")
	@Right(key = "update", name = "修改")
	public String doUpdate(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		return null;
	}

	@RequestMapping("/delete")
	@Right(key = "delete", name = "删除")
	@ResponseBody
	public String doDelete(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		return null;
	}

}