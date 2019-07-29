package com.ar.admin.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.ar.admin.bean.Login;
import com.ar.admin.bean.LoginMapper;


@Service
public class LoginService {
	@Autowired
    public JdbcTemplate jdbcTemplate;
	
	public List<Login> getUserbyId(final String username,final String pwd){
    	String sql = "SELECT * FROM AdminMember where MemberID=? and MemberPWD=?";
    	List<Login> user = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, pwd);
            }
        }, new LoginMapper());
        return user;
    }
	
}
