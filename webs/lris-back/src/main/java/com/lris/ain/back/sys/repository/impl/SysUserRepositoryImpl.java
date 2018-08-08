package com.lris.ain.back.sys.repository.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.UnixCrypt;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.lris.ain.back.entities.B1006;
import com.lris.ain.back.sys.repository.SysUserRepository;
import com.lris.ain.back.sys.vo.SetRole;
import com.lris.ain.core.data.jdbc.PubDao;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;

@Repository
public class SysUserRepositoryImpl extends PubDao implements SysUserRepository {

	@Override
	public void insertSysUser(B1006 tb) throws Exception {
		String sql = "insert into lris.B1006 set F02=?,F03=?,F04=?,F05=?,F06=now(),F09=?,F10=?,F11=0,F12=?,F13=? ";
		this.getJdbcTemplate().update(sql, tb.getF02(), UnixCrypt.crypt(tb.getF03(), DigestUtils.sha256Hex(tb.getF03()))
				, tb.getF04(), tb.getF05(), tb.getF09(),tb.getF10(),tb.getF12(),tb.getF13());
	}

	@Override
	public List<SetRole> getUserRoles(int F01) throws Exception {
		String sql = "select t1.F01,t1.F02,t1.F06,t2.F01 userId from lris.B1003 t1 "
				+ "left join lris.B1005 t2 on t1.F01=t2.F02 and t2.F01=? order by t1.F01 ";
		List<SetRole> objs = this.getJdbcTemplate().query(sql, new RowMapper<SetRole>() {
			public SetRole mapRow(ResultSet rs, int rowNum) throws SQLException {
				SetRole obj = new SetRole();
				obj.setF01(rs.getInt("F01"));
				obj.setF02(rs.getString("F02"));
				obj.setF06(rs.getString("F06"));
				obj.setUserId(rs.getInt("userId"));
				return obj;
			}
		}, F01);
		return objs;
	}

