package com.ssafy.spot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spot.model.BasicResponse;
import com.ssafy.spot.service.TourReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("TourReviewController V1")
@RestController
@CrossOrigin
public class TourReviewController {
	
	@Autowired
	TourReviewService service;
	
	@ApiOperation(value = "관광지의 리뷰 조회", notes = "message : success, fail과 성공시 리뷰리스트 반환", response = List.class)
	@GetMapping("/review")
	public ResponseEntity findReviews(){
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		
		try {
			
			result.message = "";
		}catch(Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status = HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
	
}
