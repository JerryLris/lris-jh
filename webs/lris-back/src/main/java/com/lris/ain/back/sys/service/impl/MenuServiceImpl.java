package com.lris.ain.back.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lris.ain.back.sys.repository.MenuRepository;
import com.lris.ain.back.sys.service.MenuService;
import com.lris.ain.back.sys.vo.Menu;


@Service
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuRepository repository;
	
	public void newMenu(Menu tb) throws Exception{
		repository.insertMenu(tb);
	}
	
	public List<Menu> getRightF02(int F01) throws Exception{
		return repository.getRightF02(F01);
	}
	
	public List<Menu> getMenu(int F01) throws Exception{
		return repository.getByID(F01);
	}
	
	public List<String> getParentModuleMenu(int F05) throws Exception {
		return repository.getParentModuleMenu(F05);
	}
	
	public List<Menu> getModuleMenu(String F02, int F05, int F01) throws Exception {
		return repository.getModuleMenu(F02, F05,  F01);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void updateMenu(String[] F02s, int F01, int F05) throws Exception {
		repository.updateMenu(F02s, F01, F05);
	}
	
	public List<Menu> getMenusByRole(String F02, int F01, int F05) throws Exception {
		return repository.getMenusByRole(F02, F01, F05);
	}
	
	public List<String> getModuleByRole(int F01, int F05) throws Exception {
		return repository.getModuleByRole(F01, F05);
	}
	
}