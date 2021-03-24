package com.web.spot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.spot.dao.UserDao;
import com.web.spot.dao.WishDao;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao dao;
	
	
	
}
