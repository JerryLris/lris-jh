package com.lris.ain.back.common.service;

import javax.servlet.http.HttpServletRequest;

import com.lris.ain.back.entities.B1091;
import com.lris.ain.back.sys.vo.BackGroundOperationLog;
import com.lris.ain.back.sys.vo.UserOperationLog;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;

public interface LogService {

	/**
	 * 更改日志
	 * 
	 * @param req
	 * @param desc
	 * @throws Exception
	 */
	public void logChange(HttpServletRequest req, String desc) throws Exception;

	/**
	 * 请求日志
	 * 
	 * @param req
	 * @throws Exception
	 */
	public void logReq(HttpServletRequest req) throws Exception;

	/**
	 * 登录，退出日志
	 * 
	 * @param req
	 * @param success
	 * @param desc
	 * @param source
	 * @param type
	 * @throws Exception
	 */
	public void logLogin(HttpServletRequest req, Boolean success, String desc, int source, int type) throws Exception;

	/**
	 * 登录，退出日志
	 * 
	 * @param req
	 * @param success
	 * @param desc
	 * @param source
	 * @param type
	 * @param userId
	 * @throws Exception
	 */
	public void logLogin(HttpServletRequest req, Boolean success, String desc, int source, int type, int userId) throws Exception;

	/**
	 * 后台操作日志
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public PageResult<BackGroundOperationLog> searchBackGroundOperation(Query query) throws Exception;

	/**
	 * 用户操作日志
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public PageResult<UserOperationLog> searchUserOperation(Query query) throws Exception;

	/**
	 * 短信下发日志
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public PageResult<B1091> searchMsgSent(Query query) throws Exception;


}
