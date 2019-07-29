package com.ar.admin.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdminMemberMapper implements RowMapper<AdminMember>  {
	public AdminMember mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdminMember adminmember = new AdminMember();
		adminmember.setMemberID(rs.getString("MemberID"));	
		adminmember.setMemberPWD(rs.getString("MemberPWD"));
		adminmember.setMemberName(rs.getString("MemberName"));
		adminmember.setRole(rs.getString("Role"));
		adminmember.setCreateUser(rs.getString("CreateUser"));
		return adminmember;
	}

}
