package com.web.spot.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.spot.model.BasicResponse;
import com.web.spot.service.WishService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class WishController {
	@Autowired
	WishService service;
	
	@PostMapping(value= "/wish")
	@ApiOperation(value = "Wishlist 추가", notes= "Wishlist 추가 요청")
	public Object wish(@RequestParam String user_id, @RequestParam String spot_id) {
		service.addwish(user_id, spot_id);
		HashMap<String, String> wish=service.wishcheck(user_id,spot_id);
		
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "add wish";
        result.object=wish;
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/wish/{wishlist_id}")
	@ApiOperation(value = "wish 취소", notes = "wish 취소요청을 보낸다.")
	public Object unlike(@PathVariable String wishlist_id) {
		service.delwish(wishlist_id);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "del wish success";
        return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/wish/{user_id}")
	@ApiOperation(value = "wishlist 목록", notes = "wishlist 보여준다.")
	public Object wishlist(@PathVariable String user_id) {
		List<HashMap<String, String>> map= service.wishlist(user_id);
		if(map!=null) {
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        result.object=map;
	        return new ResponseEntity<>(result, HttpStatus.OK);
	      
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/wishcheck")
	@ApiOperation(value = "wishlist 체크", notes = "wishlist 체크")
	public Object wishcheck(@RequestParam String user_id, @RequestParam String spot_id) {
		HashMap<String, String> like= service.wishcheck(user_id,spot_id);
		if(like!=null) {
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        result.object=like;
	        return new ResponseEntity<>(result, HttpStatus.OK);
	      
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
