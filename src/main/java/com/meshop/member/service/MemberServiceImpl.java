package com.meshop.member.service;

import static com.meshop.common.JdbcTemplate.close;
import static com.meshop.common.JdbcTemplate.commit;
import static com.meshop.common.JdbcTemplate.getConnection;
import static com.meshop.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.meshop.member.dao.MemberDAO;
import com.meshop.member.entity.Member;


public class MemberServiceImpl implements MemberService {
	
	MemberDAO memberDAO = new MemberDAO();

	@Override
	public int insertMember(Member member) {
		//회원가입
		
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDAO.insertMember(conn, member);
			commit(conn);
		} catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}
	
	@Override
	public int findMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDAO.findMember(conn, member);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	@Override
	public String findId(String name) {
		String result = "";
		Connection conn = getConnection();
		try {
			result = memberDAO.findId(conn, name);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	@Override
	public String findPw(String id) {
		String result = "";
		Connection conn = getConnection();
		try {
			result = memberDAO.findPw(conn, id);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	@Override
	public boolean duplCheck(String memberId) {
		boolean result;
		Connection conn = getConnection();
		try {
			result = memberDAO.duplCheck(conn, memberId);
		} catch(Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	@Override
	public boolean storeDuplCheck(String storeName) {
		boolean result;
		Connection conn = getConnection();
		try {
			result = memberDAO.storeDuplCheck(conn, storeName);
		} catch(Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	@Override
	public List<Member> findAllMember(){
		List<Member> list = new ArrayList<>();
		Connection conn = getConnection();
		
		list = memberDAO.findAllMember(conn);
		
		close(conn);
		return list;
	}
	

}