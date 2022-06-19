package com.meshop.member.entity;

import java.util.List;


public class Member {
	private String memberId;
	private String password;
	private List<Integer> wish;
	

	public List<Integer> getWish() {
		return wish;
	}
	public void setWish(List<Integer> wish) {
		this.wish = wish;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
