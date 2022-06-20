package com.meshop.member.dao;

import static com.meshop.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import com.meshop.member.entity.Member;
import com.meshop.member.exception.MemberException;
import com.meshop.product.dao.ProductDAOImpl;
import com.meshop.product.exception.ProductException;

public class MemberDAOImpl implements MemberDAO {

	private Properties properties = new Properties();
	
	public MemberDAOImpl(){
		//생성됨과 동시에 쿼리 설정 파일 가져오기
		String filename = ProductDAOImpl.class.getResource("/sql/member-query.properties").getPath();
        try{
            properties.load(new FileReader(filename));

        }catch (IOException e){
            String message = e.getMessage();
        	System.out.println(message);
        }
        System.out.println("filename = " + filename);
	}

	@Override
	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		String sql = properties.getProperty("insertMember");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getStoreName());
			pstmt.setString(5, member.getPlace());
			pstmt.setString(6, member.getMemberRole());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("회원가입 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	// Login
	@Override
	public int findMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = properties.getProperty("findMember");
		int no = 0;
		try {
			
			System.out.println("member :" +  member );
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,member.getMemberId());
			pstmt.setString(2, member.getPassword());
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				no = rset.getInt(1);
			}
			
			System.out.println("no : " + no);
			
			
		} catch (SQLException e) {
			throw new MemberException("로그인 과정 중에 오류가 발생하였습니다.", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return no;
	}

	@Override
	public String findId(Connection conn, String name) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = properties.getProperty("findId");
		String result = "";
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if (rset.next())
				result = rset.getString(1);
		} catch (SQLException e) {
			throw new MemberException("입력하신 정보로 가입된 아이디가 없습니다.", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	@Override
	public String findPw(Connection conn, String id) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = properties.getProperty("findPw");
		String result = "";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if (rset.next())
				result = rset.getString(1);
		} catch (SQLException e) {
			throw new MemberException("조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	@Override
	public int duplCheck(Connection conn, String memberId) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int result = 0;
		try {
			String sql = properties.getProperty("findDuplCheck");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
		
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException("오류가 발생하였습니다.", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

}
