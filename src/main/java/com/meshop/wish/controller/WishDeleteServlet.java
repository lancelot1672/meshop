package com.meshop.wish.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meshop.member.entity.Member;
import com.meshop.wish.service.WishService;
import com.meshop.wish.service.WishServiceImpl;

@WebServlet(name =  "wishDeleteServlet",urlPatterns = "/wish/delWish")
public class WishDeleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	WishService wishService = new WishServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int productId = Integer.parseInt(request.getParameter("productId"));
		
		int result = wishService.deleteWish(memberId, productId);
		
		//세션에 들어있는 member wish 리스트 수정
		HttpSession session = request.getSession();
		Member m = (Member) session.getAttribute("loginMember");
		List<Integer> wishList = m.getWish();

		for(int i=0; i<wishList.size(); i++) {
			if(wishList.get(i) == productId) {
				wishList.remove(i);
				break;
			}
		}
		
		m.setWish(wishList);;
		session.setAttribute("loginMember", m);
		
		response.getWriter().append("success");
	}
	
}
