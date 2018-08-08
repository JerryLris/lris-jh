package com.lris.ain.back.sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lris.ain.back.auth.Module;
import com.lris.ain.back.auth.Right;
import com.lris.ain.back.common.SessionManager;
import com.lris.ain.back.entities.B1003;
import com.lris.ain.back.sys.service.MenuService;
import com.lris.ain.back.sys.service.RoleService;
import com.lris.ain.back.sys.vo.Menu;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;
import com.lris.ain.core.utils.parser.IntegerParser;
import com.lris.ain.core.web.PubController;


@Controller
@RequestMapping("/Role")
@Module(pkey="SYS",key="Role",name="角色管理")
public class RoleAction extends PubController{
	
	@Resource
	private RoleService service;
	@Resource
	private MenuService menuService;
	
	public RoleAction(){
		this.logger = LogManager.getLogger(RoleAction.class);
		this.returnurl = "/Role/list";
	}
	
	@RequestMapping("/add")
	@Right(key="add",name="新增")
	public String doAdd(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		B1003 role=new B1003();
		role.setF02(req.getParameter("roleName"));
		role.setF03(req.getParameter("roleDes"));
	    role.setF05(SessionManager.getName(req));
	    role.setF06(req.getParameter("status"));
	    service.insertRole(role);
		return "redirect:/Role/list";
	}
	
	@RequestMapping("/edit")
	public String toEdit(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		int id=IntegerParser.parse(req.getParameter("id"));
		if(id>0){
			B1003 role = service.getByID(id);
			model.addAttribute("obj", role);
		}
		return "sys/roleEdit";
	}
	
	@RequestMapping("/update")
	@Right(key="update",name="修改")
	public String doUpdate(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		int id = IntegerParser.parse(req.getParameter("id"));
		String name = req.getParameter("roleName");
		String des = req.getParameter("roleDes");
		String state = req.getParameter("status");
		if(id>0){
			B1003 role1 = new B1003();
			role1.setF01(id);
			role1.setF02(name);
			role1.setF03(des);
			role1.setF06(state);
			service.updateRole(role1);
		}
		return "redirect:/Role/list";
	}
	
	@RequestMapping("/list")
	@Right(key="list",name="查询")
	public String doList(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		Query query = this.getQuery(req);
		PageResult<B1003> page = service.search(query);
		model.addAttribute("page", page.getPage());
		model.addAttribute("data", page.getItems());
		//systemLogService.putSystemLogContent(req,"查询");
		return "sys/roleList";
	}
	
	@RequestMapping("/menuedit")
	public String doMenuEdit(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception {
		int id=IntegerParser.parse(req.getParameter("id"));
		if (id > 0) {
			B1003 role = service.getByID(id);
			List<String> pMenu=menuService.getParentModuleMenu(1);
			Map<String, List<Menu>> menus=new HashMap<String, List<Menu>>();
			for (String pm : pMenu) {
				List<Menu> cms=menuService.getModuleMenu(pm, 1, id);
				menus.put(pm, cms);
			}
			model.addAttribute("rightF02",menus);
			model.addAttribute("obj", role);
		}
		return "sys/setMenu";
	}

	@RequestMapping("/menuupdate")
	@Right(key = "menuupdate", name = "菜单设置")
	public String doMenuUpdate(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception {
		int id = IntegerParser.parse(req.getParameter("id"));
		String[] menuIds = req.getParameterValues("menuIds");
		if (id > 0) {
			menuService.updateMenu(menuIds, id, 1);
		}
		return "redirect:/Role/list";
	}
	
	@RequestMapping("/rightedit")
	public String doRightEdit(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		int id = IntegerParser.parse(req.getParameter("id"));
		if(id>0){
			B1003 role = service.getByID(id);
			model.addAttribute("obj", role);
			Map<String,Map<String, List<Menu>>> rightF02=new HashMap<String,Map<String, List<Menu>>>();
			//查询模块名称
			List<String> module=menuService.getModuleByRole(id, 1);
			
			for (String mde : module) {
				//查询模块下的菜单名称
				List<Menu> funcs=menuService.getMenusByRole(mde,id,1);
				Map<String, List<Menu>> menus=new HashMap<String, List<Menu>>();
				for (Menu menuObj : funcs) {
					//查询操作及权限
					List<Menu> cms=menuService.getModuleMenu(menuObj.getPrivilegeId().split("\\.")[1], 2, id);
					menus.put(menuObj.getF04(), cms);
				}
				rightF02.put(mde, menus);
			}
			model.addAttribute("rightF02",rightF02);
		}
		return "sys/setRight";
	}
	
	
	@RequestMapping("/rightupdate")
	@Right(key="rightupdate",name="权限设置")
	public String doRightUpdate(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		int id = IntegerParser.parse(req.getParameter("id"));
		String[] rightIds = req.getParameterValues("rightIds");
		if (id > 0) {
			menuService.updateMenu(rightIds, id, 2);
		}
		return "redirect:/Role/list";
	}

}
