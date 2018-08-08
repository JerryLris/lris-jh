package com.lris.ain.back.sys.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lris.ain.back.sys.repository.MenuRepository;
import com.lris.ain.back.sys.vo.Menu;
import com.lris.ain.core.data.jdbc.PubDao;



@Repository
public class MenuRepositoryImpl extends PubDao implements MenuRepository {

	@Override
	public void insertMenu(Menu tb) throws Exception {
		String sql = "insert into lris.B1004 set F01=?,F02=? ";
		this.getJdbcTemplate().update(sql, tb.getRoleId(), tb.getF01());
	}

	/**
	 * 查询父功能代号，分类
	 */
	@Override
	public List<Menu> getRightF02(int F01) throws Exception {
		String sql = "select F02,F04 from lris.B1002 where F05=1  group by F02 ";
		List<Menu> objs = this.getJdbcTemplate().query(sql,new RowMapper<Menu>() {
			@Override
			public Menu mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Menu obj = new Menu();
				obj.setF02(rs.getString("F02"));
				obj.setF04(rs.getString("F04"));
				return obj;
			}
		});
		return objs;
	}


	@Override
	public List<Menu> getByID(int F01) throws Exception {
		String sql = "select t2.F01 roleId,t1.F01 privilegeId,t1.F02,t1.F04 from lris.B1002 t1 "
				+ "left join lris.B1004 t2 on t2.F02=t1.F01 and t2.F01=?  "
				+ "where t1.F05=1 order by t1.F02 ";
		List<Menu> objs = this.getJdbcTemplate().query(sql,new RowMapper<Menu>() {
			@Override
			public Menu mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Menu obj = new Menu();
				obj.setRoleId(rs.getInt(rs.getInt("roleId")));
				obj.setPrivilegeId(rs.getString("privilegeId"));
				obj.setF02(rs.getString("F02"));
				obj.setF04(rs.getString("F04"));
				return obj;
			}
		}, F01);
		return objs;
	}


	/**
	 * 查询菜单和操作的对应的模块和Action类
	 * @param  F05	权限类型:1=菜单;2=操作
	 */
	@Override
	public List<String> getParentModuleMenu(int F05) throws Exception {
		String queryRight = "select distinct F02 from lris.B1002 where F05=?";
		return this.getJdbcTemplate().queryForList(queryRight, String.class, F05);
	}
	
	/**
	 * 根据级别和父功能代号查询该父功能下的所有功能菜单
	 * @param  F02 	父功能代号
	 * @param  F01 	角色ID
	 * @param  F05	权限类型:1=菜单;2=操作
	 */
	@Override
	public List<Menu> getModuleMenu(String F02, int F05, int F01) throws Exception{
		String queryRight = "select t1.F01 privilegeId,t1.F04,t2.F01 roleId from lris.B1002 t1 "
				+ " left outer join lris.B1004 t2 on t2.F02=t1.F01 and t2.F01=? "
				+ " where t1.F05=? and t1.F02=?";
		List<Menu> list = this.getJdbcTemplate().query(queryRight,
				new RowMapper<Menu>() {
					@Override
					public Menu mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Menu obj = new Menu();
						obj.setRoleId(rs.getInt("roleId"));
						obj.setPrivilegeId(rs.getString("privilegeId"));
						obj.setF04(rs.getString("F04"));
						return obj;
					}
				}, F01, F05, F02);
		return list;
	}
	
	/**
	 * 更新角色的功能和操作权限
	 * @param  F02s 权限
	 * @param  F01 	角色ID
	 * @param  F05	权限类型:1=菜单;2=操作
	 */
	@Override
	public void updateMenu(String[] F02s, int F01, int F05) throws Exception{
		String sql = "delete from lris.B1004 where F01=? and F02 in (select F01 from lris.B1002 where F05=?)";
		getJdbcTemplate().update(sql, F01, F05);
		if(F02s!=null && F02s.length>0){
			String insertsql = "insert into lris.B1004 set F01=?,F02=?";
			List<Object[]> list = new ArrayList<Object[]>();
			for (String F02 : F02s) {
				list.add(new Object[]{F01,F02});
			}
			getJdbcTemplate().batchUpdate(insertsql, list);
		}
	}
	
	/**
	 * 根据角色ID查询该角色的菜单
	 * @param  F01 	角色ID
	 * @param  F05	权限类型:1=菜单;2=操作
	 */
	@Override
	public List<Menu> getMenusByRole(String F02, int F01, int F05) throws Exception {
		String queryRight = "select t1.F01 privilegeId,t1.F04,t2.F01 roleId from lris.B1002 t1 "
				+ " left outer join lris.B1004 t2 on t2.F02=t1.F01  "
				+ " where t2.F01=? and t1.F05=? and t1.F02=?";
		List<Menu> list = this.getJdbcTemplate().query(queryRight,
				new RowMapper<Menu>() {
					@Override
					public Menu mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Menu obj = new Menu();
						obj.setRoleId(rs.getInt("roleId"));
						obj.setPrivilegeId(rs.getString("privilegeId"));
						obj.setF04(rs.getString("F04"));
						return obj;
					}
				}, F01, F05, F02);
		return list;
	}
	
	/**
	 * 根据角色ID查询该角色的模块类型
	 * @param  F01 	角色ID
	 * @param  F05	权限类型:1=菜单;2=操作
	 */
	@Override
	public List<String> getModuleByRole(int F01, int F05) throws Exception {
		String queryRight = " select distinct t2.F02 from lris.B1004 t1 "
				+ " left outer join lris.B1002 t2 on t2.F01=t1.F02 "
				+ " where t1.F01=? and t2.F05=?  ";
		return this.getJdbcTemplate().queryForList(queryRight, String.class, F01, F05);
	}

}