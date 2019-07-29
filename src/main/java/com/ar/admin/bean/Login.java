package com.ar.admin.bean;

public class Login {
	private String ID;
	private String MemberPWD;	
	private String MemberID;
	private String Role;
	private String usercode;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getMemberPWD() {
		return MemberPWD;
	}
	public void setMemberPWD(String memberPWD) {
		MemberPWD = memberPWD;
	}
	public String getMemberID() {
		return MemberID;
	}
	public void setMemberID(String memberID) {
		MemberID = memberID;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	
}
