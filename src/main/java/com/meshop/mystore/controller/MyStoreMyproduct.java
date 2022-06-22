package com.meshop.mystore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meshop.mystore.entity.Myproduct;
import com.meshop.mystore.service.MyproductService;
import com.meshop.product.entity.ProductExt;

/**
 * Servlet implementation class MyStoreMyproduct
 */
@WebServlet("/mystore/myProduct")
public class MyStoreMyproduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
			// 사용자 입력값 처리
			
//			String title = request.getParameter("title");
//			String content = request.getParameter("content");
//			   //서버에 저장된 이미지 파일의 경로를 반환받아 저장
//	        String filePath=request.getServletContext().getRealPath("/WEB-INF/Koala.jpg");
			
			List<ProductExt> list = MyproductService.findAll();
		
			
			// 뷰단처리
//		} catch(Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
		
		// 페이지바 영역 
//		int totalMystore = myproductStore.getTotalProductstore(); 
		// 뷰단위임
//		request.setAttribute("MyProduct",list);
//		request.setAttribute("mypagebar", mypagebar)
		// 뷰에 전달
		request.getRequestDispatcher("/WEB-INF/views/product/Myproduct.jsp");
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
}




