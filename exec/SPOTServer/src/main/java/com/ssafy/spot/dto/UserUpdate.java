package com.ssafy.spot.dto;

public class UserUpdate {
	private String user_id;
	private String nickname;
	private String password;
	
	public UserUpdate() {
	}

	public UserUpdate(String user_id, String nickname, String password) {
		this.user_id = user_id;
		this.nickname = nickname;
		this.password = password;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
