package com.ssafy.spot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spot.model.BasicResponse;
import com.ssafy.spot.service.TourSightService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("TourReviewController V1")
@CrossOrigin
@RestController
public class TourSightController {
	
	@Autowired
	TourSightService service;
	
	@ApiOperation(value = "관광지 상세정보 조회", notes = "message : success, fail과 성공시 리뷰리스트 반환", response = String.class)
	@GetMapping("/toursight/{id}")
	public Object findTourSight(@ApiParam(value = "String", required = true) @PathVariable String id){
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
}
