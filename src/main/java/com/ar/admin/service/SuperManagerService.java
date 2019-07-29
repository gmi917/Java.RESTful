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

import com.ar.admin.md5;
import com.ar.admin.bean.AdminMember;
import com.ar.admin.bean.AdminMemberMapper;
import com.ar.admin.bean.Category;
import com.ar.admin.bean.CategoryMapper;
import com.ar.admin.bean.Image;
import com.ar.admin.bean.ImageMapper;

@Service
public class SuperManagerService {
	@Autowired
    public JdbcTemplate jdbcTemplate;
	
	public List<Category> getAllCategorys() {

        String sql = "SELECT * FROM category";

        List<Category> categorys = jdbcTemplate.query(sql, 
                new BeanPropertyRowMapper(Category.class));
        return categorys;
    }
	
	public List<Category> getCategorybyId(final String id){
    	String sql = "SELECT * FROM category where ID=?";
    	List<Category> categorys = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, id);
            }
        }, new CategoryMapper());
        return categorys;
    }
	
	public List<Category> insertCategory(Category category,String user){
    	String sql="insert into category (CategoryName,CreateUser) values(?,?)";
    	jdbcTemplate.update(sql,new Object[]{category.getCategoryName(),user});
    	List categorys= this.getAllCategorys();
    	return categorys;
    }
	
	public void updateCategory(Category category){
    	String sql = "update category set CategoryName=? where ID=?";
    	jdbcTemplate.update(sql,category.getCategoryName(),category.getID());
//    	List categorys= this.getAllCategorys();
//    	return categorys;
    }
	
	public void deleteCategorybyId(String id) {
    	String sql = "delete from category where ID = ?";
    	jdbcTemplate.update(sql, id);
    }
	
	public List<AdminMember> getAllAdminMembers() {

        String sql = "SELECT * FROM AdminMember";

        List<AdminMember> adminMembers = jdbcTemplate.query(sql, 
                new BeanPropertyRowMapper(AdminMember.class));
        return adminMembers;
    }
	
	public List<AdminMember> getAdminMemberbyId(final String id){
    	String sql = "SELECT * FROM AdminMember where ID=?";
    	List<AdminMember> adminMembers = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, id);
            }
        }, new AdminMemberMapper());
        return adminMembers;
    }
	
	public List<AdminMember> insertAdminMember(AdminMember adminMember,String user){
    	String sql="insert into AdminMember (MemberID,MemberPWD,MemberName,Role,MemberNumber,CreateUser) values(?,?,?,?,?,?)";
    	jdbcTemplate.update(sql,new Object[]{adminMember.getMemberID(),md5.md5(adminMember.getMemberPWD()),adminMember.getMemberName(),
    			adminMember.getMemberNumber(),"admin",user});
    	List adminMembers= this.getAllAdminMembers();
    	return adminMembers;
    }
	
	public void updateAdminMember(AdminMember adminMember){
    	String sql = "update AdminMember set MemberName=?,MemberNumber=? where ID=?";
    	jdbcTemplate.update(sql,adminMember.getMemberName(),adminMember.getMemberNumber(),adminMember.getID());
//    	List adminMembers= this.getAllAdminMembers();
//    	return adminMembers;
    }
	
	public void deleteAdminMemberbyId(String id) {
    	String sql = "delete from AdminMember where ID = ?";
    	jdbcTemplate.update(sql, id);
    }
	
	public void deletePrizebyUser(String user) {
    	String sql = "delete from Prize where CreateUser = ?";
    	jdbcTemplate.update(sql, user);
    }
	
	public boolean findCategoryID(String CategoryId) {
		String sql = "SELECT * FROM Prize WHERE CategoryID=" + CategoryId;
	    return jdbcTemplate.query(sql, new ResultSetExtractor<Boolean>() {
	 
	        @Override
	        public Boolean extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	return true;
	            }
				return false;
	        }	 
	    });
	}
	
	public boolean findMemberID(String MemberId) {
		String sql = "SELECT * FROM AdminMember WHERE MemberID=?";
	    return jdbcTemplate.query(sql, new Object[] { MemberId }, new ResultSetExtractor<Boolean>() {
	 
	        @Override
	        public Boolean extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	return true;
	            }
				return false;
	        }	 
	    });
	}
	
	public List<Image> getImgbyId(final String user){
		String sql = "select Image from Prize where CreateUser=?";
		List<Image> Images = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user);
            }
        }, new ImageMapper());
		return Images;
	}
}
