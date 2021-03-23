package com.web.spot.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.spot.dao.WishDao;

@Service
public class WishServiceImpl implements WishService{
	@Autowired
	WishDao dao;
	
	@Override
	public void addwish(String user_id, String spot_id) {
		dao.addwish(user_id, spot_id);
	}

	@Override
	public HashMap<String, String> wishcheck(String user_id, String spot_id) {
		return dao.wishcheck(user_id, spot_id);
	}

	@Override
	public void delwish(String wishlist_id) {
		dao.delwish(wishlist_id);
	}

	@Override
	public List<HashMap<String, String>> wishlist(String user_id) {
		return dao.wishlist(user_id);
	}
	
	
	
}
