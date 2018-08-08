package com.lris.ain.back.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lris.ain.back.entities.B1003;
import com.lris.ain.back.enums.UseState;
import com.lris.ain.back.sys.repository.RoleRepository;
import com.lris.ain.back.sys.service.RoleService;
import com.lris.ain.core.exception.ParamException;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;


@Service
public class RoleServiceImpl  implements RoleService {
	
	@Resource
	private RoleRepository repository;

	@Override
	public void insertRole(B1003 role) throws Exception {
		B1003 obj = repository.getByRoleName(role.getF02());
		if(obj==null){
			repository.insertRole(role);
		}else{
			throw new ParamException("角色重复,新增失败");
		}
	}

	@Override
	public void delRole(UseState F06,int F01) throws Exception {
		repository.delRole(F06,F01);
	}

	@Override
	public void updateRole(B1003 role) throws Exception {
		B1003 obj = repository.getByRoleName(role.getF02());
		if(obj==null || role.getF01()==obj.getF01()){
			repository.updateRole(role);
		}else{
			throw new ParamException("角色重复,修改失败");
		}
	}

	@Override
	public B1003 getByID(int F01) throws Exception {
		return repository.getByID(F01);
	}

	@Override
	public PageResult<B1003> search(Query query) throws Exception {
		return repository.search(query);
	}

	@Override
	public B1003 getByRoleName(String roleName) throws Exception {
		return repository.getByRoleName(roleName);
	}

}
