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
import com.lris.ain.back.sys.vo.BackGroundOperationLog;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;
import com.lris.ain.core.web.PubController;

@Controller
@RequestMapping("/BackGroundOperationLog")
@Module(pkey = "SYS", key = "BackGroundOperationLog", name = "后台操作日志")
public class BackGroundOperationLogAction extends PubController {

	@Resource
	private LogService logService;
	
	public BackGroundOperationLogAction() {
		logger = LogManager.getLogger(BackGroundOperationLogAction.class);
	}
	
	@RequestMapping("/list")
	@Right(key="list",name="查询")
	public String doList(HttpServletRequest req,HttpServletResponse resp,Model model) throws Exception{
		Query query = this.getQuery(req);
		PageResult<BackGroundOperationLog> page=logService.searchBackGroundOperation(query);
		model.addAttribute("page", page.getPage());
		model.addAttribute("data", page.getItems());
		return "sys/backgroundoperationlog";
	}
}
