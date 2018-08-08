package com.lris.ain.back.sys.repository;

import java.util.List;

import com.lris.ain.back.common.repository.PubRepository;
import com.lris.ain.back.sys.vo.Right;


public interface RightRepository extends PubRepository {

	/**
	 * 获取用户权限
	 */
	public List<Right> getUserRights(int t7110id) throws Exception;

	/**
	 * 获取用户权限
	 */
	public List<String> getUserSecondRights(int t7110id) throws Exception;

}