package com.ssafy.spot.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spot.dto.SignupReq;
import com.ssafy.spot.dto.User;
import com.ssafy.spot.dto.UserUpdate;
import com.ssafy.spot.dto.loginReq;
import com.ssafy.spot.model.BasicResponse;
import com.ssafy.spot.service.JwtService;
import com.ssafy.spot.service.MailService;
import com.ssafy.spot.service.UserService;
import com.ssafy.spot.util.TempKey;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
public class UserController {
	@Autowired
	UserService service;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	private JwtService jwtservice;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@PostMapping(value = "/signup")
	@ApiOperation(value = "회원가입", notes = "성공 시 회원가입 완료")
	@ApiResponses({
		@ApiResponse(code= 200, message="회원가입 성공"),
		@ApiResponse(code= 409, message="이미 가입된 회원"),
	})
	public Object insertUser(@RequestBody SignupReq req) {
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		try {
			User check=service.findByEmail(req.getEmail());
			
			if(check==null) {
				service.insertUser(req);
				result.message = "success";
			}
			else {	
				result.message = "exist email";
			}
		} catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status= HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
	
	@DeleteMapping(value = "/user/{user_id}")
	@ApiOperation(value = "회원탈퇴")
	public Object deleteUser(@PathVariable String user_id) {
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		
		try {
			service.deleteUser(user_id);
			result.message = "success";
		} catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status= HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
	
	@PostMapping(value = "/login")
	@ApiOperation(value = "로그인", notes = "성공 시 로그인 완료")
	@ApiResponses({
		@ApiResponse(code= 200, message="로그인 성공"),
		@ApiResponse(code= 401, message="로그인 실패"),
	})
	public Object login(@RequestBody loginReq req, HttpServletResponse response) {
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		try {
			User user=service.login(req);
			
			if(user==null) {
				result.message = "fail";
			} else if(user.getEmailcheck().equals("0")) {
				result.message = "email";
			} else {	
				String token = jwtservice.create(user);
				logger.trace("로그인 토큰정보 : {}", token);
				response.setHeader("auth-token", token);
				result.message = "success";
				result.result=user;
			}
		} catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status= HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
		
	}
	
	@GetMapping(value = "/user/{user_id}")
	@ApiOperation(value = "멤버정보 가져오기")
	public Object findUserById(@PathVariable String user_id) {
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		try {
			HashMap<String, String> map= service.findById(user_id);
			if(map!=null) {
				result.message = "success";
				result.result=map;
			}
		} catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status= HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
		
	}
	
	@PutMapping(value = "/user/update")
	@ApiOperation(value = "회원정보수정")
	public Object updateuser(@RequestBody UserUpdate req) {
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		try {
			if(req.getPassword().equals("0")) {
				service.updateNickname(req);
			}
			else {
				service.updateUser(req);
			}
			result.message = "success";
		} catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status= HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
}
