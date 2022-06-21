package com.meshop.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.meshop.common.MeshopUtils;
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
		try {
			String place = request.getParameter("place");
			boolean statusBool = Boolean.valueOf(request.getParameter("statusToggle"));
			boolean placeBool = Boolean.valueOf(request.getParameter("placeToggle"));
			
			// 1. 사용자 입력값 처리
			String sort = request.getParameter("sort");
			sort = sort != null ? sort : "reg_date";
			int numPerPage = productService.NUM_PER_PAGE;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {
				// 예외발생시 현재페이지 1
			}
			
			Map<String, Object> param = new HashMap<>();
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			param.put("start", start);
			param.put("end", end);
			param.put("place", place);
			
			List<ProductExt> productList = null;
			int totalProducts = 0;
			if(statusBool && placeBool) {
				productList = productService.findByStatusPlace(param, sort);
				totalProducts = productService.getStatusPlaceTotalProducts(place);
				
			}
			else if(statusBool && !placeBool) {
				productList = productService.findByStatus(param, sort);
				totalProducts = productService.getStatusTotalProducts();
			}
			else if(!statusBool && placeBool) {
				productList = productService.findByPlace(param, sort);
				totalProducts = productService.getPlaceTotalProducts(place);
			}
			else {
				productList = productService.findAllOrderBy(param, sort);
				totalProducts = productService.getTotalProducts();
			}
			
			// 페이지바
			String url = request.getRequestURI();
			String pagebar = MeshopUtils.getPagebar(cPage, numPerPage, totalProducts, url);
			
			Map<String, Object> jsonMap = new HashMap<>();
			jsonMap.put("list", productList);
			jsonMap.put("pagebar", pagebar);
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().append(new Gson().toJson(jsonMap));
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
