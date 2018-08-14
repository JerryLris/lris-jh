package com.lris.ain.back.sys.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lris.ain.back.auth.Module;
import com.lris.ain.back.auth.Right;
import com.lris.ain.back.common.SessionManager;
import com.lris.ain.back.common.service.LogService;
import com.lris.ain.back.sys.service.VariableService;
import com.lris.ain.back.sys.vo.Variable;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;
import com.lris.ain.core.web.PubController;

@Controller
@RequestMapping("/Variable")
@Module(pkey = "SYS", key = "Variable", name = "平台常量")
public class VariableAction extends PubController {

	@Resource
	private VariableService service;

	@Resource
	private LogService log;

	public VariableAction() {
		logger = LogManager.getLogger(VariableAction.class);
	}

	@RequestMapping("/edit")
	@Right(key = "edit", name = "修改")
	public String doEdit(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		String key = req.getParameter("id");
		if (StringUtils.isNotEmpty(key)) {
			Variable variable = service.getVariable(key);
			model.addAttribute("obj", variable);
		}
		return "sys/variableEdit";
	}

	@RequestMapping("/update")
	@Right(key = "edit", name = "修改")
	public String doUpdate(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		String key = req.getParameter("id");
		String type = req.getParameter("type");
		String desc = req.getParameter("desc");
		String val = req.getParameter("val");
		if (StringUtils.isNotEmpty(key)) {
			Variable var = new Variable();
			var.setKey(key);
			var.setType(type);
			var.setDesc(desc);
			var.setVal(val);
			service.updateVariable(var);
			log.logChange(req, "平台常量变更日志:用户" + SessionManager.getName(req) + "修改平台变量key=" + key + "的值=" + val + ",描述=" + desc);
		}
		return "redirect:/Variable/list";
	}

	@RequestMapping("/list")
	@Right(key = "list", name = "查询")
	public String doList(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		List<Variable> types = this.service.getTypes();
		model.addAttribute("types", types);
		Query query = this.getQuery(req);
		PageResult<Variable> page = service.search(query);
		model.addAttribute("page", page.getPage());
		model.addAttribute("data", page.getItems());
		return "sys/variableList";
	}
}
