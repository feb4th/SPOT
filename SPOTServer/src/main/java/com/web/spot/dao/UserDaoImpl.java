package com.web.spot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.spot.mapper.UserMapper;
import com.web.spot.mapper.WishMapper;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	UserMapper mapper;
	
}
