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

public class ProductDAOImpl implements ProductDAO{
    private Properties properties = new Properties();
	public ProductDAOImpl(){  
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
        ResultSet rset = null;
        String sql = properties.getProperty("findThumbnail");
        System.out.println(sql);
        
        List<ProductExt> list = new ArrayList<>();
        
        try {
        	pstmt = conn.prepareStatement(sql);
        	rset = pstmt.executeQuery();
        	
        	while(rset.next()) {
        		ProductExt p = new ProductExt();
        		
        		//상품 미리보기 정보
        		p.setTitle(rset.getString("title"));
        		p.setPrice(rset.getInt("price"));
        		p.setBrand(rset.getString("brand"));
        		
        		//대표 이미지 파일
        		Attachment a = new Attachment();
        		a.setOriginalFilename(rset.getString("original_name"));
        		a.setRenamedFilename(rset.getString("renamed_name"));
        		
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
        	close(rset);
        	close(pstmt);
        	close(conn);
        }
        System.out.println(list.toString());
		return list;
	}

	@Override
	public int insertProduct(Product product) {
		return 0;
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
