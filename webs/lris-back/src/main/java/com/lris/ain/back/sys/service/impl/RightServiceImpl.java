package com.lris.ain.back.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lris.ain.back.sys.repository.RightRepository;
import com.lris.ain.back.sys.service.RightService;
import com.lris.ain.back.sys.vo.Right;
import com.lris.ain.core.exception.ParamException;


@Service
public class RightServiceImpl implements RightService {

	@Resource
	private RightRepository repository;

	@Override
	public Map<String, Right> getUserRights(int t7110id) throws Exception {
		List<Right> rList = repository.getUserRights(t7110id);
		Map<String, Right> result = new HashMap<String, Right>();
		if (rList != null && !rList.isEmpty()) {
			for (Right right : rList) {
				result.put(right.getF01(), right);
			}
		}
		return result;
	}

	@Override
	public List<String> getUserSecondRights(int t7110id) throws Exception {
		if (t7110id <= 0) {
			throw new ParamException();
		}
		return repository.getUserSecondRights(t7110id);
	}
}