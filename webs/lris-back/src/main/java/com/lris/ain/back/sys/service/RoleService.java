package com.lris.ain.back.sys.service;

import com.lris.ain.back.entities.B1003;
import com.lris.ain.back.enums.UseState;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;


public interface RoleService {
	
	/**
	 * 新增角色
	 */
    public void insertRole(B1003 role) throws Exception;
	
    /**
     * 删除角色,状态改为停用
     */
	public void delRole(UseState F06,int F01) throws Exception;
	
	/**
	 * 更新角色
	 */
	public void updateRole(B1003 role) throws Exception;
	
	/**
	 * 获取角色
	 */
	public B1003 getByID(int F01) throws Exception;
	
	/**
	 * 查询分页
	 */
	public PageResult<B1003>  search(Query query) throws Exception;
	
	/**
	 * 获取角色
	 */
	public B1003 getByRoleName(String roleName) throws Exception;

}
