package com.ar.json.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PrizeMapper implements RowMapper<Prize> {
	public Prize mapRow(ResultSet rs, int rowNum) throws SQLException {
		Prize prize=new Prize();
		prize.setId(rs.getString("ID"));
		prize.setImage(rs.getString("Image"));
		prize.setPrizeName(rs.getString("PrizeName"));
		return prize;
	}

}
