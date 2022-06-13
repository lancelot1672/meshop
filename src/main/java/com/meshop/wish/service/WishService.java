package com.meshop.wish.service;

import java.util.List;

import com.meshop.wish.entity.Wish;

public interface WishService {
	List<Integer> findByMemberId(String memberId);
}
