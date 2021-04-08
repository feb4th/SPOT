package com.ssafy.spot.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.spot.dao.WishDao;
import com.ssafy.spot.dto.AddWish;
import com.ssafy.spot.dto.WishList;

@Service
public class WishServiceImpl implements WishService{
	@Autowired
	WishDao dao;
	
	@Override
	public void insertWish(AddWish wish) {
		dao.insertWish(wish);
	}

	@Override
	public HashMap<String, String> checkWish(String user_id, String spot_id) {
		return dao.checkWish(user_id, spot_id);
	}

	@Override
	public void deleteWish(String wishlist_id) {
		dao.deleteWish(wishlist_id);
	}

	@Override
	public List<HashMap<String, String>> wishlist(String user_id) {
		return dao.wishlist(user_id);
	}

	@Override
	public HashMap<String, String> findLat(String spot_id) {
		return dao.findLat(spot_id);
	}
	
	
	
}
