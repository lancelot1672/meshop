package com.meshop.member.dao;

import static com.meshop.common.JdbcTemplate.*;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.meshop.chat.dao.ChatDAO;
import com.meshop.member.entity.Member;

public class MemberDAO {
    private Properties properties = new Properties();
	public MemberDAO() {
		//생성됨과 동시에 쿼리 설정 파일 가져오기
		String filename = ChatDAO.class.getResource("/sql/member-query.properties").getPath();
        try{
            properties.load(new FileReader(filename));

        }catch (IOException e){
            String message = e.getMessage();
        	System.out.println(message);
        }
	}
	public List<Member> findAllMember(Connection conn){
		//모든 멤버 정보 가져오기
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<>();
		
		String sql = properties.getProperty("findAllMember");
		//select * from member_view;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member m = new Member();
				m.setMemberId(rs.getString("member_id"));
				m.setStoreName(rs.getString("store_name"));
				m.setMemberName(rs.getString("member_name"));
				m.setStore_grade(rs.getInt("store_grade"));
				m.setJoinDate(rs.getDate("join_date"));
				m.setBoardCount(rs.getInt("board_count"));
				
				list.add(m);
			}
		}catch(SQLException e) {
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
}
