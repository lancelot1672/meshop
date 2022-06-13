package com.meshop.product.service;

import java.util.List;
import java.util.Map;

import com.meshop.product.entity.Product;
import com.meshop.product.entity.ProductExt;

public interface ProductService {
	List<ProductExt> findAll();
	List<ProductExt> findAll(Map<String, Object> param);
	
	int insertProduct(Product product);
	
	int getTotalProducts();
}
