package com.lris.ain.back.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.UnixCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lris.ain.back.common.SessionManager;
import com.lris.ain.back.common.domain.SessionKey;
import com.lris.ain.back.common.service.LogService;
import com.lris.ain.back.common.service.SysService;
import com.lris.ain.back.entities.B1006;
import com.lris.ain.back.sys.repository.SysUserRepository;
import com.lris.ain.back.sys.service.SysUserService;
import com.lris.ain.back.sys.vo.SetRole;
import com.lris.ain.core.exception.AuthException;
import com.lris.ain.core.exception.ParamException;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;
import com.lris.ain.core.utils.parser.IntegerParser;
import com.lris.ain.core.variables.SafeAuthVariable;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserRepository repository;
	@Resource
	private LogService logService;
	@Resource
	private SysService sysService;

	public void newSysUser(B1006 tb) throws Exception {
		repository.insertSysUser(tb);
	}

	public List<SetRole> getUserRoles(int F01) throws Exception {
		return repository.getUserRoles(F01);
	}

	@Override
	public void setRole(String[] F02s, int F01) throws Exception {
		repository.setRole(F02s, F01);
	}

	public void updateSysUser(B1006 tb) throws Exception {
		repository.updateSysUser(tb);
	}

	public B1006 getSysUser(int F01) throws Exception {
		return repository.getByID(F01);
	}

	public PageResult<B1006> search(Query query) throws Exception {
		return repository.search(query);
	}

	@Override
	public B1006 doLogin(HttpServletRequest req) throws Exception {
		if (req == null) {
			throw new ParamException();
		}
		String username = req.getParameter("username");
		String pwd = req.getParameter("sp");
		String code = req.getParameter("code");
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(pwd)) {
			throw new ParamException("用户名或密码不能为空");
		}
		pwd = new String(Base64.decodeBase64(pwd), "utf-8");
		int errortime = repository.getLoginErrorTime(req.getRemoteHost());
		int iperrortime = IntegerParser.parse(sysService.getSysVal(SafeAuthVariable.LOGIN_ERROR_TIMES_IP.getKey()));
		if (errortime >= iperrortime) {
			throw new AuthException("该ip已累计"+iperrortime+"次登录失败，请明天再尝试");
		}
		B1006 user = repository.getByUsername(username);
		if (user != null) {
			if(StringUtils.isEmpty(user.getF05()) || "TY".equals(user.getF05())) {
				throw new ParamException("账号已停用,操作失败");
			}
			int wrongtime = repository.getMessageWrongTime(user.getF01());
			int loginerrortime = IntegerParser.parse(sysService.getSysVal(SafeAuthVariable.LOGIN_ERROR_TIMES_USER.getKey()));
			if (wrongtime >= loginerrortime) {
				throw new AuthException("该账号已累计"+loginerrortime+"次密码错误，请明天再尝试");
			}
			if (code == null || !code.equals(SessionManager.getAttribute(req, SessionKey.BAP_LOGIN_CODE, true))) {
				logService.logLogin(req, false, "验证码错误", 1, 0);
				throw new ParamException("验证码错误");
			}
			// 验证加密密码，密码错误需要登记错误次数，操作多少次就需要锁定账户，待系统管理员来解锁
			pwd = UnixCrypt.crypt(pwd, DigestUtils.sha256Hex(pwd));
			if (!pwd.equals(user.getF03())) {
				logService.logLogin(req, false, "用户名或密码错误", 1, 0, user.getF01());
				throw new AuthException("用户名或密码错误");
			}
			repository.updateLastLoginInfo(user.getF01(), req.getRemoteHost());

		} else {
			logService.logLogin(req, false, "用户名或密码错误", 1, 0);
			throw new AuthException("用户名或密码错误");
		}
		return user;
	}

	/**
	 * 修改密码
	 */
	@Override
	public void updatePwd(String F03, int F01) throws Exception {
		repository.updatePwd(F03, F01);
	}

	@Override
	public int checkF02(String F02) throws Exception {
		return repository.checkF02(F02);
	}

	@Override
	public List<String> getAuditUserTel() throws Exception {
		return repository.getAuditUserTel();
	}

	@Override
	public int checkF11(int uid) throws Exception {
		return repository.checkF11(uid);
	}

	@Override
	public void unlock(int uid) throws Exception {
		repository.unlock(uid);
	}

}