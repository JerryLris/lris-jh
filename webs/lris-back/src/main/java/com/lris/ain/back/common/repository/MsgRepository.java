package com.lris.ain.back.common.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lris.ain.back.entities.B1091;
import com.lris.ain.core.data.jdbc.PubDao;

@Repository
public class MsgRepository extends PubDao{
	
	/**
	 * 发送短信
	 * @param type 类型
	 * @param msg 内容
	 * @param tels 号码列表
	 */
	public int sendSms(final B1091 b1091) throws Exception {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new PreparedStatementCreator() {
		    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		    	String sql = "INSERT INTO lris.B1091(F02,F03,F04,F05,F06,F09) values(?,?,?,?,now(),?)";
		        PreparedStatement ps = connection.prepareStatement(sql,	Statement.RETURN_GENERATED_KEYS);
		        ps.setInt(1, b1091.getF02());
				ps.setString(2, b1091.getF03());
				ps.setString(3, b1091.getF04());
				ps.setInt(4, b1091.getF05());
				ps.setString(5, b1091.getF09());
		        return ps;
		      }
		    }, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	/**
	 * 更新短信下发结果
	 * @param F01
	 * @param F07
	 * @throws Exception
	 */
	public void updateSmsReturn(int F01,String F07,String F10) throws Exception {
		String sql = "update lris.B1091 set F07=?,F10=? where F01=?";
		this.getJdbcTemplate().update(sql,F07,F10,F01);
	}
	
	/**
	 * 更新短信发送用户手机结果
	 * @param F01
	 * @param F08
	 * @throws Exception
	 */
	public void updateSmsSendResult(int F01,String F08) throws Exception {
		String sql = "update lris.B1091 set F08=? where F01=?";
		this.getJdbcTemplate().update(sql,F08,F01);
	}
	
	/**
	 * 发送邮件
	 * @param type
	 * @param subject
	 * @param content
	 * @param addresses
	 */
	public void sendMail(final int type, final String subject, final String content, final String[] addresses) throws Exception{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new PreparedStatementCreator() {
		    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		    	String sql = "INSERT INTO S10._1046(F02,F03,F04,F05,F07) VALUES(?,?,?,?,?)";
		        PreparedStatement ps = connection.prepareStatement(sql,	Statement.RETURN_GENERATED_KEYS);
		        ps.setString(1, subject);
		        ps.setString(2, content);
		        ps.setInt(3, type);
		        ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
		        ps.setString(5, "W");
		        return ps;
		      }
		    }, keyHolder);
		if(keyHolder!=null){
			final long id = keyHolder.getKey().longValue();
			if(id>0){
				String sql = "INSERT INTO S10._1047(F01,F02) VALUES(?,?)";
				this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter(){

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setLong(1, id);
						ps.setString(2, addresses[i]);
					}

					@Override
					public int getBatchSize() {
						return addresses.length;
					}
				});
			}
		}
	}
	
	/**
	 * 发送站内信
	 * @param userid
	 * @param subject
	 * @param content
	 */
	public void sendLetter(int userid, String subject, String content) throws Exception{
		String sql = "insert into lris.B1130 set F02=?,F03=?,F04=?,F05=now(),F07=0";
		getJdbcTemplate().update(sql, userid, subject, content);
	}
	
	/**
	 * 查询短信发送次数
	 * @param F01
	 * @param F07
	 * @throws Exception
	 */
	public int getSentCount(B1091 tab) throws Exception {
		String sql = "select count(1) from lris.B1091 where F02=? and date_format(F06,'%Y-%m-%d')=curdate() and F04 like ?";
		return this.getJdbcTemplate().queryForObject(sql, Integer.class, tab.getF02(),"%"+tab.getF04()+"%");
	}
}
