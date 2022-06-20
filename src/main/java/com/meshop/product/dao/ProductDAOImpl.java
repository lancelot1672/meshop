package com.meshop.product.dao;
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
import java.util.Map;
import java.util.Properties;

import com.meshop.product.entity.Attachment;
import com.meshop.product.entity.ProductExt;
import com.meshop.product.entity.ProductStatus;
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
        		ProductExt product = new ProductExt();
        		
        		//상품 미리보기 정보
        		product.setProductId(rs.getInt("product_id"));
        		product.setTitle(rs.getString("title"));
        		product.setPrice(rs.getInt("price"));
        		product.setBrand(rs.getString("brand"));
        		product.setPlace(rs.getString("place"));
        		product.setRegDate(rs.getDate("reg_date"));
        		product.setStatus(ProductStatus.valueOf(rs.getString("status")));
        		
        		Attachment attach = new Attachment();
        		attach.setOriginalFilename(rs.getString("original_name"));
        		attach.setRenamedFilename(rs.getString("renamed_name"));
        		
        		//첨부파일 추가
        		product.setAttachment(attach);
        		
        		//썸네일 리스트에 추가
        		list.add(product);
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
	public int insertProductBuy(Connection conn, ProductExt product) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = properties.getProperty("insertProductBuy");
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
			throw new ProductException("구매 게시글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	@Override
	public List<ProductExt> findAllOrderBy(Connection conn, Map<String, Integer> param, String sort) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductExt> productList = new ArrayList<>();
		String sql = properties.getProperty("findAllOrderBy");
		try {
			pstmt = conn.prepareStatement(sql);
			// 오름차순, 내림차순
			sort = "price".equals(sort) ? sort : sort + " desc";
			pstmt.setString(1, sort);
			pstmt.setInt(2, param.get("start"));
			pstmt.setInt(3, param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductExt product = handleProductExtResultSet(rset);
        		productList.add(product);
			}
		} catch(SQLException e) {
			throw new ProductException("모든 상품 게시글 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productList;
	}
//	public static void main(String[] args) {
//		ProductDAOImpl p = new ProductDAOImpl();
//		p.findAll();
//	}
	private ProductExt handleProductExtResultSet(ResultSet rset) throws SQLException {
		ProductExt product = new ProductExt();
		
		product.setProductId(rset.getInt("product_id"));
		product.setTitle(rset.getString("title"));
		product.setPrice(rset.getInt("price"));
		product.setBrand(rset.getString("brand"));
		product.setPlace(rset.getString("place"));
		product.setRegDate(rset.getDate("reg_date"));
		product.setStatus(ProductStatus.valueOf(rset.getString("status")));
		
		Attachment attach = new Attachment();
		attach.setOriginalFilename(rset.getString("original_name"));
		attach.setRenamedFilename(rset.getString("renamed_name"));
		
		//첨부파일 추가
		product.setAttachment(attach);
		return product;
	}
	
	@Override
	public int getTotalProducts(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalProducts = 0;
		String sql = properties.getProperty("getTotalProducts");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) totalProducts = rset.getInt(1);
		} catch(SQLException e) {
			throw new ProductException("전체 상품수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalProducts;
	}
	
	@Override
	public ProductExt findOneByProductId(Connection conn, int productId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ProductExt product = null;
		String sql = properties.getProperty("findOneByProductId");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productId);
			rset = pstmt.executeQuery();
			if(rset.next()) product = handleProductExtResultSet(rset);
		} catch(SQLException e) {
			close(rset);
			close(pstmt);
		}
		return product;
	}
}
