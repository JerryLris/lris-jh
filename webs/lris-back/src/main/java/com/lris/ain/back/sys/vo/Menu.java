package com.lris.ain.back.sys.vo;

import com.lris.ain.back.entities.B1002;

public class Menu extends B1002{
	private int roleId;      	  //角色ID
	private String privilegeId;   //权限ID
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}
	
}
