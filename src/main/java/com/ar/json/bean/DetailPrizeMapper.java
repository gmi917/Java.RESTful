package com.ar.json.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ar.json.bean.DetailPrize;

public class DetailPrizeMapper implements RowMapper<DetailPrize>{
	public DetailPrize mapRow(ResultSet rs, int rowNum) throws SQLException {
		DetailPrize detailPrize = new DetailPrize();
		detailPrize.setID(rs.getString("ID"));	
		detailPrize.setCategoryID(rs.getString("CategoryID"));
		detailPrize.setCategoryName(rs.getString("CategoryName"));
		detailPrize.setPrizeName(rs.getString("PrizeName"));
		detailPrize.setPrizeDescription(rs.getString("PrizeDescription"));
		detailPrize.setPoint(rs.getString("Point"));
		detailPrize.setState(rs.getString("State"));
		detailPrize.setImage(rs.getString("Image"));		
		return detailPrize;
	}

}
