package com.meshop.member.dao;

import java.sql.Connection;
import java.util.Map;

import com.meshop.member.entity.Member;

public interface MemberDAO {
	
	int findMember(Connection con, Member member);
	
	int insertMember(Connection conn,  Member member);
	
	String findId(Connection conn, String name);
	
	String findPw(Connection conn, String id);
	
	int duplCheck(Connection conn, String id);
	
}
