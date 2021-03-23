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
		service.addwish(user_id, spot_id);
		
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "add wish";
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
