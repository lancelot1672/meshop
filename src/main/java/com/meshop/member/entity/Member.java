package com.meshop.member.entity;

import java.sql.Date;
import java.util.List;


public class Member {
	private String memberId;
	private String password;
	private String memberName;
	private String storeName;
	private int storeGrade;
	private Date joinDate;
	private String place;
	private String memberRole;
	private List<Integer> wish;
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// 회원가입 form내 데이터
	// member_id, password, member_name, store_name, place, member_role
	public Member(String memberId, String password, String memberName, String storeName, String place, String memberRole) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.storeName = storeName;
		this.place = place;
		this.memberRole = memberRole;
	}
	
	public Member(String memberId, String password, String memberName, String storeName, int storeGrade, Date joinDate,
			String place, String memberRole, List<Integer> wish) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.storeName = storeName;
		this.storeGrade = storeGrade;
		this.joinDate = joinDate;
		this.place = place;
		this.memberRole = memberRole;
		this.wish = wish;
	}
	
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public int getStoreGrade() {
		return storeGrade;
	}
	public void setStoreGrade(int storeGrade) {
		this.storeGrade = storeGrade;
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
	public String getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
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

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", memberName=" + memberName + ", storeName=" + storeName
				+ ", storeGrade=" + storeGrade + ", joinDate=" + joinDate + ", place=" + place + ", memberRole=" + memberRole
				+ ", wish=" + wish + "]";
	}

}
