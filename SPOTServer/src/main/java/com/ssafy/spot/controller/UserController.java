package com.ssafy.spot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spot.model.User;
import com.ssafy.spot.repository.UserRepository;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")
	@ApiOperation(value = "addUser")
	@ApiImplicitParams({ @ApiImplicitParam(name = "user", value = "회원 객체", required = true, dataType = "User") })
	public ResponseEntity<Boolean> addUser(@RequestBody User user) {
		if (userRepository.save(user) != null) {
			userRepository.save(user);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else
			return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
	}
	
}
