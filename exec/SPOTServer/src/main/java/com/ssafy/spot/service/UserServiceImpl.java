package com.ssafy.spot.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.spot.dao.UserDao;
import com.ssafy.spot.dto.SignupReq;
import com.ssafy.spot.dto.User;
import com.ssafy.spot.dto.UserUpdate;
import com.ssafy.spot.dto.loginReq;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao dao;

	@Override
	public void insertUser(SignupReq req) {
		dao.insertUser(req);
	}

	@Override
	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public void deleteUser(String user_id) {
		dao.deleteUser(user_id);
	}

	@Override
	public User login(loginReq req) {
		return dao.login(req);
	}

	@Override
	public HashMap<String, String> findById(String user_id) {
		return dao.findById(user_id);
	}

	@Override
	public void updateNickname(UserUpdate req) {
		dao.updateNickname(req);
	}

	@Override
	public void updateUser(UserUpdate req) {
		dao.updateUser(req);
	}

	@Override
	public void insertImage(String user_id, String timeurl) {
		dao.insertImage(user_id, timeurl);
	}

	@Override
	public void emailLink(User u) {
		dao.emailLink(u);
	}

	@Override
	public User getUserByEmail(String email) {
		return dao.getUserByEmail(email);
	}

	@Override
	public void updateAuth(String user_id) {
		dao.updateAuth(user_id);
	}

	@Override
	public int findNickname(String Nickname) {
		return dao.findNickname(Nickname);
	}

	@Override
	public int findEmail(String email) {
		return dao.findEmail(email);
	}
	
	
	
}
