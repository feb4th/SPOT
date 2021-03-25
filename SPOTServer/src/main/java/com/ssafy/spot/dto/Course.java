package com.ssafy.spot.dto;

public class Course {
	private String course_id;
	private String user_id;
	private String spot_id;
	private String order;
	private String type;
	
	public Course() {
	}

	public Course(String course_id, String user_id, String spot_id, String order, String type) {
		this.course_id = course_id;
		this.user_id = user_id;
		this.spot_id = spot_id;
		this.order = order;
		this.type = type;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSpot_id() {
		return spot_id;
	}

	public void setSpot_id(String spot_id) {
		this.spot_id = spot_id;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
