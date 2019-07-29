package com.ar.admin.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class LoginMapper implements RowMapper<Login> {
	public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
		Login login = new Login();
		login.setMemberID(rs.getString("MemberID"));
		login.setRole(rs.getString("Role"));		
		return login;
	}

}
