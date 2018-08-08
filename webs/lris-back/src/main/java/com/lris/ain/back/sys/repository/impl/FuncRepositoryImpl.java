package com.lris.ain.back.sys.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lris.ain.back.sys.repository.FuncRepository;
import com.lris.ain.back.sys.vo.Func;
import com.lris.ain.core.data.jdbc.PubDao;


@Repository
public class FuncRepositoryImpl extends PubDao implements FuncRepository{

	@Override
	public void insertFunc(Func[] func) throws Exception {
		if(func==null||func.length==0){
			return;
		}
		String sql = "replace into B1002 set F01=?,F02=?,F03=?,F04=?,F05=?";
		List<Object[]> arg = new ArrayList<Object[]>();
		for(int i=0;i<func.length;i++){
			Func f = func[i];
			Object[] obj = new Object[5];
			obj[0] = f.getId();
			obj[1] = f.getPkey();
			obj[2] = f.getKey();
			obj[3] = f.getName();
			obj[4] = f.getLevel();
			arg.add(obj);
		}
		this.getJdbcTemplate().batchUpdate(sql, arg);
	}

}
