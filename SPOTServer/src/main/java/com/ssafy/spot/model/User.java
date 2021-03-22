package com.ssafy.spot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "user")
public class User {
	@Id
	private String _id;
	private String name;
	private String email;
	private String password;
	private String nickname;                 
	
	public User(String email, String password, String nickname, String name){
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.name = name;
	}
	
}