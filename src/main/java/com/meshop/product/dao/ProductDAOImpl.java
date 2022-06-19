package com.meshop.product.dao;
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

import com.meshop.product.entity.Attachment;
import com.meshop.product.entity.Product;
import com.meshop.product.entity.ProductExt;
import com.meshop.product.exception.ProductException;

public class ProductDAOImpl implements ProductDAO{
    private Properties properties = new Properties();
	public ProductDAOImpl(){
		//생성됨과 동시에 쿼리 설정 파일 가져오기
		String filename = ProductDAOImpl.class.getResource("/sql/product-query.properties").getPath();
        try{
            properties.load(new FileReader(filename));

        }catch (IOException e){
            String message = e.getMessage();
        	System.out.println(message);
        }
        System.out.println("filename = " + filename);
	}
	@Override
	public List<ProductExt> findAll() {
        //준비
		Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        // SQL
        String sql = properties.getProperty("findThumbnail");
        System.out.println(sql);
        
        List<ProductExt> list = new ArrayList<>();
        
        
        //DB 로직
        try {
        	pstmt = conn.prepareStatement(sql);
        	rs = pstmt.executeQuery();
        	
        	while(rs.next()) {
        		ProductExt p = new ProductExt();
        		
        		//상품 미리보기 정보
           		p.setProductId(rs.getInt("product_id"));
        		p.setTitle(rs.getString("title"));
        		p.setPrice(rs.getInt("price"));
        		p.setBrand(rs.getString("brand"));
 
        		//대표 이미지 파일
        		Attachment a = new Attachment();
        		a.setOriginalFilename(rs.getString("original_name"));
        		a.setRenamedFilename(rs.getString("renamed_name"));
        		
        		//첨부파일 추가
        		p.setAttachment(a);
        		
        		//썸네일 리스트에 추가
        		list.add(p);
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
        System.out.println(list.toString());
		return list;
	}

	@Override
	public int insertProduct(Connection conn, ProductExt product) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = properties.getProperty("insertProduct");
		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, product.getMemberId());
			pstmt.setString(1, "qwer");
			pstmt.setString(2, product.getTitle());
			pstmt.setString(3, product.getContent());
			pstmt.setString(4, product.getCategory());
//			pstmt.setString(5, product.getPlace());
			pstmt.setString(5, "마포구");
			pstmt.setString(6, String.valueOf(product.getStatus()));
			pstmt.setInt(7, product.getPrice());
			pstmt.setString(8, product.getBrand());
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			throw new ProductException("판매 게시글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	@Override
	public int findCurrentProductId(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = properties.getProperty("findCurrentProductId");
		int no = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) no = rset.getInt(1);
		} catch(SQLException e) {
			throw new ProductException("게시글 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return no;
	}
	
	@Override
	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		String sql = properties.getProperty("insertAttachment");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getProductId());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			pstmt.setString(4, String.valueOf(attach.getAttachtype()));
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			throw new ProductException("첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	@Override
	public int getTotalProducts() {
		return 0;
	}
//	public static void main(String[] args) {
//		ProductDAOImpl p = new ProductDAOImpl();
//		p.findAll();
//	}
}
