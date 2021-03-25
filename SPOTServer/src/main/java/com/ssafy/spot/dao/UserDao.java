package com.ssafy.spot.dao;

import java.util.HashMap;

import com.ssafy.spot.dto.SignupReq;
import com.ssafy.spot.dto.User;
import com.ssafy.spot.dto.UserUpdate;
import com.ssafy.spot.dto.loginReq;

public interface UserDao {
	public void insertUser(SignupReq req);
	public User findByEmail(String email);
	public void deleteUser(String user_id);
	public User login(loginReq req);
	public HashMap<String, String> findById(String user_id);
	public void updateNickname(UserUpdate req);
	public void updateUser(UserUpdate req);
}
