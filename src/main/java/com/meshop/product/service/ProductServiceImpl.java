package com.meshop.product.service;

import java.util.List;
import java.util.Map;

import com.meshop.product.dao.ProductDAO;
import com.meshop.product.dao.ProductDAOImpl;
import com.meshop.product.entity.Product;
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
	public int insertProduct(Product product) {
		return 0;
	}

	@Override
	public int getTotalProducts() {
		return 0;
	}

}
