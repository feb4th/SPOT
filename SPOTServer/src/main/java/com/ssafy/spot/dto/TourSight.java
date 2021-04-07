package com.ssafy.spot.dto;

public class TourSight {
	
	private String id;
	private String name;
	private String new_address;
	private String old_address;
	private String latitude;
	private String longitude;
	private String facilities;
	private String park;
	private String info;
	private String tel;
	private String img;
	
	

	public TourSight() {}

	@Override
	public String toString() {
		return "TourSight [id=" + id + ", name=" + name + ", new_address=" + new_address + ", old_address="
				+ old_address + ", latitude=" + latitude + ", longitude=" + longitude + ", facilities=" + facilities
				+ ", park=" + park + ", info=" + info + ", tel=" + tel + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNew_address() {
		return new_address;
	}

	public void setNew_address(String new_address) {
		this.new_address = new_address;
	}

	public String getOld_address() {
		return old_address;
	}

	public void setOld_address(String old_address) {
		this.old_address = old_address;
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

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
