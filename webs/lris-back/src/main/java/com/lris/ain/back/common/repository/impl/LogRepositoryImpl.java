package com.lris.ain.back.common.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lris.ain.back.common.repository.LogRepository;
import com.lris.ain.back.entities.B1091;
import com.lris.ain.back.entities.B1304;
import com.lris.ain.back.entities.B1305;
import com.lris.ain.back.entities.B1306;
import com.lris.ain.back.sys.vo.BackGroundOperationLog;
import com.lris.ain.back.sys.vo.UserOperationLog;
import com.lris.ain.core.data.jdbc.PubDao;
import com.lris.ain.core.query.PageResult;
import com.lris.ain.core.query.Query;

@Repository
public class LogRepositoryImpl extends PubDao implements LogRepository {

	public PageResult<BackGroundOperationLog> searchBackGroundOperation(Query query) throws Exception {
		PageResult<BackGroundOperationLog> pageResult = new PageResult<BackGroundOperationLog>();
		String wheresql = "where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (query.hasStr("username")) {
			wheresql += " and t2.F02 like ? ";
			params.add("%" + query.getStr("username") + "%");
		}
		if (query.hasStr("describe")) {
			wheresql += " and t1.F06 like ? ";
			params.add("%" + query.getStr("describe") + "%");
		}
		if (query.hasStr("timestart")) {
			wheresql += " and t1.F03 >= ? ";
			params.add(query.getStr("timestart") + " 00:00:00");
		}
		if (query.hasStr("timeend")) {
			wheresql += " and t1.F03 <= ? ";
			params.add(query.getStr("timeend") + " 23:59:59");
		}

		if (query.getPageIndex() == 1) {
			String countsql = "select count(1) from D13.B1305 t1 " + "left join D10.B1006 t2 on t2.F01=t1.F02 " + wheresql;
			int count = this.getJdbcTemplate().queryForObject(countsql, Integer.class, params.toArray());
			query.getPage().setTotalCount(count);
		}

