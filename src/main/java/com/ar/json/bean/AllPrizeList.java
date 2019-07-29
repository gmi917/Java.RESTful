package com.ar.json.bean;

import java.util.List;

public class AllPrizeList {
	private String categoryName; 
	//private Prize[] prize;
	private List<Prize> prize;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<Prize> getPrize() {
		return prize;
	}
	public void setPrize(List<Prize> prize) {
		this.prize = prize;
	}
	
//	public Prize[] getPrize() {
//		return prize;
//	}
//	public void setPrize(Prize[] prize) {
//		this.prize = prize;
//	}
	
	
}
