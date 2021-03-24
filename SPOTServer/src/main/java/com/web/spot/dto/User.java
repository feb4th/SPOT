package com.web.spot.dto;

public class User {
	private String user_id;
	private String email;
	private String password;
	private String name;
	private String nickname;
	
	public User() {
	}

	public User(String user_id, String email, String password, String name, String nickname) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
}
