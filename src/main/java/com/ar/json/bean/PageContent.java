package com.ar.json.bean;

import java.util.List;

public class PageContent {
	private String first;
	private String prev;
	private String next;
	private String last;
	private List<UserPrize> content;
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	
	public String getPrev() {
		return prev;
	}
	public void setPrev(String prev) {
		this.prev = prev;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public List<UserPrize> getContent() {
		return content;
	}
	public void setContent(List<UserPrize> content) {
		this.content = content;
	}
	

}
