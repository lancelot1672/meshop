package com.meshop.product.service;

import static com.meshop.common.JdbcTemplate.commit;
import static com.meshop.common.JdbcTemplate.getConnection;
import static com.meshop.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.meshop.product.dao.ProductDAO;
import com.meshop.product.dao.ProductDAOImpl;
import com.meshop.product.entity.Attachment;
import com.meshop.product.entity.ProductExt;

public class ProductServiceImpl implements ProductService{
	
	ProductDAO productDAO = new ProductDAOImpl();
	@Override
	public List<ProductExt> findAll() {
		return productDAO.findAll();
	}

	@Override
	public List<ProductExt> findAll(Map<String, Object> param) {
		return null;
	}

	@Override
	public int insertProduct(ProductExt product) {
		int result = 0;
		Connection conn = getConnection();
		try {
			// 1. board 등록
			result = productDAO.insertProduct(conn, product);
			
			// 2. board pk 가져오기
			int no = productDAO.findCurrentProductId(conn);
			System.out.println("product current no = " + no);
			
			// 3. attachment에 등록
			List<Attachment> attachments = product.getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					System.out.println("hi");
					attach.setProductId(no);
					result = productDAO.insertAttachment(conn, attach);
				}
			}
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	@Override
	public int getTotalProducts() {
		return 0;
	}

}
