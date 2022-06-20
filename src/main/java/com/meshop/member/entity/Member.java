package com.meshop.member.entity;

import java.sql.Date;
import java.util.List;


public class Member {
	private String memberId;
	private String password;
	private String memberName;
	private String StoreName;
	private double store_grade;
	private Date joinDate;
	private String place;
	private MemberRole memberRole;
	private int boardCount;
	private List<Integer> wish;
	

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberName=" + memberName + ", StoreName=" + StoreName
				+ ", store_grade=" + store_grade + ", joinDate=" + joinDate + ", place=" + place + ", memberRole="
				+ memberRole + ", wish=" + wish + "]";
	}
	
	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

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
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getStoreName() {
		return StoreName;
	}
	public void setStoreName(String storeName) {
		StoreName = storeName;
	}
	public double getStore_grade() {
		return store_grade;
	}
	public void setStore_grade(double store_grade) {
		this.store_grade = store_grade;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public MemberRole getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(MemberRole memberRole) {
		this.memberRole = memberRole;
	}
	
	
}
