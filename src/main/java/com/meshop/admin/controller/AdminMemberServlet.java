package com.meshop.admin.controller;

import java.io.IOException;


@WebServlet("/admin/member")
public class AdminMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//ajax 통신
		// 1. 업무 로직
		
		// 2. 응답처리 json
//		response.setContentType("application/json; charset=utf-8");
//		response.getWriter().append(new Gson().toJson(list));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
