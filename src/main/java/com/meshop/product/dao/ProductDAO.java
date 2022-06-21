package com.meshop.product.dao;


import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.meshop.product.entity.Attachment;
import com.meshop.product.entity.ProductExt;

public interface ProductDAO {
	List<ProductExt> findAll();
	
	int insertProduct(Connection conn, ProductExt product);
	
	int insertProductBuy(Connection conn, ProductExt product);
	
	int findCurrentProductId(Connection conn);

	int insertAttachment(Connection conn, Attachment attach);
	
	int getTotalProducts(Connection conn);

	List<ProductExt> findAllOrderBy(Connection conn, Map<String, Object> param, String sort);

	ProductExt findOneByProductId(Connection conn, int productId);

	List<ProductExt> findByStatusPlace(Connection conn, Map<String, Object> param, String sort);

	List<ProductExt> findByStatus(Connection conn, Map<String, Object> param, String sort);

	List<ProductExt> findByPlace(Connection conn, Map<String, Object> param, String sort);

	int getStatusPlaceTotalProducts(Connection conn, String place);

	int getStatusTotalProducts(Connection conn);

	int getPlaceTotalProducts(Connection conn, String place);

	
	
}
