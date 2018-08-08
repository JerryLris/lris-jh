package com.lris.ain.back.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lris.ain.back.common.repository.SysRepository;


/**
 * 系统相关的通用服务
 */

@Service
public class SysService {

	@Resource
	private SysRepository repository;
	
	/**
	 * 根据key来获取系统参数
	 * @param key
	 * @return
	 */
	public String getSysVal(String key) throws Exception{
		return repository.getSysVal(key);
	}
}