		String sql = "select t2.F02,t1.F03,t1.F06 from D13.B1305 t1 " + "left join D10.B1006 t2 on t2.F01=t1.F02 " + wheresql;
		sql += " order by t1.F01 desc limit ?,?";
		params.add((query.getPageIndex() - 1) * query.getPageSize());
		params.add(query.getPageSize());
		pageResult.setPage(query.getPage());
		List<BackGroundOperationLog> list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new RowMapper<BackGroundOperationLog>() {
			@Override
			public BackGroundOperationLog mapRow(ResultSet rs, int rowNum) throws SQLException {
				BackGroundOperationLog obj = new BackGroundOperationLog();
				obj.setUsername(rs.getString("F02"));
				obj.setF03(rs.getTimestamp("F03"));
				obj.setF06(rs.getString("F06"));
				return obj;
			}
		});
		pageResult.setItems((list == null || list.size() == 0) ? null : list.toArray(new BackGroundOperationLog[list.size()]));
		return pageResult;
	}

	@Override
	public PageResult<UserOperationLog> searchUserOperation(Query query) throws Exception {
		PageResult<UserOperationLog> pageResult = new PageResult<UserOperationLog>();
		String wheresql = "where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (query.hasStr("username")) {
			wheresql += " and t2.F04 like ? ";
			params.add("%" + query.getStr("username") + "%");
		}
		if (query.hasStr("f02")) {
			wheresql += " and t1.F02 like ? ";
			params.add("%" + query.getStr("f02") + "%");
		}
		if (query.hasStr("f03")) {
			wheresql += " and t1.F03 = ? ";
			params.add(query.getStr("f03"));
		}
		if (query.hasStr("timestart")) {
			wheresql += " and t1.F04 >= ? ";
			params.add(query.getStr("timestart") + " 00:00:00");
		}
		if (query.hasStr("timeend")) {
			wheresql += " and t1.F04 <= ? ";
			params.add(query.getStr("timeend") + " 23:59:59");
		}
		if (query.hasStr("f05")) {
			wheresql += " and t1.F05 = ? ";
			params.add(query.getStr("f05"));
		}
		if (query.hasStr("f11")) {
			wheresql += " and t1.F11 like ? ";
			params.add("%" + query.getStr("f11") + "%");
		}
		if (query.hasStr("f06")) {
			wheresql += " and t1.F06 = ? ";
			params.add(query.getInt("f06"));
		}

		if (query.getPageIndex() == 1) {
			String countsql = "select count(1) from D13.B1302 t1 " + "left join D11.B1101 t2 on t2.F01=t1.F08 " + wheresql;
			int count = this.getJdbcTemplate().queryForObject(countsql, Integer.class, params.toArray());
			query.getPage().setTotalCount(count);
		}

		String sql = "select t2.F04 username,t1.F02,t1.F03,t1.F04,t1.F05,t1.F06,t1.F07,t1.F08,t1.F09,t1.F10,t1.F11 from D13.B1302 t1" + " left join D11.B1101 t2 on t2.F01=t1.F08 " + wheresql;
		sql += " order by t1.F01 desc limit ?,?";
		params.add((query.getPageIndex() - 1) * query.getPageSize());
		params.add(query.getPageSize());
		pageResult.setPage(query.getPage());
		List<UserOperationLog> list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new RowMapper<UserOperationLog>() {
			@Override
			public UserOperationLog mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserOperationLog obj = new UserOperationLog();
				obj.setUsername(rs.getString("username"));
				obj.setF02(rs.getString("F02"));
				obj.setF03(rs.getString("F03"));
				obj.setF04(rs.getTimestamp("F04"));
				obj.setF05(rs.getString("F05"));
				obj.setF06(rs.getInt("F06"));
				obj.setF07(rs.getString("F07"));
				obj.setF08(rs.getInt("F08"));
				obj.setF09(rs.getString("F09"));
				obj.setF10(rs.getString("F10"));
				obj.setF11(rs.getString("F11"));
				return obj;
			}
		});
		pageResult.setItems((list == null || list.size() == 0) ? null : list.toArray(new UserOperationLog[list.size()]));
		return pageResult;
	}
	
	@Override
	public PageResult<B1091> searchMsgSent(Query query) throws Exception {
		PageResult<B1091> pageResult = new PageResult<B1091>();
		String wheresql = "where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (query.hasStr("content")) {
			wheresql += " and f03 like ? ";
			params.add("%" + query.getStr("content") + "%");
		}
		if (query.hasStr("phone")) {
			wheresql += " and f04 like ? ";
			params.add("%" + query.getStr("phone") + "%");
		}

		if (query.getPageIndex() == 1) {
			String countsql = "select count(1) from D10.B1091 "+ wheresql;
			int count = this.getJdbcTemplate().queryForObject(countsql, Integer.class, params.toArray());
			query.getPage().setTotalCount(count);
		}

		String sql = "select F01,F02,F03,F04,F05,F06,F07,F08,F09,F10 from D10.B1091 " + wheresql + "order by F01 desc limit ?,?";
		params.add((query.getPageIndex() - 1) * query.getPageSize());
		params.add(query.getPageSize());
		pageResult.setPage(query.getPage());
		List<B1091> list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new RowMapper<B1091>() {
			@Override
			public B1091 mapRow(ResultSet rs, int rowNum) throws SQLException {
				B1091 obj = new B1091();
				obj.setF01(rs.getInt("F01"));
				obj.setF02(rs.getInt("F02"));
				obj.setF03(rs.getString("F03"));
				obj.setF04(rs.getString("F04"));
				obj.setF05(rs.getInt("F05"));
				obj.setF06(rs.getTimestamp("F06"));
				obj.setF07(rs.getString("F07"));
				obj.setF08(rs.getString("F08"));
				obj.setF09(rs.getString("F09"));
				obj.setF10(rs.getString("F10"));
				return obj;
			}
		});
		pageResult.setItems((list == null || list.size() == 0) ? null : list.toArray(new B1091[list.size()]));
		return pageResult;
	}

	@Override
	public void logLogin(B1306 b1306) throws Exception {
		getJdbcTemplate().update("insert into D13.B1306(F02,F03,F04,F05,F06,F07,F08) values(?,?,?,?,?,?,?)", //
				b1306.getF02(), b1306.getF03(), b1306.getF04(), b1306.getF05(), b1306.getF06(), b1306.getF07(), b1306.getF08());
	}

	@Override
	public void logReq(B1304 b1304) throws Exception {
		getJdbcTemplate().update("insert into D13.b1304(F02,F03,F04,F05,F06,F07,F08,F09,F10,F11,F12) values(?,?,?,?,?,?,?,?,?,?,?)",//
				b1304.getF02(), b1304.getF03(), b1304.getF04(), b1304.getF05(), b1304.getF06(), b1304.getF07(), b1304.getF08(), //
				b1304.getF09(), b1304.getF10(), b1304.getF11(), b1304.getF12());
	}

	@Override
	public void logChange(B1305 b1305) throws Exception {
		getJdbcTemplate().update("insert into D13.b1305(F02,F03,F06) values(?,?,?)", b1305.getF02(), b1305.getF03(), b1305.getF06());
	}
	
}
