package com.ssafy.spot.dto;

public class AddWish {
	private String user_id;
	private String id;
	private String type;
	private String name;
	private String latitude;
	private String longitude;
	private String img;
	
	public AddWish() {
	}

	

	public AddWish(String user_id, String id, String type, String name, String latitude, String longitude, String img) {
		super();
		this.user_id = user_id;
		this.id = id;
		this.type = type;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.img = img;
	}



	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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



	public String getImg() {
		return img;
	}



	public void setImg(String img) {
		this.img = img;
	}
	
	
	
	
}
