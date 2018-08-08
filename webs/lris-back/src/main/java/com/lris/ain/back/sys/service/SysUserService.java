package com.lris.ain.back.sys.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lris.ain.back.entities.B1006;
import com.lris.ain.back.sys.vo.SetRole;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;

public interface SysUserService {

	/**
	 * 新增后台帐号
	 */
	public void newSysUser(B1006 tb) throws Exception;

	/**
	 * 查询所有角色,并查询F01所拥有的角色
	 */
	public List<SetRole> getUserRoles(int F01) throws Exception;

	/**
	 * 给后台帐号授权角色权限
	 */
	public void setRole(String[] F02s, int F01) throws Exception;

	/**
	 * 更新后台帐号
	 */
	public void updateSysUser(B1006 tb) throws Exception;

	/**
	 * 根据F01查询对应的后台帐号信息
	 */
	public B1006 getSysUser(int F01) throws Exception;

	/**
	 * 查询后台帐号信息并分页
	 */
	public PageResult<B1006> search(Query query) throws Exception;

	/**
	 * 后台帐号登录
	 */
	public B1006 doLogin(HttpServletRequest request) throws Exception;

	/**
	 * 修改密码
	 */
	public void updatePwd(String F03, int F01) throws Exception;

	/**
	 * 检查登录名是否重复
	 */
	public int checkF02(String F02) throws Exception;
	
	/**
	 * 获取审单人员手机号
	 * @return
	 * @throws Exception
	 */
	public List<String> getAuditUserTel() throws Exception;
	
	/**
	 * 检测是否审单人员
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public int checkF11(int uid) throws Exception;

	/**
	 * 解锁审单人员
	 * @param uid
	 * @throws Exception
	 */
	public void unlock(int uid) throws Exception;
	

}