package com.ssafy.spot.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
			List<Object> ob = new LinkedList<Object>();
			ob.add(service.findById(id));
			ob.add(service.findImage(id));
			result.result = ob;
			result.message = "success";
		}catch(Exception e) {
			result.message = "fail";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status = HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
	@ApiOperation(value = "관광지 검색어 자동완성", notes = "message : success, fail과 성공시 리뷰리스트 반환", response = List.class)
	@GetMapping("/toursight/auto/{name}")
	public Object findTourSightAuto(@ApiParam(value = "String", required = true) @PathVariable String name){
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		
		try {
			result.result = service.findByName(name + "%");
			result.message = "success";
		}catch(Exception e) {
			result.message = "fail";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<>(result, status);
	}
	@ApiOperation(value = "관광지 검색어 자동완성", notes = "message : success, fail과 성공시 리뷰리스트 반환", response = List.class)
	@GetMapping("/toursight/around/{lat}/{lng}/{range}")
	public Object findTourSightAround(@ApiParam(value = "String", required = true) @PathVariable String lat,
			@PathVariable String lng, @PathVariable String range){
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		
		try {
			result.result = service.findByRange(lat, lng, range);
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
