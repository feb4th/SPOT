package com.web.spot.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.spot.mapper.WishMapper;

@Repository
public class WishDaoImpl implements WishDao{
	@Autowired
	WishMapper mapper;
	
	@Override
	public void addwish(String user_id, String spot_id) {
		mapper.addwish(user_id, spot_id);
	}

	@Override
	public HashMap<String, String> wishcheck(String user_id, String spot_id) {
		return mapper.wishcheck(user_id, spot_id);
	}

	@Override
	public void delwish(String wishlist_id) {
		mapper.delwish(wishlist_id);
	}

	@Override
	public List<HashMap<String, String>> wishlist(String user_id) {
		return mapper.wishlist(user_id);
	}
	
}
