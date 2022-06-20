
package com.meshop.member.service;

import java.util.List;
import java.util.Map;

import com.meshop.member.entity.Member;

public interface MemberService {

	int insertMember(Member member);	
	
	int findMember(Member member);
	
	String findId(String name);
	
	String findPw(String id);
	
	int duplCheck(String id);
	
	List<Member> findAllMember();
}

