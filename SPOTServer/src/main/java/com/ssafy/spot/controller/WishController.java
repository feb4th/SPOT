package com.ssafy.spot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spot.model.BasicResponse;
import com.ssafy.spot.service.WishService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class WishController {
	@Autowired
	WishService service;
	
	@PostMapping(value= "/wish")
	@ApiOperation(value = "Wishlist", notes= "Wishlist 추가 요청")
	public Object wish(@RequestParam String user_id, @RequestParam String spot_id) {        
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		try {
			service.addwish(user_id, spot_id);
			result.message = "";
		}catch(Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status = HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
}
