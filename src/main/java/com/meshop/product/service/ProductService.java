package com.meshop.product.service;

import java.util.List;
import java.util.Map;

import com.meshop.product.entity.ProductExt;

public interface ProductService {
	List<ProductExt> findAll();
	
	int insertProduct(ProductExt product);
	
	int insertProductBuy(ProductExt product);
	
	int getTotalProducts();
	
	// product List
	int NUM_PER_PAGE = 16;
	List<ProductExt> findAllOrderBy(Map<String, Integer> param, String sort);

	ProductExt findOneByProductId(int productId);
}
