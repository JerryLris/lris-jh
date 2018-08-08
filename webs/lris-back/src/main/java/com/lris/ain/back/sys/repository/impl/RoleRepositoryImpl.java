package com.lris.ain.back.sys.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lris.ain.back.entities.B1003;
import com.lris.ain.back.enums.UseState;
import com.lris.ain.back.sys.repository.RoleRepository;
import com.lris.ain.core.data.jdbc.PubDao;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;


@Repository
public class RoleRepositoryImpl extends PubDao implements RoleRepository {

	@Override
	public void insertRole(B1003 role) throws Exception {
		String sql="insert into lris.B1003 set F02=?,F03=?,F04=now(),F05=?,F06=?";
		this.getJdbcTemplate().update(sql,role.getF02(),role.getF03(),role.getF05(),role.getF06());
	}

	@Override
	public void delRole(UseState F06,int F01) throws Exception {
		String sql = "update lris.B1003 set F06=? where F01=?";
		this.getJdbcTemplate().update(sql,F06,F01);
	}

	@Override
	public void updateRole(B1003 role) throws Exception {
		String sql = "update lris.B1003 set F02=?,F03=?,F06=? where F01=?";
		this.getJdbcTemplate().update(sql,role.getF02(),role.getF03(),role.getF06(),role.getF01());
	}
    
	@Override
	public B1003 getByID(int F01) throws Exception {
		String sql = "select F01,F02,F03,F04,F05,F06 from lris.B1003 where F01=? ";
		List<B1003> obj = this.getJdbcTemplate().query(sql, new RowMapper<B1003>(){
			@Override
			public B1003 mapRow(ResultSet rs, int rowNum) throws SQLException {
				B1003 obj = new B1003();
				obj.setF01(rs.getInt("F01"));
				obj.setF02(rs.getString("F02"));
				obj.setF03(rs.getString("F03"));
				obj.setF04(rs.getTimestamp("F04"));
				obj.setF05(rs.getString("F05"));
				obj.setF06(rs.getString("F06"));
				return obj;
			}
		},F01);
		return singleResult(obj);
	}
 
	@Override
	public PageResult<B1003> search(Query query) throws Exception {
		PageResult<B1003> pageResult = new PageResult<B1003>();
		String wheresql = "where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		
		if(query.hasStr("name")){
			wheresql += " and F02 like ? ";
			params.add("%"+query.getStr("name")+"%");
		}
		if(query.hasStr("status")){
			wheresql += " and F06= ? ";
			params.add(query.getStr("status"));
		}
		
		String sql = "select F01,F02,F03,F04,F05,F06 from lris.B1003 "+wheresql+" order by F01 limit ?,?";
		
		String countsql = "select count(1) from lris.B1003 "+wheresql;
		int count = this.getJdbcTemplate().queryForObject(countsql, Integer.class, params.toArray());
		query.getPage().setTotalCount(count);
		
		params.add((query.getPageIndex()-1)*query.getPageSize());
		params.add(query.getPageSize());
		
		pageResult.setPage(query.getPage());
		List<B1003> list = this.getJdbcTemplate().query(sql,params.toArray(), new RowMapper<B1003>(){
			@Override
			public B1003 mapRow(ResultSet rs, int rowNum) throws SQLException {
				B1003 obj = new B1003();
				obj.setF01(rs.getInt("F01"));
				obj.setF02(rs.getString("F02"));
				obj.setF03(rs.getString("F03"));
				obj.setF04(rs.getTimestamp("F04"));
				obj.setF05(rs.getString("F05"));
				obj.setF06(rs.getString("F06"));
				return obj;
			}
		});
		pageResult.setItems((list==null||list.size()==0)?null:list.toArray(new B1003[list.size()]));
		return pageResult;
	}

	@Override
	public B1003 getByRoleName(String roleName) throws Exception {
		String sql="select F01,F02,F03,F04,F05,F06 from lris.B1003 where F02=? ";
		List<B1003> obj = this.getJdbcTemplate().query(sql, new RowMapper<B1003>(){
			@Override
			public B1003 mapRow(ResultSet rs, int rowNum) throws SQLException {
				B1003 obj = new B1003();
				obj.setF01(rs.getInt("F01"));
				obj.setF02(rs.getString("F02"));
				obj.setF03(rs.getString("F03"));
				obj.setF04(rs.getTimestamp("F04"));
				obj.setF05(rs.getString("F05"));
				obj.setF06(rs.getString("F06"));
				return obj;
			}
		},roleName);
		return singleResult(obj);
	}

}
