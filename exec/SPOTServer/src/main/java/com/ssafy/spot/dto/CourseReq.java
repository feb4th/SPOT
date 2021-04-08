package com.ssafy.spot.dto;

public class CourseReq {
	private String course_id;
	private String name;
	private String user_id;
	private String spot_id;
	private String orders;
	private String latitude;
	private String longitude;
	private String type;
	private String date;
	private String course_name;
	
	public CourseReq() {
	}

	public CourseReq(String course_id, String name, String user_id, String spot_id, String orders, String latitude,
			String longitude, String type, String date, String course_name) {
		super();
		this.course_id = course_id;
		this.name = name;
		this.user_id = user_id;
		this.spot_id = spot_id;
		this.orders = orders;
		this.latitude = latitude;
		this.longitude = longitude;
		this.type = type;
		this.date = date;
		this.course_name = course_name;
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

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
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

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	
	
}
