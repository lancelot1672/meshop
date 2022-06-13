package com.meshop.wish.dao;

import static com.meshop.common.JdbcTemplate.close;
import static com.meshop.common.JdbcTemplate.getConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.meshop.product.dao.ProductDAOImpl;

public class WishDAOImpl implements WishDAO {
    private Properties properties = new Properties();
	public WishDAOImpl() {
		//생성됨과 동시에 쿼리 설정 파일 가져오기
		String filename = ProductDAOImpl.class.getResource("/sql/wish-query.properties").getPath();
        try{
            properties.load(new FileReader(filename));

        }catch (IOException e){
            String message = e.getMessage();
        	System.out.println(message);
        }
        System.out.println("filename = " + filename);
	}
	
	@Override
	public List<Integer> findByMemberId(String memberId) {
		//준비
		Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        // Integer 로 한 이유는?? => 한 아이디의 1,3번 상품이 wish라고 하면 list에 1과 3을 추가.
        // 딱히 Wish 객체로 감싸 리턴할 필요가 없음!!
		List<Integer> wishList = new ArrayList<>();
		
        try {
        	// 멤버아이디에 따른 위시 목록 가져오기
        	//String sql = "select * from wish where member_id=?";
        	String sql = properties.getProperty("findByMemberId");
        	
        	//쿼리에 memberId 세팅
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1, memberId);
        	
        	// 데이터 요청
        	rs = pstmt.executeQuery();
        	while(rs.next()) {
        		int productId = rs.getInt("product_id");
        		wishList.add(productId);
        	}
        	
        }catch(SQLException e) {
            String message = e.getMessage();
        	System.out.println(message);
        }finally {
        	//자원 반납.
        	close(rs);
        	close(pstmt);
        	close(conn);
        }
		return wishList;
	}
	

}
