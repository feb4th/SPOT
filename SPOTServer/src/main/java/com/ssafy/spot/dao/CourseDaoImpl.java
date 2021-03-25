package com.ssafy.spot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.spot.dto.Course;
import com.ssafy.spot.mapper.CourseMapper;

@Repository
public class CourseDaoImpl implements CourseDao{
	@Autowired
	CourseMapper mapper;

	@Override
	public void insertCourse(Course course) {
		mapper.insertCourse(course);
	}
	
}
