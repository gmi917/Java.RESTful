package com.ar.admin.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.ar.admin.bean.Category;
import com.ar.admin.bean.Prize;
import com.ar.admin.bean.PrizeMapper;

@Service
public class ManagerService {
	@Autowired
    public JdbcTemplate jdbcTemplate;
	
	public String getPrizeID() {
		String sql = "select auto_increment from information_schema.tables where table_schema='ar' and table_name='prize'";
		String id = jdbcTemplate.queryForObject(sql, java.lang.String.class);
		return id;
	}
	
	public List<Prize> getAllPrizes(final String user) {

        String sql = "SELECT * FROM prize p,Category c where p.CreateUser=? and p.CategoryID=c.ID order by p.id asc";

        List<Prize> prizes = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user);
            }
        }, new PrizeMapper());
        return prizes;
    }
	
	public List<Category> getAllCategorys() {

        String sql = "SELECT * FROM category order by id asc";

        List<Category> categorys = jdbcTemplate.query(sql, 
                new BeanPropertyRowMapper(Category.class));
        return categorys;
    }
	
	public List<Prize> getPrizebyId(final String id){
    	String sql = "SELECT * FROM prize p,Category c where p.ID=? and p.CategoryID=c.ID";
    	List<Prize> prizes = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, id);
            }
        }, new PrizeMapper());
        return prizes;
    }
	
	public void insertPrize(Prize prize,String admin){
    	String sql="insert into prize (CategoryID,PrizeName,PrizeDescription,Point,State,Image,CreateUser) values(?,?,?,?,?,?,?)";
    	jdbcTemplate.update(sql,new Object[]{prize.getCategoryID(),prize.getPrizeName(),prize.getPrizeDescription(),
    			prize.getPoint(),prize.getState(),prize.getImage(),admin});
//    	List prizes= this.getAllPrizes(prize.getCreateUser());
//    	return prizes;
    }
	
	public List<Prize> updatePrize(Prize prize){
    	String sql = "update prize set CategoryID=?,PrizeName=?,PrizeDescription=?,Point=?,State=?,Image=? where ID=?";
    	jdbcTemplate.update(sql,prize.getCategoryID(),prize.getPrizeName(),prize.getPrizeDescription(),
    			prize.getPoint(),prize.getState(),prize.getImage(),prize.getID());
    	List prizes= this.getPrizebyId(prize.getID());
    	return prizes;
    }
	
	public void deletePrizebyId(String id) {
    	String sql = "delete from Prize where ID = ?";
    	jdbcTemplate.update(sql, id);
    }
	
	public String  getImgbyId(String id) {
    	String sql = "select Image from Prize where ID = ?";
    	return jdbcTemplate.query(sql, new Object[] { id }, new ResultSetExtractor<String>() {
    	    @Override
    	    public String extractData(ResultSet rs) throws SQLException, DataAccessException {
    	        if (rs.next()) {
    	            return rs.getString("Image");
    	        }
    	        return null;
    	    }
    	});
    }
	
	public void updatePWD(String user,String password_md5) {
		String sql="update AdminMember set MemberPWD=? where MemberID=?";
		jdbcTemplate.update(sql,password_md5,user);
	}
	
	public String getPWDbyId(String user) {
		String sql = "select MemberPWD FROM AdminMember where MemberID=?";
		return jdbcTemplate.query(sql, new Object[] { user }, new ResultSetExtractor<String>() {
    	    @Override
    	    public String extractData(ResultSet rs) throws SQLException, DataAccessException {
    	        if (rs.next()) {
    	            return rs.getString("MemberPWD");
    	        }
    	        return null;
    	    }
    	});
	}
}
