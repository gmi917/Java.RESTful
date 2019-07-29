package com.ar.admin.bean;

public class AdminMember {
	private String ID;
	private String MemberID;
	private String MemberPWD;
	private String MemberName;
	private String MemberNumber;
	private String Role;
	private String CreateUser;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getMemberID() {
		return MemberID;
	}
	public void setMemberID(String memberID) {
		MemberID = memberID;
	}
	public String getMemberPWD() {
		return MemberPWD;
	}
	public void setMemberPWD(String memberPWD) {
		MemberPWD = memberPWD;
	}
	public String getMemberName() {
		return MemberName;
	}
	public void setMemberName(String memberName) {
		MemberName = memberName;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public String getCreateUser() {
		return CreateUser;
	}
	public void setCreateUser(String createUser) {
		CreateUser = createUser;
	}
	public String getMemberNumber() {
		return MemberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		MemberNumber = memberNumber;
	}
	

}
