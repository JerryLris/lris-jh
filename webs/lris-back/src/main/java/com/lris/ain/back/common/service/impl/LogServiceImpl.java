package com.lris.ain.back.common.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.lris.ain.back.common.SessionManager;
import com.lris.ain.back.common.repository.LogRepository;
import com.lris.ain.back.common.service.LogService;
import com.lris.ain.back.entities.B1091;
import com.lris.ain.back.entities.B1304;
import com.lris.ain.back.entities.B1305;
import com.lris.ain.back.entities.B1306;
import com.lris.ain.back.sys.vo.BackGroundOperationLog;
import com.lris.ain.back.sys.vo.UserOperationLog;
import com.lris.ain.core.exception.ParamException;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;
import com.lris.ain.core.utils.DateTimeHelper;

@Service
public class LogServiceImpl implements LogService {

	@Resource
	private LogRepository logRepository;

	public PageResult<BackGroundOperationLog> searchBackGroundOperation(Query query) throws Exception {
		return logRepository.searchBackGroundOperation(query);
	}

	public PageResult<UserOperationLog> searchUserOperation(Query query) throws Exception {
		return logRepository.searchUserOperation(query);
	}

	public PageResult<B1091> searchMsgSent(Query query) throws Exception {
		return logRepository.searchMsgSent(query);
	}
	
	public void logLogin(HttpServletRequest req, Boolean success, String desc, int source, int type) throws Exception {
		if (req == null) {
			throw new ParamException();
		}
		B1306 b1306 = new B1306();
		b1306.setF02(SessionManager.getId(req));
		b1306.setF03(req.getRemoteHost());
		b1306.setF04(DateTimeHelper.getSqlTimeNow("yyyy-MM-dd HH:mm:ss"));
		b1306.setF05(success ? 0 : 1);
		b1306.setF06(desc);
		b1306.setF07(source);
		b1306.setF08(type);
		logRepository.logLogin(b1306);
	}

	@Override
	public void logLogin(HttpServletRequest req, Boolean success, String desc, int source, int type, int userId) throws Exception {
		if (req == null) {
			throw new ParamException();
		}
		B1306 b1306 = new B1306();
		b1306.setF02(userId);
		b1306.setF03(req.getRemoteHost());
		b1306.setF04(DateTimeHelper.getSqlTimeNow("yyyy-MM-dd HH:mm:ss"));
		b1306.setF05(success ? 0 : 1);
		b1306.setF06(desc);
		b1306.setF07(source);
		b1306.setF08(type);
		logRepository.logLogin(b1306);

	}

	@Override
	public void logReq(HttpServletRequest req) throws Exception {
		if (req == null) {
			throw new ParamException();
		}
		B1304 b1304 = new B1304();
		b1304.setF02(req.getRemoteHost());
		b1304.setF03(SessionManager.getId(req));
		b1304.setF04(DateTimeHelper.getSqlTimeNow("yyyy-MM-dd HH:mm:ss"));
		b1304.setF05(DateTimeHelper.getSqlNow("yyyy-MM-dd"));
		b1304.setF06(req.getRequestURI());
		b1304.setF07(req.getHeader("referer"));
		b1304.setF08(req.getMethod());
		b1304.setF09(req.getHeader("X-Requested-With") != null ? 1 : 0);
		b1304.setF10(req.getContextPath());
		b1304.setF11(req.getHeader("User-Agent"));
		logRepository.logReq(b1304);
	}

	@Override
	public void logChange(HttpServletRequest req, String desc) throws Exception {
		if (req == null) {
			throw new ParamException();
		}
		B1305 b1305 = new B1305();
		b1305.setF02(SessionManager.getId(req));
		b1305.setF03(DateTimeHelper.getSqlTimeNow("yyyy-MM-dd HH:mm:ss"));
		b1305.setF06(desc);
		logRepository.logChange(b1305);
	}

	
	
}
