package com.ssafy.spot.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.spot.dao.CourseDao;
import com.ssafy.spot.dto.Course;
import com.ssafy.spot.dto.CourseReq;

@Service
public class CourseServiceImpl implements CourseService{
	@Autowired
	CourseDao dao;

	@Override
	public void insertCourse(CourseReq list) {
		dao.insertCourse(list);
	}

	@Override
	public int findById(String user_id, String name) {
		return dao.findById(user_id, name);
	}

	@Override
	public List<HashMap<String, String>> courseList(String user_id) {
		return dao.courseList(user_id);
	}

	@Override
	public void updateCourse(CourseReq list) {
		dao.updateCourse(list);
	}

	@Override
	public List<String> findIdByName(String user_id, String name) {
		return dao.findIdByName(user_id, name);
	}

	@Override
	public String findDateByName(String user_id, String name) {
		return dao.findDateByName(user_id, name);
	}

	@Override
	public void deleteCourse(String course_id) {
		dao.deleteCourse(course_id);
	}

	@Override
	public List<Course> readCourse(String user_id, String name) {
		return dao.readCourse(user_id, name);
	}

	@Override
	public void deleteCourseList(String user_id, String name) {
		dao.deleteCourseList(user_id, name);
	}
	
}
