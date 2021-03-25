package com.ssafy.spot.dto;

public class User {
	private String user_id;
	private String email;
	private String password;
	private String nickname;
	private String gender;
	private String birth;
	private String authkey;
	private String emailcheck;
	
	public User() {
	}

	public User(String user_id, String email, String password, String nickname, String gender, String birth,
			String authkey, String emailcheck) {
		this.user_id = user_id;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.gender = gender;
		this.birth = birth;
		this.authkey = authkey;
		this.emailcheck = emailcheck;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAuthkey() {
		return authkey;
	}

	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}

	public String getEmailcheck() {
		return emailcheck;
	}

	public void setEmailcheck(String emailcheck) {
		this.emailcheck = emailcheck;
	}

	
	
	
	
}
