package com.ssafy.spot.dto;

public class TourSight {
	
	private String toursight_id;
	private String name;
	private String new_address;
	private String old_address;
	private String lat;
	private String lng;
	private String facilities;
	private String park;
	private String info;
	private String tel;
	
	public TourSight() {}
	
	@Override
	public String toString() {
		return "TourSight [toursight_id=" + toursight_id + ", name=" + name + ", new_address=" + new_address
				+ ", old_address=" + old_address + ", lat=" + lat + ", lng=" + lng + ", facilities=" + facilities
				+ ", park=" + park + ", info=" + info + ", tel=" + tel + "]";
	}

	public String getToursight_id() {
		return toursight_id;
	}

	public void setToursight_id(String toursight_id) {
		this.toursight_id = toursight_id;
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

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
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
	
}
