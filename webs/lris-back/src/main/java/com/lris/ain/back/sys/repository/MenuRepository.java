package com.lris.ain.back.sys.repository;

import java.util.List;

import com.lris.ain.back.common.repository.PubRepository;
import com.lris.ain.back.sys.vo.Menu;

public interface MenuRepository extends PubRepository{

	public void insertMenu(Menu tb) throws Exception;
	
	public List<Menu> getRightF02(int F01) throws Exception;
	
	public List<Menu> getByID(int F01) throws Exception;
	
	public List<String> getParentModuleMenu(int F05) throws Exception;
	
	public List<Menu> getModuleMenu(String F02, int F05, int F01) throws Exception;
	
	public void updateMenu(String[] F02s, int F01, int F05) throws Exception;
	
	public List<Menu> getMenusByRole(String F02, int F01, int F05) throws Exception;
	
	public List<String> getModuleByRole(int F01, int F05) throws Exception;
	
}