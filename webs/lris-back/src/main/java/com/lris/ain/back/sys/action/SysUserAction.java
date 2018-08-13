package com.lris.ain.back.sys.action;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.UnixCrypt;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lris.ain.back.auth.Module;
import com.lris.ain.back.auth.Right;
import com.lris.ain.back.common.SessionManager;
import com.lris.ain.back.common.service.LogService;
import com.lris.ain.back.entities.B1006;
import com.lris.ain.back.enums.UseState;
import com.lris.ain.back.sys.service.SysUserService;
import com.lris.ain.back.sys.vo.SetRole;
import com.lris.ain.core.exception.AuthException;
import com.lris.ain.core.exception.ParamException;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;
import com.lris.ain.core.utils.parser.IntegerParser;
import com.lris.ain.core.web.PubController;
import com.lris.ain.core.web.RespResult;


@Controller
@RequestMapping("/SysUser")
@Module(pkey="SYS",key="SysUser",name="账号管理")
public class SysUserAction extends PubController {

	public SysUserAction(){
		this.logger = LogManager.getLogger(SysUserAction.class);
	}
	@Resource
	private SysUserService service;
	@Resource
	private LogService log;
	 
	@RequestMapping("/authEdit")
	@Right(key="authEdit",name="授权")
	public String doAuthEdit(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		int id=IntegerParser.parse(req.getParameter("id"));
		if (id > 0) {
			List<SetRole> setRole= service.getUserRoles(id);
			model.addAttribute("obj",setRole);
		}
		return "sys/setRole";
	}
	@RequestMapping("/setRole")
	@Right(key="authEdit",name="授权")
	public String setRole(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		int id=IntegerParser.parse(req.getParameter("id"));
		String[] roleIds=req.getParameterValues("roleIds");
		if(id>0){
			this.service.setRole(roleIds, id);
			log.logChange(req, "后台变更记录:账号管理用户授权,"+SessionManager.getName(req)+"给用户id="+id+"授权,角色ID="+roleIds);
		}
		return "redirect:/SysUser/list";
	}
	
	@RequestMapping("/edit")
	@Right(key="edit",name="新增")
	public String doEdit(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		genFormToken(req, model);
		/*B1171[] b1171s = consultantService.getSaleConsultants();
		model.addAttribute("b1171s", b1171s);*/
		return "sys/sysUserEdit";
	}
	
	@RequestMapping("/toupdate")
	@Right(key="toupdate",name="修改")
	public String toUpdate(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		int id=IntegerParser.parse(req.getParameter("id"));
		genFormToken(req, model);
		if(id>0){
			B1006 user= service.getSysUser(id);
			/*B1171[] b1171s = consultantService.getSaleConsultants();
			model.addAttribute("b1171s", b1171s);*/
			model.addAttribute("obj",user);
		}
		return "sys/sysUserEdit";
	}
	
	@RequestMapping("/add")
	@Right(key="edit",name="新增")
	public String doAdd(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		checkFormToken(req);
		B1006 user=new B1006();
		user.setF02(req.getParameter("userName"));
		user.setF03(req.getParameter("password"));
		user.setF04(req.getParameter("realName"));
		user.setF05(req.getParameter("status"));
		user.setF09(req.getParameter("phone"));
		user.setF10(0);
		user.setF12(req.getParameter("f12"));
		user.setF13(Integer.parseInt(req.getParameter("cuiShoueId")));
		if(service.checkF02(user.getF02())==0){
			service.newSysUser(user);
			log.logChange(req, "后台变更记录:账号管理用户新增,"+SessionManager.getName(req)+"新增用户"+req.getParameter("userName"));
			return "redirect:/SysUser/list";
		}else{
			model.addAttribute("obj",user);
			model.addAttribute("errormsg","用户名已存在");
			return "forward:/SysUser/edit";
		}
	}
	
	@RequestMapping("/update")
	@Right(key="toupdate",name="修改")
	public String doUpdate(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		int id = Integer.parseInt(req.getParameter("id")==null?"0":req.getParameter("id"));
		String password=req.getParameter("password");
		String phone=req.getParameter("phone");
		String status=req.getParameter("status");
		if(id>0){
			B1006 user=new B1006();
			user.setF01(id);
			user.setF03(password);
			user.setF05(status);
			user.setF09(phone);
			user.setF10(0);
			user.setF11(IntegerParser.parse(req.getParameter("f11")));
			user.setF12(req.getParameter("f12"));
			user.setF13(Integer.parseInt(req.getParameter("cuiShoueId")));
			this.service.updateSysUser(user);
			log.logChange(req, "后台变更记录:账号管理用户修改,"+SessionManager.getName(req)+"修改用户id="+id+"的信息");
		}
		return "redirect:/SysUser/list";
	}
	
	@RequestMapping("/list")
	@Right(key="list",name="查询")
	public String doList(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		Query query = this.getQuery(req);
		PageResult<B1006> page = service.search(query);
		model.addAttribute("page", page.getPage());
		model.addAttribute("data", page.getItems());
		model.addAttribute("useState", UseState.values());
		return "sys/sysUserList";
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping("/resetpwd")
	public String toResetpwd(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		return "sys/resetPwd";
	}
	
	
	@RequestMapping("/doresetpwd")
	public void doResetpwd(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		String result= "";
		int id = SessionManager.getId(req);
		if(id <= 0){
			throw new AuthException("请先登录");
		}
		Query query = this.getQuery(req);
		B1006 obj = service.getSysUser(id);
		if(obj == null){
			throw new ParamException("用户未注册");
		}
		String oldpwd = query.getStr("oldpwd");
		oldpwd = UnixCrypt.crypt(oldpwd,DigestUtils.sha256Hex(oldpwd));
		if(!oldpwd.equals(obj.getF03())){
			result = "请输入正确的旧密码";
		}else{
			String pwd = query.getStr("newpwd");
			pwd = UnixCrypt.crypt(pwd,DigestUtils.sha256Hex(pwd));
			service.updatePwd(pwd, id);
			log.logChange(req, "后台变更记录:账号管理用户密码修改,"+SessionManager.getName(req)+"修改了密码");
			result = "密码修改成功";
		}
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(result);
	}
	
	/**
	 * @author hulipeng
	 * 检查用户名是否重复	NO:不重复; YES:用户名重复
	 */
	@RequestMapping("/docheckf02")
	public void doCheckF02(HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		String result= "YES";
		String F02 = req.getParameter("f02");
		if(!StringUtils.isEmpty(F02)){
			if(service.checkF02(F02) == 0)
				result = "NO";
		}
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(result);
	}
	
/*	@RequestMapping("/getList")
	@ResponseBody
	public List<B1012> doGetList(){
		return re
	}*/
	
	@RequestMapping("/unlock")
	@Right(key="unlock",name="解锁")
	@ResponseBody
	public String doUnlock(HttpServletRequest req,HttpServletResponse resp,Model model) throws Exception{
		int uid = Integer.parseInt(req.getParameter("id"));
		if(uid <= 0) {
			throw new ParamException("参数错误");
		}
		service.unlock(uid);
		RespResult rs = new RespResult();
		rs.setRs(RespResult.SUCESS);
		return rs.toJson();
	}
}
