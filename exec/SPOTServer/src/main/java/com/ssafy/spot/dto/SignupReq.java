package com.ssafy.spot.dto;

public class SignupReq {
	private String email;
	private String password;
	private String nickname;
	private String gender;
	private String birth;
	
	public SignupReq() {
	}

	public SignupReq(String email, String password, String nickname, String gender, String birth) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.gender = gender;
		this.birth = birth;
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
	
	
	
}
