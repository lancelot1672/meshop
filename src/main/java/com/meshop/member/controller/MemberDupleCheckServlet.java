package com.meshop.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name="memberDuplServlet", urlPatterns = "/member/check")
public class MemberDupleCheckServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("id");
		
		System.out.println("id : " + memberId);
		
		Member member = new Member();
		member.setMemberId(memberId);
		
		MemberService memberService = new MemberServiceImpl();
		int result = memberService.duplCheck(memberId);
		
		PrintWriter out = response.getWriter();
		
		out.print(result);
	}

}
