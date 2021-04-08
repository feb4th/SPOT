package com.ssafy.spot.mapper;

import java.util.HashMap;
import java.util.List;

import com.ssafy.spot.dto.Course;
import com.ssafy.spot.dto.CourseReq;

public interface CourseMapper {
	public void insertCourse(CourseReq list);
	public int findById(String user_id, String name);
	public List<HashMap<String, String>> courseList(String user_id);
	public void updateCourse(CourseReq list);
	public List<String> findIdByName(String user_id, String course_id);
	public String findDateByName(String user_id, String course_id);
	public void deleteCourse(String course_id);
	public List<Course> readCourse(String user_id, String course_id);
	public void deleteCourseList(String user_id, String course_id);
	public void insertCourseId(String user_id);
	public int findCourseId();
	public void reset(String course_id);
}
