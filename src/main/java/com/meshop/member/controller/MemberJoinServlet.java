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
import com.meshop.member.service.MemberService;
import com.meshop.member.service.MemberServiceImpl;
import com.meshop.wish.service.WishService;
import com.meshop.wish.service.WishServiceImpl;

@WebServlet(name="memberJoinServlet", urlPatterns = "/member/join")
public class MemberJoinServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/auth/join.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			request.setCharacterEncoding("EUC-KR");
			response.setContentType("text/html; charset=EUC-KR");
			
			String memberId = request.getParameter("id");
			String password = request.getParameter("password");
			String name =  request.getParameter("name");
			String storeName =  request.getParameter("storeName");
			String place =  request.getParameter("place");
			
			Member member = new Member();
			member.setMemberId(memberId);
			member.setPassword(password);
			member.setMemberName(name);
			member.setStoreName(storeName);
			member.setPlace(place);
			member.setMemberRole("U");
			
			MemberService memberService = new MemberServiceImpl();
			
			memberService.insertMember(member);
			
			HttpSession session = request.getSession();
			
			session.setAttribute("loginMember", member);
			
			response.sendRedirect(request.getContextPath() +"/main");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
