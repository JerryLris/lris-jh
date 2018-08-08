package com.lris.ain.back.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lris.ain.back.sys.repository.FuncRepository;
import com.lris.ain.back.sys.service.FuncService;
import com.lris.ain.back.sys.vo.Func;


@Service
public class FuncServiceImpl implements FuncService{

	@Resource
	private FuncRepository repository;
	
	@Override
	@Transactional
	public void newFunc(Func[] func) throws Exception {
		repository.insertFunc(func);
	}

}
