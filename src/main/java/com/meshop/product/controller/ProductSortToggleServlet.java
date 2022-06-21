package com.meshop.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.meshop.product.entity.ProductExt;
import com.meshop.product.service.ProductService;
import com.meshop.product.service.ProductServiceImpl;

/**
 * Servlet implementation class ProductSortToggleServlet
 */
@WebServlet("/product/productSortToggle")
public class ProductSortToggleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String place = request.getParameter("memberId");
		
		boolean statusBool = Boolean.valueOf(request.getParameter("statusToggle"));
		boolean placeBool = Boolean.valueOf(request.getParameter("placeToggle"));
		
		String sqlSort = "";
		List<ProductExt> productList = null;
		if(statusBool && placeBool) sqlSort = "where status = 'N' and place = " + place;
		else if(!statusBool && placeBool) sqlSort = "where place = " + place;
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(new Gson().toJson(sqlSort));
	}

}
