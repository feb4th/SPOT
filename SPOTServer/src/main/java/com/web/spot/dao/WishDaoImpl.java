package com.web.spot.dao;

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
	
}
