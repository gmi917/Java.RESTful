package com.ar.json.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ar.admin.md5;
import com.ar.admin.bean.Category;
import com.ar.json.bean.PrizeMapper;
import com.ar.json.bean.TotalPointMapper;
import com.ar.json.bean.AllPrizeList;
import com.ar.json.bean.DetailPrize;
import com.ar.json.bean.DetailPrizeMapper;
import com.ar.json.bean.Prize;
import com.ar.json.bean.TotalPoint;
import com.ar.json.bean.User;
import com.ar.json.bean.UserPrize;
import com.ar.json.bean.UserPrizeMapper;

@Service
public class UserService {
	@Autowired
    public JdbcTemplate jdbcTemplate;
	
	public boolean login(String username,String password) {
		String sql = "select * from UserMember where UserAccount = ? and UserPWD=?";
    	return jdbcTemplate.query(sql, new Object[] { username,password }, new ResultSetExtractor<Boolean>() {
    	    @Override
    	    public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
    	        if (rs.next()) {
    	            return true;
    	        }
    	        return false;
    	    }
    	});
	}
	
	public boolean checkUser(String userAccount) {
		String sql = "select * from UserMember where UserAccount=?";
		return jdbcTemplate.query(sql, new Object[] { userAccount }, new ResultSetExtractor<Boolean>() {
    	    @Override
    	    public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
    	        if (rs.next()) {
    	            return true;
    	        }
    	        return false;
    	    }
    	});
	}
	
	public void addUser(User user) {
		String sql="insert into UserMember (UserAccount,UserPWD,UserName,Email,Tel) values(?,?,?,?,?)";
		String password_md5 = md5.md5(user.getUserPWD().trim());
    	jdbcTemplate.update(sql,new Object[]{user.getUserAccount(),password_md5.toUpperCase(),user.getUserName(),user.getEmail(),user.getTel()});
	}
	
	public List<UserPrize> getAllPrizes() {

        String sql = "SELECT * FROM Prize where State=0";

        List<UserPrize> userPrizes = jdbcTemplate.query(sql, 
                new BeanPropertyRowMapper(UserPrize.class));
        return userPrizes;
    }
	
	public List<AllPrizeList> getAllPrizeList(){
		String sql="SELECT * FROM prize p,Category c where c.ID=? and p.CategoryID=c.ID and p.State=0";
		List<AllPrizeList> prizeList = new ArrayList<AllPrizeList>(); 
		final List<Category> category=this.getAllCategory();
		AllPrizeList allPrizeList;
		if(category!=null && category.size()>0) {
			for(int i=0;i<category.size();i++) {
				final String id=category.get(i).getID();
				allPrizeList= new AllPrizeList();
				allPrizeList.setCategoryName(category.get(i).getCategoryName());
				List<Prize> prize = jdbcTemplate.query(sql, new PreparedStatementSetter() {
		            public void setValues(PreparedStatement preparedStatement) throws SQLException {
		                preparedStatement.setString(1, id);
		            }
		        }, new PrizeMapper());
				if(prize!=null && prize.size()>0) {
					allPrizeList.setPrize(prize);
					prizeList.add(allPrizeList);					
				}				
			}
		}
		return prizeList;
	}
	
	public List<DetailPrize> getPrizeDetailbyId(final String id){
    	String sql = "SELECT * FROM prize p,Category c where p.ID=? and p.CategoryID=c.ID and p.State=0";
    	List<DetailPrize> detailPrize = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, id);
            }
        }, new DetailPrizeMapper());
        return detailPrize;
    }
	
	public List<TotalPoint> getTotalPointbyUsername(final String username){
		String sql = "SELECT TotalPoint FROM UserMember where UserAccount=?";
    	List<TotalPoint> totalPoint = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, username);
            }
        }, new TotalPointMapper());
        return totalPoint;
	}
	
	public List<Category> getAllCategory(){
		String sql = "select * from Category";
		List<Category> categorys= jdbcTemplate.query(sql, 
                new BeanPropertyRowMapper(Category.class));
        return categorys;
	}
	
	public List<UserPrize> getPrizebyCategoryId(final String id){
		String sql = "select * from Prize where CategoryID=? and State=0";
		List<UserPrize> userPrizes=jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, id);
            }
        }, new UserPrizeMapper());
		return userPrizes;
	}
	
	@Transactional(rollbackFor=Exception.class, propagation = Propagation.REQUIRES_NEW)
	public boolean insertPrizeOrder(final String username,String prizeId,String point) {
	    try {
	    	String sqlLog="insert into PointLog (UserAccount,PrizeID,Point,Description) values(?,?,?,?)";
			jdbcTemplate.update(sqlLog,new Object[]{username,prizeId,"-"+point,username+"兌換商品編號"+prizeId+";減少"+point+"點"});			
			String sql2="select TotalPoint from UserMember where UserAccount=?";
			int TotalPoint = jdbcTemplate.queryForObject(
					sql2,
	                 new Object[] {username},
	                 java.lang.Integer.class);

			TotalPoint =TotalPoint-Integer.parseInt(point);
			String sql1="update UserMember set TotalPoint=? where UserAccount=?";
			jdbcTemplate.update(sql1,new Object[]{TotalPoint,username});			
			String sql="insert into UserOrder (UserAccount,PrizeID,State) values(?,?,?)";
			jdbcTemplate.update(sql,new Object[]{username,prizeId,"1"});			  	
	    }catch(Exception e) {
	    	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	    	System.out.println(e.toString());
	    	return false;
	    }
		return true;		
	}
	
	public int insertRatingStarPrize(String UserAccount,String PrizeID,String RatingStar,String Comment) {
		String sql="insert into RatingStar (UserAccount,PrizeID,RatingStar,Comment) values(?,?,?,?)";
		return jdbcTemplate.update(sql,new Object[]{UserAccount,PrizeID,RatingStar,Comment});
	}
	
	public boolean updateUserPoint(String userAccount,int Point) {
		try {
			String sqlpoint="select TotalPoint from UserMember where UserAccount=?";
			int TotalPoint = jdbcTemplate.queryForObject(
					sqlpoint,
	                 new Object[] {userAccount},
	                 java.lang.Integer.class);
			TotalPoint=TotalPoint+Point;
			String sql="update UserMember set TotalPoint=? where UserAccount=?";
			jdbcTemplate.update(sql,new Object[]{TotalPoint,userAccount});
			String sqlLog="insert into PointLog (UserAccount,Point,Description) values(?,?,?)";
			jdbcTemplate.update(sqlLog,new Object[]{userAccount,"+"+Point,userAccount+"從抓寶模組處賺取到"+Point+"點積分"});
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public String getPrizeCount() {
		String sql="select count(*) from Prize where State=0";
		String prizeCount=jdbcTemplate.queryForObject(sql, java.lang.String.class);
		return prizeCount;
	}
	
	public boolean checkMemberNumber(String PrizeId,String MemberNumber) {
		String sql = "select * from Prize p,AdminMember a where a.MemberID=p.CreateUser and p.id=? and a.MemberNumber=?";
		return jdbcTemplate.query(sql, new Object[] { PrizeId,MemberNumber }, new ResultSetExtractor<Boolean>() {
    	    @Override
    	    public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
    	        if (rs.next()) {
    	            return true;
    	        }
    	        return false;
    	    }
    	});
	}

}
