package com.lris.ain.back.sys.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lris.ain.back.auth.Module;
import com.lris.ain.back.auth.Right;
import com.lris.ain.back.common.service.LogService;
import com.lris.ain.back.sys.vo.UserOperationLog;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;
import com.lris.ain.core.web.PubController;

@Controller
@RequestMapping("/UserOperationLog")
@Module(pkey = "SYS", key = "UserOperationLog", name = "用户操作日志")
public class UserOperationLogAction extends PubController {

	@Resource
	private LogService logService;

	public UserOperationLogAction() {
		logger = LogManager.getLogger(UserOperationLogAction.class);
	}

	@RequestMapping("/list")
	@Right(key = "list", name = "查询")
	public String doList(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		Query query = this.getQuery(req);
		PageResult<UserOperationLog> page = logService.searchUserOperation(query);
		model.addAttribute("page", page.getPage());
		model.addAttribute("data", page.getItems());
		return "sys/useroperationlog";
	}
}
