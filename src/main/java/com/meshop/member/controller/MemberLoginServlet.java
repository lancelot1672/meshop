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


@WebServlet(name="memberLoginServlet", urlPatterns = "/login")
public class MemberLoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String memberId = request.getParameter("id");
			String password = request.getParameter("password");

			Member member = new Member();
			member.setMemberId(memberId);
			member.setPassword(password);

			MemberService memberService = new MemberServiceImpl();
			int result = memberService.findMember(member);

			// 로그인 했을 때 찜 목록 가져오기
			WishService wishService = new WishServiceImpl();
			List<Integer> wishList = wishService.findByMemberId(memberId);
			member.setWish(wishList);

			HttpSession session = request.getSession();

			session.setAttribute("loginMember", member);

			request.setAttribute("result", result);

			if (result != 0) {
				response.sendRedirect(request.getContextPath() + "/main");
			} else {
				PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=EUC-KR");
				response.setCharacterEncoding("EUC-KR");
				out.println("<script type=\"text/javascript\">");
				out.println("alert('아이디 혹은 비밀번호가 틀렸습니다.');");
				out.println("location='/meshop/member/login';");
				out.println("</script>");
			}

//        RequestDispatcher reqDispatcher = request.getRequestDispatcher(request.getContextPath() + "/main");
//        reqDispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
