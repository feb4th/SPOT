package com.ssafy.spot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.spot.dao.CourseDao;
import com.ssafy.spot.dto.Course;

@Service
public class CourseServiceImpl implements CourseService{
	@Autowired
	CourseDao dao;

	@Override
	public void insertCourse(Course course) {
		dao.insertCourse(course);
	}
	
}
