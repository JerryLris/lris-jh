package com.lris.ain.back.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lris.ain.back.sys.repository.VariableRepository;
import com.lris.ain.back.sys.service.VariableService;
import com.lris.ain.back.sys.vo.Variable;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;

@Service
public class VariableServiceImpl implements VariableService {

	@Resource
	private VariableRepository repository;

	@Override
	public void insertVariable(Variable var) throws Exception {
		repository.insertVariable(var);
	}

	@Override
	public void delVariable(String F01) throws Exception {
		repository.delVariable(F01);
	}

	@Override
	public void updateVariable(Variable var) throws Exception {
		repository.updateVariable(var);
	}

	@Override
	public Variable getVariable(String F01) throws Exception {
		return repository.getByID(F01);
	}

	@Override
	public PageResult<Variable> search(Query query) throws Exception {
		return repository.search(query);
	}

	@Override
	public List<Variable> getTypes() throws Exception {
		return repository.getTypes();
	}

	@Override
	public void newVariable(Variable[] vars) throws Exception {
		repository.insertVariable(vars);
	}

}