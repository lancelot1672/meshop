package com.meshop.wish.dao;

import java.util.List;

public interface WishDAO {
	List<Integer> findByMemberId(String memberId);
}
