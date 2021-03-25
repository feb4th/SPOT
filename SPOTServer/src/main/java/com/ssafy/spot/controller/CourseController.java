package com.ssafy.spot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spot.dto.Course;
import com.ssafy.spot.model.BasicResponse;
import com.ssafy.spot.service.CourseService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class CourseController {
	@Autowired
	CourseService service;
	
	@PostMapping(value = "/course")
	@ApiOperation(value = "코스생성", notes = "성공 시 코스생성 완료")
	
	public Object insertCourse(@RequestBody Course course) {
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		
		try {
			service.insertCourse(course);
			result.message = "insert course";
		} catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		status= HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
	

	
}
