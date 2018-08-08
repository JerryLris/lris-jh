package com.lris.ain.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lris.ain.back.sys.vo.Variable;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试  
@ContextConfiguration(locations={"classpath*:spring/spring.xml"}) //加载配置文件  
public class TestDB {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	
	@Test
	public void test() {
		Variable var = new Variable();
		var.setKey("HEHE");
		var.setVal("这是呵呵变量值");
		var.setDesc("这是TEST呵呵变量值");
		var.setType("TEST");
		String sql = "insert into B1001 set F01=?,F02=?,F03=?,F04=? ";
		jdbcTemplate.update(sql, var.getKey(), var.getVal(), var.getType(), var.getDesc());
		
	}
/*		String sql = "select t1.F02 mobile,t1.F04 name,t1.F06 idcard,t2.F06 bankcard from d11.b1104 t2 left join d11.b1101 t1 on t1.F01 = t2.F02 where t2.F11 = '建设银行'";
		List<Map<String, Object>> objs = jdbcTemplate.query(sql, new RowMapper<Map<String, Object>>(){
			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String, Object> obj = new HashMap<>();
				obj.put("name", rs.getString("name"));
				obj.put("mobile", rs.getString("mobile"));
				obj.put("idcard", CryptoHelper.decode(rs.getString("idcard")));
				obj.put("bankcard", CryptoHelper.decode(rs.getString("bankcard")));
				return obj;
			}
		});
		int num = 1;
		for(Map<String, Object> map:objs) {
			System.out.println("===第"+num+"条记录==========");
			for(String key:map.keySet()) {
				System.out.println("key="+key+",value="+map.get(key));
			}
			num++;
		}
		
	}*/
}
