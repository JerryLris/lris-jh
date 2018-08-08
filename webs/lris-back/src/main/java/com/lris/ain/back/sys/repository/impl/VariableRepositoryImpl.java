package com.lris.ain.back.sys.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.lris.ain.back.sys.repository.VariableRepository;
import com.lris.ain.back.sys.vo.Variable;
import com.lris.ain.core.data.jdbc.PubDao;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;

@Repository
public class VariableRepositoryImpl extends PubDao implements VariableRepository {


	@Override
	public void insertVariable(Variable var) throws Exception {
		String sql = "insert into B1001 set F01=?,F02=?,F03=?,F04=? ";
		this.getJdbcTemplate().update(sql, var.getKey(), var.getVal(), var.getType(), var.getDesc());
	}

	@Override
	public void delVariable(String F01) throws Exception {
		String sql = "delete from B1001 where F01=?";
		this.getJdbcTemplate().update(sql, F01);
	}

	@Override
	public void updateVariable(Variable var) throws Exception {
		String sql = "update B1001 set F01=?,F02=?,F03=?,F04=? where F01=?";
		this.getJdbcTemplate().update(sql, var.getKey(), var.getVal(), var.getType(), var.getDesc(), var.getKey());
	}

	@Override
	public Variable getByID(String F01) throws Exception {
		String sql = "select F01,F02,F03,F04 from B1001 where F01=? ";
		List<Variable> obj = this.getJdbcTemplate().query(sql, new RowMapper<Variable>() {

			@Override
			public Variable mapRow(ResultSet rs, int rowNum) throws SQLException {
				Variable obj = new Variable();
				obj.setKey(rs.getString("F01"));
				obj.setVal(rs.getString("F02"));
				obj.setType(rs.getString("F03"));
				obj.setDesc(rs.getString("F04"));
				return obj;
			}
		}, F01);
		return this.singleResult(obj);
	}

	/**
	 * 条件查询
	 */

	@Override
	public PageResult<Variable> search(Query query) throws Exception {
		PageResult<Variable> pageResult = new PageResult<Variable>();
		String wheresql = "where 1=1 ";
		List<Object> params = new ArrayList<Object>();

		if (query.hasStr("desc")) {
			wheresql += " and F04 like ? ";
			params.add("%" + query.getStr("desc") + "%");
		}

		if (query.hasStr("key")) {
			wheresql += " and F01 like ? ";
			params.add("%" + query.getStr("key") + "%");
		}

		if (query.hasStr("type")) {
			wheresql += " and F03= ?";
			params.add(query.getStr("type"));
		}

		String sql = "select F01,F02,F03,F04 from B1001 " + wheresql + " order by F01 limit ?,?";

		if (query.getPageIndex() == 1) {
			String countsql = "select count(1) from B1001 " + wheresql;
			int count = this.getJdbcTemplate().queryForObject(countsql, Integer.class, params.toArray());
			query.getPage().setTotalCount(count);
		}

		params.add((query.getPageIndex() - 1) * query.getPageSize());
		params.add(query.getPageSize());

		pageResult.setPage(query.getPage());
		List<Variable> list = this.getJdbcTemplate().query(sql, params.toArray(), new RowMapper<Variable>() {

			@Override
			public Variable mapRow(ResultSet rs, int rowNum) throws SQLException {
				Variable obj = new Variable();
				obj.setKey(rs.getString("F01"));
				obj.setVal(rs.getString("F02"));
				obj.setType(rs.getString("F03"));
				obj.setDesc(rs.getString("F04"));
				return obj;
			}
		});
		pageResult.setItems((list == null || list.size() == 0) ? null : list.toArray(new Variable[list.size()]));
		return pageResult;
	}

	/**
	 * 获取常量类型
	 */

	@Override
	public List<Variable> getTypes() throws Exception {
		String sql = "select F03 from B1001 group by F03";
		List<Variable> types = this.getJdbcTemplate().query(sql, new RowMapper<Variable>() {
			@Override
			public Variable mapRow(ResultSet rs, int rowNum) throws SQLException {
				Variable obj = new Variable();
				obj.setType(rs.getString("F03"));
				return obj;
			}
		});
		return types;
	}
	
	@Override
	public void insertVariable(Variable[] vars) throws Exception {
		if(vars==null||vars.length==0){
			return;
		}
		String sql = "select F01 from B1001 where F01=?";
		List<Object[]> arg = new ArrayList<Object[]>();
		for(int i=0;i<vars.length;i++){
			Variable var = vars[i];
			SqlRowSet rs = this.getJdbcTemplate().queryForRowSet(sql,var.getKey());
			if(rs.next()){
				continue;
			}
			Object[] obj = new Object[4];
			obj[0] = var.getKey();
			obj[1] = var.getVal();
			obj[2] = var.getType();
			obj[3] = var.getDesc();
			arg.add(obj);
		}
		sql = "insert into B1001 set F01=?,F02=?,F03=?,F04=?";
		this.getJdbcTemplate().batchUpdate(sql, arg);
	}

}