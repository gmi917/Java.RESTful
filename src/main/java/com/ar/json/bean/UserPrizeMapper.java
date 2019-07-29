package com.ar.json.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserPrizeMapper implements RowMapper<UserPrize> {
	public UserPrize mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserPrize userPrize = new UserPrize();
		userPrize.setID(rs.getString("ID"));
		userPrize.setCategoryID(rs.getString("CategoryID"));
		userPrize.setPrizeName(rs.getString("PrizeName"));
		userPrize.setPoint(rs.getString("Point"));
		userPrize.setImage(rs.getString("Image"));
		return userPrize;
	}

}
