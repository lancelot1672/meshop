package com.meshop.member.service;
import static com.meshop.common.JdbcTemplate.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.meshop.member.dao.MemberDAO;
import com.meshop.member.entity.Member;



public class MemberService {
	private MemberDAO memberDao = new MemberDAO();
	
	public List<Member> findAllMember(){
		List<Member> list = new ArrayList<>();
		Connection conn = getConnection();
		
		list = memberDao.findAllMember(conn);
		
		close(conn);
		return list;
	}
}
