package com.lris.ain.back.sys.service;

import java.util.List;

import com.lris.ain.back.sys.vo.Menu;


public interface MenuService {

	public void newMenu(Menu tb) throws Exception;
	
	public List<Menu> getRightF02(int F01) throws Exception;
	
	public List<Menu> getMenu(int F01) throws Exception;
	
	public List<String> getParentModuleMenu(int F05) throws Exception;
	
	/**
	 * 根据级别和父功能代号查询该父功能下的所有功能菜单
	 * @param  F02 	父功能代号
	 * @param  F01 	角色ID
	 * @param  F05	权限类型:1=菜单;2=操作
	 */
	public List<Menu> getModuleMenu(String F02, int F05, int F01) throws Exception;
	
	/**
	 * 更新角色的功能和操作权限
	 * @param  F02s 权限
	 * @param  F01 	角色ID
	 * @param  F05	权限类型:1=菜单;2=操作
	 */
	public void updateMenu(String[] F02s, int F01, int F05) throws Exception;
	
	/**
	 * 根据角色ID查询该角色的菜单
	 * @param  F01 	角色ID
	 * @param  F05	权限类型:1=菜单;2=操作
	 */
	public List<Menu> getMenusByRole(String F02, int F01, int F05) throws Exception;
	
	/**
	 * 根据角色ID查询该角色的模块类型
	 * @param  F01 	角色ID
	 * @param  F05	权限类型:1=菜单;2=操作
	 */
	public List<String> getModuleByRole(int F01, int F05) throws Exception;
	
}