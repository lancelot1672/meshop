package com.meshop.member.service;

import static com.meshop.common.JdbcTemplate.close;
import static com.meshop.common.JdbcTemplate.commit;
import static com.meshop.common.JdbcTemplate.getConnection;
import static com.meshop.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.meshop.member.dao.MemberDAO;
import com.meshop.member.dao.MemberDAOImpl;
import com.meshop.member.entity.Member;


public class MemberServiceImpl implements MemberService {
	
	MemberDAO memberDAO = new MemberDAOImpl();

	@Override
	public int insertMember(Member member) {
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
	public int duplCheck(String id) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDAO.duplCheck(conn, id);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	

}
