package com.ssafy.spot.dao;

import java.util.List;

import com.ssafy.spot.dto.TourSight;

public interface TourSightDao {
	public TourSight findById(String id);
	public List<TourSight> findByName(String name);
	public List<TourSight> findByRange(String latitude, String longitude, String range);
	public List<String> findImage(String id);
	public List<TourSight> tourSightList(String area);
	public String findOneImage(String id);
}
