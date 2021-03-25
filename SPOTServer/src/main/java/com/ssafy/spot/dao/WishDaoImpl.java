package com.ssafy.spot.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.spot.dto.WishList;
import com.ssafy.spot.mapper.WishMapper;

@Repository
public class WishDaoImpl implements WishDao{
	@Autowired
	WishMapper mapper;
	
	@Override
	public void insertWish(WishList wish) {
		mapper.insertWish(wish);
	}

	@Override
	public HashMap<String, String> checkWish(String user_id, String spot_id) {
		return mapper.checkWish(user_id, spot_id);
	}

	@Override
	public void deleteWish(String wishlist_id) {
		mapper.deleteWish(wishlist_id);
	}

	@Override
	public List<HashMap<String, String>> wishlist(String user_id) {
		return mapper.wishlist(user_id);
	}
	
}
