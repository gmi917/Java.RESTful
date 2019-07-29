package com.ar.json.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TotalPointMapper implements RowMapper<TotalPoint> {
	public TotalPoint mapRow(ResultSet rs, int rowNum) throws SQLException {
		TotalPoint totalPoint = new TotalPoint();
		totalPoint.setTotalPoint(rs.getString("TotalPoint"));
		return totalPoint;
	}

}
