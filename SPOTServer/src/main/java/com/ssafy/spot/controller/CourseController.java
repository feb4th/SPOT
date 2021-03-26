package com.ssafy.spot.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spot.dto.Course;
import com.ssafy.spot.dto.CourseReq;
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
	
	public Object insertCourse(@RequestBody List<CourseReq> list) {
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		String user_id=list.get(0).getUser_id();
		String name=list.get(0).getName();
		try {
			if(service.findById(user_id, name)==0) {
				SimpleDateFormat format= new SimpleDateFormat("yyyyMMddHHmmss");
				Date time = new Date();
				String timeurl=format.format(time);
				
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setDate(timeurl);
					service.insertCourse(list.get(i));
				}
				result.message = "insert course";
			}
			else {
				result.message = "exist name";
			}
		} catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		status= HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
	
	@GetMapping(value = "/courselist/{user_id}")
	@ApiOperation(value = "코스list", notes = "코스list 조회")
	
	public Object insertCourse(@PathVariable String user_id) {
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		try {
			List<HashMap<String, String>> list=service.courseList(user_id);
			result.message = "course list";
			result.result = list;
		} catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		status= HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
	
	@PutMapping(value = "/course/{name}")
	@ApiOperation(value = "코스list 수정", notes = "코스list 수정")
	
	public Object updateCourse(@RequestBody List<CourseReq> list, @PathVariable String name, @RequestParam String user_id) {
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		try {
			List<String> id=service.findIdByName(user_id, name);
			
			if(id.size()==list.size()) {
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setCourse_id(id.get(i));
					service.updateCourse(list.get(i));
				}
			}
			else if(id.size()<list.size()) {
				for (int i = 0; i < id.size(); i++) {
					list.get(i).setCourse_id(id.get(i));
					service.updateCourse(list.get(i));
				}
				String date=service.findDateByName(user_id, name);
				for (int i = id.size(); i < list.size(); i++) {
					list.get(i).setDate(date);
					service.insertCourse(list.get(i));
				}
			}
			else {
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setCourse_id(id.get(i));
					service.updateCourse(list.get(i));
				}
				for (int i = list.size(); i < id.size(); i++) {
					service.deleteCourse(id.get(i));
				}
			}
			
			result.message = "update course list";
		} catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		status= HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
	
	@GetMapping(value = "/course/{user_id}")
	@ApiOperation(value = "코스조회", notes = "코스 상세조회")
	
	public Object readCourse(@PathVariable String user_id, @RequestParam String name) {
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		try {
			List<Course> list=service.readCourse(user_id, name);
			result.message = "course list info";
			result.result = list;
		} catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		status= HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
	
	@DeleteMapping(value = "/courselist/{user_id}")
	@ApiOperation(value = "코스list 삭제", notes = "코스list 삭제")
	
	public Object deleteCourseList(@PathVariable String user_id, @RequestParam String name) {
		BasicResponse result = new BasicResponse();
		HttpStatus status;
		try {
			service.deleteCourseList(user_id, name);
			result.message = "delete course list";
		} catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		status= HttpStatus.ACCEPTED;
        return new ResponseEntity<>(result, status);
	}
}
