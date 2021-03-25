package com.ssafy.spot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spot.dto.TourReview;
import com.ssafy.spot.model.BasicResponse;
import com.ssafy.spot.service.TourReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("TourReviewController V1")
@RestController
@CrossOrigin
public class TourReviewController {
	
	@Autowired
	TourReviewService service;
	
	@ApiOperation(value = "관광지의 리뷰 조회", notes = "message : success, fail과 성공시 리뷰리스트 반환", response = List.class)
	@GetMapping("/review/{id}")
	public Object findReviews(@ApiParam(value = "String", required = true) @PathVariable String id){
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		
		try {
			result.result = service.findById(id);
			result.message = "success";
		}catch(Exception e) {
			result.message = "fail";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status = HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
	
	@ApiOperation(value = "관광지의 리뷰 생성", notes = "message : success, fail", response = TourReview.class)
	@PostMapping("/review")
	public Object addReview(@ApiParam(value = "TourReview", required = true) @RequestBody TourReview review){
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		
		try {
			service.insertReview(review);
			result.message = "success";
		}catch(Exception e) {
			result.message = "fail";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status = HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
	
	@ApiOperation(value = "관광지의 리뷰 수정", notes = "message : success, fail", response = TourReview.class)
	@PutMapping("/review")
	public Object updateReview(@ApiParam(value = "TourReview", required = true) @RequestBody TourReview review){
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		
		try {
			service.updateReview(review);
			result.message = "success";
		}catch(Exception e) {
			result.message = "fail";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status = HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
	
}
