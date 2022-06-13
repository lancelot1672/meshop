package com.meshop.product.dao;


import java.util.List;

import com.meshop.product.entity.Product;
import com.meshop.product.entity.ProductExt;

public interface ProductDAO {
	List<ProductExt> findAll();
	//List<ProductExt> findAll(Map<String, Object> param);
	
	int insertProduct(Product product);
	
	int getTotalProducts();
	
	
}
