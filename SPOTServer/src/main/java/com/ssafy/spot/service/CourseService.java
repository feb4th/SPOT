package com.ssafy.spot.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.spot.dto.Course;
import com.ssafy.spot.dto.CourseReq;

public interface CourseService {
	public void insertCourse(CourseReq list);
	public int findById(String user_id, String name);
	public List<HashMap<String, String>> courseList(String user_id);
	public void updateCourse(CourseReq list);
	public List<String> findIdByName(String user_id, String name);
	public String findDateByName(String user_id, String name);
	public void deleteCourse(String course_id);
	public List<Course> readCourse(String user_id, String name);
	public void deleteCourseList(String user_id, String name);
}
