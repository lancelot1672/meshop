package com.meshop.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meshop.member.entity.Member;
import com.meshop.wish.service.WishService;
import com.meshop.wish.service.WishServiceImpl;

@WebServlet(name="memberLoginServlet", urlPatterns = "/member/login")
public class MemberLoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setPassword(password);
		
		//로그인 했을 때 찜 목록 가져오기
		WishService wishService = new WishServiceImpl();
		List<Integer> wishList = wishService.findByMemberId(memberId);
		member.setWish(wishList);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("loginMember", member);
		
//        RequestDispatcher reqDispatcher = request.getRequestDispatcher(request.getContextPath() + "/main");
//        reqDispatcher.forward(request, response);
		response.sendRedirect(request.getContextPath() +"/main");
	}

}
