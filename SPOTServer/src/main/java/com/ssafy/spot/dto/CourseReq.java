package com.ssafy.spot.dto;

public class CourseReq {
	private String course_id;
	private String name;
	private String user_id;
	private String spot_id;
	private String orders;
	private String memo;
	private String time;
	private String type;
	private String date;
	
	public CourseReq() {
	}
	
	public CourseReq(String course_id, String name, String user_id, String spot_id, String orders, String memo,
			String time, String type, String date) {
		this.course_id = course_id;
		this.name = name;
		this.user_id = user_id;
		this.spot_id = spot_id;
		this.orders = orders;
		this.memo = memo;
		this.time = time;
		this.type = type;
		this.date = date;
	}
	
	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	
	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
