package com.lris.ain.back.sys.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lris.ain.back.sys.repository.RightRepository;
import com.lris.ain.back.sys.vo.Right;
import com.lris.ain.core.data.jdbc.PubDao;


@Repository
public class RightRepositoryImpl extends PubDao implements RightRepository {

	@Override
	public List<Right> getUserRights(int t7110id) throws Exception {
		return getJdbcTemplate().query("select distinct t3.F02,t3.F01,t3.F04 "
				+ "from lris.B1005 t1 inner join lris.B1004 t2 on t1.F02=t2.F01 "
				+ "inner join lris.B1002 t3 on t2.F02=t3.F01 "
				+ "where t1.F01=? and t3.F05=1 order by t3.F02", new RowMapper<Right>() {
			@Override
			public Right mapRow(ResultSet rs, int i) throws SQLException {
				Right right = new Right();
				right.setF01(rs.getString("F01"));
				right.setF02(rs.getString("F02"));
				right.setF04(rs.getString("F04"));
				return right;
			}
		}, t7110id);
	}

	@Override
	public List<String> getUserSecondRights(int t7110id) throws Exception {
		return getJdbcTemplate().query("select distinct t3.F01 from lris.B1005 t1 "
				+ "inner join lris.B1004 t2 on t1.F02=t2.F01 "
				+ "inner join lris.B1002 t3 on t2.F02=t3.F01 "
				+ "where t1.F01=? and t3.F05=2 order by t3.F02", new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int i) throws SQLException {
				return rs.getString(1);
			}
		}, t7110id);
	}

}