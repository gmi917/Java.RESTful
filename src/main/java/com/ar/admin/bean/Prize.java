package com.ar.admin.bean;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Prize {
	private String ID;
	private String CategoryID;
	private String CategoryName;
	private String PrizeName;
	private String PrizeDescription;
	private String Point;
	private String State;
	private String Image;
	private String CreateUser;
	private List<MultipartFile> images;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(String categoryID) {
		CategoryID = categoryID;
	}
		
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public String getPrizeName() {
		return PrizeName;
	}
	public void setPrizeName(String prizeName) {
		PrizeName = prizeName;
	}
	public String getPrizeDescription() {
		return PrizeDescription;
	}
	public void setPrizeDescription(String prizeDescription) {
		PrizeDescription = prizeDescription;
	}
	public String getPoint() {
		return Point;
	}
	public void setPoint(String point) {
		Point = point;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public String getCreateUser() {
		return CreateUser;
	}
	public void setCreateUser(String createUser) {
		CreateUser = createUser;
	}
	public List<MultipartFile> getImages() {
		return images;
	}
	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	

}
