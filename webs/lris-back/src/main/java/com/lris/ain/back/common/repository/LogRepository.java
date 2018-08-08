package com.lris.ain.back.common.repository;

import com.lris.ain.back.entities.B1091;
import com.lris.ain.back.entities.B1304;
import com.lris.ain.back.entities.B1305;
import com.lris.ain.back.entities.B1306;
import com.lris.ain.back.sys.vo.BackGroundOperationLog;
import com.lris.ain.back.sys.vo.UserOperationLog;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;

/**
 * 日志
 */
public interface LogRepository {

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
	 */
	public PageResult<B1091> searchMsgSent(Query query) throws Exception;
	


	/**
	 * 登录，退出日志
	 * 
	 * @param b1306
	 * @throws Exception
	 */
	public void logLogin(B1306 b1306) throws Exception;

	/**
	 * 请求日志
	 * 
	 * @param b1304
	 * @throws Exception
	 */
	public void logReq(B1304 b1304) throws Exception;

	/**
	 * 更改日志
	 * 
	 * @param b1305
	 * @throws Exception
	 */
	public void logChange(B1305 b1305) throws Exception;

}
