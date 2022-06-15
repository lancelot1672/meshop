package com.meshop.product.dao;


import java.sql.Connection;
import java.util.List;

import com.meshop.product.entity.Attachment;
import com.meshop.product.entity.ProductExt;

public interface ProductDAO {
	List<ProductExt> findAll();
	//List<ProductExt> findAll(Map<String, Object> param);
	
	int insertProduct(Connection conn, ProductExt product);
	
	int getTotalProducts();

	int findCurrentProductId(Connection conn);

	int insertAttachment(Connection conn, Attachment attach);
	
	
}
