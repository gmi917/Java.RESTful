package com.ar.admin.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PrizeMapper implements RowMapper<Prize>  {
	public Prize mapRow(ResultSet rs, int rowNum) throws SQLException {
		Prize prize = new Prize();
		prize.setID(rs.getString("ID"));	
		prize.setCategoryID(rs.getString("CategoryID"));
		prize.setCategoryName(rs.getString("CategoryName"));
		prize.setPrizeName(rs.getString("PrizeName"));
		prize.setPrizeDescription(rs.getString("PrizeDescription"));
		prize.setPoint(rs.getString("Point"));
		prize.setState(rs.getString("State"));
		prize.setImage(rs.getString("Image"));
		prize.setCreateUser(rs.getString("CreateUser"));
		return prize;
	}

}
