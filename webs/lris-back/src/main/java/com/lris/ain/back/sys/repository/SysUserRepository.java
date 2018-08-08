package com.lris.ain.back.sys.repository;

import java.util.List;

import com.lris.ain.back.common.repository.PubRepository;
import com.lris.ain.back.entities.B1006;
import com.lris.ain.back.sys.vo.SetRole;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;

public interface SysUserRepository extends PubRepository {

	public void insertSysUser(B1006 tb) throws Exception;

	public List<SetRole> getUserRoles(int F01) throws Exception;

	public void updateSysUser(B1006 tb) throws Exception;

	public B1006 getByID(int F01) throws Exception;

	public B1006 getByUsername(String username) throws Exception;

	public PageResult<B1006> search(Query query) throws Exception;

	public void setRole(String[] f02s, int f01) throws Exception;

	/**
	 * 修改密码
	 */
	public void updatePwd(String F03, int F01) throws Exception;

	public int checkF02(String F02) throws Exception;

	/**
	 * 获取登录错误次数
	 */
	public int getLoginErrorTime(String ip) throws Exception;

	/**
	 * 获取登录信息填写错误次数
	 */
	public int getMessageWrongTime(int B1006id) throws Exception;

	/**
	 * 更新最后登录时间
	 */
	public void updateLastLoginInfo(int B1006id,String ip) throws Exception;

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
	 * 更新签到状态
	 * @param b1006
	 * @throws Exception
	 */
	public void updateSysUserSign(B1006 b1006) throws Exception;

	/**
	 * 解锁审单人员
	 * @param uid
	 * @throws Exception
	 */
	public void unlock(int uid) throws Exception;
}