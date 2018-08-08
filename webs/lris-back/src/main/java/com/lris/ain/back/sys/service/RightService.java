package com.lris.ain.back.sys.service;

import java.util.List;
import java.util.Map;

import com.lris.ain.back.sys.vo.Right;


public interface RightService {

	public Map<String, Right> getUserRights(int t7110id) throws Exception;

	public List<String> getUserSecondRights(int t7110id) throws Exception;

}