package com.meshop.wish.service;

import java.util.List;

import com.meshop.wish.dao.WishDAO;
import com.meshop.wish.dao.WishDAOImpl;

public class WishServiceImpl implements WishService{
	WishDAO wishDAO = new WishDAOImpl();
	@Override
	public List<Integer> findByMemberId(String memberId) {
		return wishDAO.findByMemberId(memberId);
	}

}
