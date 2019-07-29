package com.ar.json.bean;

import org.springframework.stereotype.Component;

@Component
public class User {
	private String UserAccount;
	private String UserPWD;
	private String UserName;
	private String Email;
	private String Tel;
	private String TotalPoint;
	public String getUserAccount() {
		return UserAccount;
	}
	public void setUserAccount(String userAccount) {
		UserAccount = userAccount;
	}
	public String getUserPWD() {
		return UserPWD;
	}
	public void setUserPWD(String userPWD) {
		UserPWD = userPWD;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getTotalPoint() {
		return TotalPoint;
	}
	public void setTotalPoint(String totalPoint) {
		TotalPoint = totalPoint;
	}
		
}