	@Override
	public void setRole(final String[] F02s, final int F01) throws Exception {
		String delSql = "delete  from lris.B1005 where F01=?";
		this.getJdbcTemplate().update(delSql, F01);
		if(F02s!=null && F02s.length>0) {
			String addSql = "insert into lris.B1005 set F01=?,F02=? ";
			getJdbcTemplate().batchUpdate(addSql, new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setInt(1, F01);
					ps.setString(2, F02s[i]);
				}

				public int getBatchSize() {
					return F02s.length;
				}
			});
		}
	}

	@Override
	public void updateSysUser(B1006 tb) throws Exception {
		String sql = "update lris.B1006 set F05=?,F09=?,F10=?,F11=?,F12=?,F13=? where F01=?";
		this.getJdbcTemplate().update(sql, tb.getF05(), tb.getF09(),tb.getF10(),tb.getF11(),tb.getF12(),tb.getF13(),tb.getF01());
		if (!StringUtils.isEmpty(tb.getF03())) {
			getJdbcTemplate().update("update lris.B1006 set F03=? where F01=?", UnixCrypt.crypt(tb.getF03(), DigestUtils.sha256Hex(tb.getF03())), tb.getF01());
		}
	}

	@Override
	public B1006 getByID(int F01) throws Exception {
		String sql = "select * from lris.B1006 where F01=? ";
		List<B1006> obj = this.getJdbcTemplate().query(sql, new RowMapper<B1006>() {
			public B1006 mapRow(ResultSet rs, int rowNum) throws SQLException {
				B1006 obj = new B1006();
				obj.setF01(rs.getInt("F01"));
				obj.setF02(rs.getString("F02"));
				obj.setF03(rs.getString("F03"));
				obj.setF04(rs.getString("F04"));
				obj.setF05(rs.getString("F05"));
				obj.setF06(rs.getTimestamp("F06"));
				obj.setF07(rs.getTimestamp("F07"));
				obj.setF08(rs.getString("F08"));
				obj.setF09(rs.getString("F09"));
				obj.setF10(rs.getInt("F10"));
				obj.setF11(rs.getInt("F11"));
				obj.setF12(rs.getString("F12"));
				obj.setF13(rs.getInt("F13"));
				return obj;
			}
		}, F01);
		return singleResult(obj);
	}

	@Override
	public PageResult<B1006> search(Query query) throws Exception {
		PageResult<B1006> pageResult = new PageResult<B1006>();
		String wheresql = "where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (query.hasStr("name")) {
			wheresql += " and F02 like ? ";
			params.add("%" + query.getStr("name") + "%");
		}
		if (query.hasStr("realName")) {
			wheresql += " and F04 like ? ";
			params.add("%" + query.getStr("realName") + "%");
		}
		if (query.hasStr("createTimeStart")) {
			wheresql += " and F07 >=? ";
			params.add(query.getStr("createTimeStart"));
		}
		if (query.hasStr("createTimeEnd")) {
			wheresql += " and F07 <=? ";
			params.add(query.getStr("createTimeEnd"));
		}
		if (query.hasStr("status")) {
			wheresql += " and F05= ? ";
			params.add(query.getStr("status"));
		}

		String sql = "select F01,F02,F03,F04,F05,F06,F07,F08,F09,F10,F13 from lris.B1006 " + wheresql + " order by F01 limit ?,?";

		if (query.getPageIndex() == 1) {
			String countsql = "select count(1) from lris.B1006 " + wheresql;
			int count = this.getJdbcTemplate().queryForObject(countsql, Integer.class, params.toArray());
			query.getPage().setTotalCount(count);
		}
		params.add((query.getPageIndex() - 1) * query.getPageSize());
		params.add(query.getPageSize());

		pageResult.setPage(query.getPage());
		List<B1006> list = this.getJdbcTemplate().query(sql, params.toArray(), new RowMapper<B1006>() {
			@Override
			public B1006 mapRow(ResultSet rs, int rowNum) throws SQLException {
				/*B1006 obj = ;*/
			/*	obj.setF01(rs.getInt("F01"));
				obj.setF02(rs.getString("F02"));
				obj.setF03(rs.getString("F03"));
				obj.setF04(rs.getString("F04"));
				obj.setF05(rs.getString("F05"));
				obj.setF06(rs.getTimestamp("F06"));
				obj.setF07(rs.getTimestamp("F07"));
				obj.setF08(rs.getString("F08"));
				obj.setF09(rs.getString("F09"));
				obj.setF10(rs.getInt("F10"));*/
				B1006 obj = new B1006();
				obj.setF13(rs.getInt("F13"));
				return getB1006(obj,rs);
			}
		});
		pageResult.setItems((list == null || list.size() == 0) ? null : list.toArray(new B1006[list.size()]));
		return pageResult;
	}
    /*****
     * 登录操作  根据我名字去查询 数据返回一个实体类对象
     */
	@Override
	public B1006 getByUsername(String username) throws Exception {
		String sql = "select * from lris.B1006 where binary F02=?";
		List<B1006> list = this.getJdbcTemplate().query(sql, new RowMapper<B1006>() {
			//查询返回的结果集 
			@Override
			public B1006 mapRow(ResultSet rs, int rowNum) throws SQLException {
				
			/*	obj.setF01(rs.getInt("F01"));
				obj.setF02(rs.getString("F02"));
				obj.setF03(rs.getString("F03"));
				obj.setF04(rs.getString("F04"));
				obj.setF05(rs.getString("F05"));
				obj.setF06(rs.getTimestamp("F06"));
				obj.setF07(rs.getTimestamp("F07"));
				obj.setF08(rs.getString("F08"));
				obj.setF09(rs.getString("F09"));
				obj.setF10(rs.getInt("F10"));*/
				B1006 obj=getB1006(new B1006(),rs);
				obj.setF11(rs.getInt("F11"));
				obj.setF12(rs.getString("F12"));
				obj.setF13(rs.getInt("F13"));
				return obj;
			}
		}, username);
		return this.singleResult(list);
	}

	/**
	 * 修改密码
	 */
	@Override
	public void updatePwd(String F03, int F01) throws Exception {
		String pasSql = "update lris.B1006 set F03=? where F01=?";
		this.getJdbcTemplate().update(pasSql, F03, F01);
	}

	/**
	 * 检查登录名是否重复
	 */
	@Override
	public int checkF02(String F02) throws Exception {
		String sql = "select count(1) from lris.B1006 where binary   F02=? ";
		return getJdbcTemplate().queryForObject(sql, Integer.class, F02);
	}

	@Override
	public int getLoginErrorTime(String ip) throws Exception {
		List<Integer> list = getJdbcTemplate().query("select count(1) from D13.B1306 where F03=? and date(F04)=? and F05=1", new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getInt(1);
			}
		}, ip, new Date(System.currentTimeMillis()));
		return list == null ? 0 : list.get(0);
	}

	@Override
	public int getMessageWrongTime(int B1006id) throws Exception {
		List<Integer> list = getJdbcTemplate().query("select count(1) from D13.B1306 where F02=? and date(F04)=? and F05=1", new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getInt(1);
			}
		}, B1006id, new Date(System.currentTimeMillis()));
		return list == null ? 0 : list.get(0);
	}

	@Override
	public void updateLastLoginInfo(int B1006id, String ip) throws Exception {
		getJdbcTemplate().update("update lris.B1006 set F07=CURRENT_TIMESTAMP(),F08=? where F01=?", ip, B1006id);
	}

	@Override
	public List<String> getAuditUserTel() throws Exception {
		String sql = "select F09 from lris.B1006 where F11=1 and F09 is not null and F09!=''";
		List<String> list = getJdbcTemplate().query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString(1);
			}
		});
		return list;
	}
    
	/***
	 * 通用查询登录 返回登录通用对象
	 * @param obj
	 * @param rs
	 * @return 返回登录对象
	 */
	
	public B1006 getB1006(B1006 obj,ResultSet rs) {
		try {
			obj.setF01(rs.getInt("F01"));
			obj.setF02(rs.getString("F02"));
			obj.setF03(rs.getString("F03"));
			obj.setF04(rs.getString("F04"));
			obj.setF05(rs.getString("F05"));
			obj.setF06(rs.getTimestamp("F06"));
			obj.setF07(rs.getTimestamp("F07"));
			obj.setF08(rs.getString("F08"));
			obj.setF09(rs.getString("F09"));
			obj.setF10(rs.getInt("F10"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}
	
	
	
	
	@Override
	public int checkF11(int uid) throws Exception {
		String sql = "select F11 from lris.B1006 where F01 = ?";
		return this.getJdbcTemplate().queryForObject(sql, Integer.class, uid);
	}

	@Override
	public void updateSysUserSign(B1006 tb) throws Exception {
		String sql = "update lris.B1006 set F11=? where F01=?";
		this.getJdbcTemplate().update(sql, tb.getF11(), tb.getF01());
	}

	@Override
	public void unlock(int uid) throws Exception {
		String sql = "delete from D13.B1306 where F02 = ? and date(F04) = ? and F05 = 1 ";
		this.getJdbcTemplate().update(sql, uid ,new Date(System.currentTimeMillis()));
	}

}