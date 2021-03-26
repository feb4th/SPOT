package com.ssafy.spot.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.spot.dto.Course;
import com.ssafy.spot.dto.CourseReq;
import com.ssafy.spot.mapper.CourseMapper;

@Repository
public class CourseDaoImpl implements CourseDao{
	@Autowired
	CourseMapper mapper;

	@Override
	public void insertCourse(CourseReq list) {
		mapper.insertCourse(list);
	}

	@Override
	public int findById(String user_id, String name) {
		return mapper.findById(user_id, name);
	}

	@Override
	public List<HashMap<String, String>> courseList(String user_id) {
		return mapper.courseList(user_id);
	}

	@Override
	public void updateCourse(CourseReq list) {
		mapper.updateCourse(list);
	}

	@Override
	public List<String> findIdByName(String user_id, String name) {
		return mapper.findIdByName(user_id, name);
	}

	@Override
	public String findDateByName(String user_id, String name) {
		return mapper.findDateByName(user_id, name);
	}

	@Override
	public void deleteCourse(String course_id) {
		mapper.deleteCourse(course_id);
	}

	@Override
	public List<Course> readCourse(String user_id, String name) {
		return mapper.readCourse(user_id, name);
	}

	@Override
	public void deleteCourseList(String user_id, String name) {
		mapper.deleteCourseList(user_id, name);
	}

	
}
