package com.ssafy.spot.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spot.dto.AddWish;
import com.ssafy.spot.dto.WishList;
import com.ssafy.spot.model.BasicResponse;
import com.ssafy.spot.service.WishService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class WishController {
	@Autowired
	WishService service;
	
	@PostMapping(value= "/wish")
	@ApiOperation(value = "Wishlist 추가", notes= "Wishlist 추가 요청")
	public Object wish(@RequestBody AddWish wish) {
		
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		try {
			if(wish.getId().compareTo("500000")<0) {
				wish.setType("0");
			}
			else if(wish.getId().compareTo("500000")>0) {
				wish.setType("1");
			}
			service.insertWish(wish);
			HashMap<String, String> wish_id=service.checkWish(wish.getUser_id(), wish.getId());
			result.message = "add wish";
			result.result=wish_id;
		} catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status= HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
	
	@DeleteMapping(value = "/wish/{wishlist_id}")
	@ApiOperation(value = "wish 취소", notes = "wish 취소요청을 보낸다.")
	public Object unlike(@PathVariable String wishlist_id) {
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		try {
			service.deleteWish(wishlist_id);
			result.message = "del wish success";
		} catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status= HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
	
	@GetMapping(value = "/wish/{user_id}")
	@ApiOperation(value = "wishlist 목록", notes = "wishlist 보여준다.")
	public Object wishlist(@PathVariable String user_id) {
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		try {
			System.out.println(service.findLat("500012"));
			List<HashMap<String, String>> list= service.wishlist(user_id);
			if(list!=null) {
				result.message = "success";
				result.result=list;
			}
		} catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status= HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
		
	}
	
	@GetMapping(value = "/wishcheck")
	@ApiOperation(value = "wishlist 체크", notes = "wishlist 체크")
	public Object wishcheck(@RequestParam String user_id, @RequestParam String spot_id) {
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		try {
			HashMap<String, String> check= service.checkWish(user_id, spot_id);
			if(check!=null) {
				result.message = "success";
				result.result=check;
			}
		} catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status= HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
}
