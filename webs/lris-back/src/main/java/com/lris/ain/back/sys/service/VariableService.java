package com.lris.ain.back.sys.service;

import java.util.List;

import com.lris.ain.back.sys.vo.Variable;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;

public interface VariableService {

	/**
	 * 添加
	 * @param var
	 * @throws Exception
	 */
	public void insertVariable(Variable var) throws Exception;

	/**
	 * 更新
	 * @param var
	 * @throws Exception
	 */
	public void updateVariable(Variable var) throws Exception;

	/**
	 * 删除
	 * @param F01
	 * @throws Exception
	 */
	public void delVariable(String F01) throws Exception;

	/**
	 * 获取单个常量
	 * @param F01
	 * @return
	 * @throws Exception
	 */
	public Variable getVariable(String F01) throws Exception;

	/**
	 * 查询条件
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public PageResult<Variable> search(Query query) throws Exception;

	/**
	 * 获取常量类型
	 * @return
	 * @throws Exception
	 */
	public List<Variable> getTypes() throws Exception;
	
	public void newVariable(Variable[] vars) throws Exception;

}