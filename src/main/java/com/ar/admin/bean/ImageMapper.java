package com.ar.admin.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ImageMapper implements RowMapper<Image>{

	@Override
	public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Image image = new Image();
		image.setImage(rs.getString("Image"));
		return image;
	}

}
