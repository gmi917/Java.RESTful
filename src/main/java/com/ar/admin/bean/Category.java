package com.ar.admin.bean;

public class Category {
	private String ID;
	private String CategoryName;
	private String CreateUser;
	private String CreateDate;

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCreateUser() {
		return CreateUser;
	}

	public void setCreateUser(String createUser) {
		CreateUser = createUser;
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
	

}
