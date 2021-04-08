package com.ssafy.spot.dao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.spot.dto.SignupReq;
import com.ssafy.spot.dto.User;
import com.ssafy.spot.dto.UserUpdate;
import com.ssafy.spot.dto.loginReq;
import com.ssafy.spot.mapper.UserMapper;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	UserMapper mapper;

	@Override
	public void insertUser(SignupReq req) {
		mapper.insertUser(req);
	}

	@Override
	public User findByEmail(String email) {
		return mapper.findByEmail(email);
	}

	@Override
	public void deleteUser(String user_id) {
		mapper.deleteUser(user_id);
	}

	@Override
	public User login(loginReq req) {
		return mapper.login(req);
	}

	@Override
	public HashMap<String, String> findById(String user_id) {
		return mapper.findById(user_id);
	}

	@Override
	public void updateNickname(UserUpdate req) {
		mapper.updateNickname(req);
	}

	@Override
	public void updateUser(UserUpdate req) {
		mapper.updateUser(req);
	}

	@Override
	public void insertImage(String user_id, String timeurl) {
		mapper.insertImage(user_id, timeurl);
	}

	@Override
	public void emailLink(User u) {
		mapper.emailLink(u);
	}

	@Override
	public User getUserByEmail(String email) {
		return mapper.getUserByEmail(email);
	}

	@Override
	public void updateAuth(String user_id) {
		mapper.updateAuth(user_id);
	}

	@Override
	public int findNickname(String Nickname) {
		return mapper.findNickname(Nickname);
	}

	@Override
	public int findEmail(String email) {
		return mapper.findEmail(email);
	}
	
}
