package com.web.spot.service;

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
	
	
	
}
