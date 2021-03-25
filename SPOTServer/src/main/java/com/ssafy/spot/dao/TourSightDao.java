package com.ssafy.spot.dao;

import java.util.List;

import com.ssafy.spot.dto.TourSight;

public interface TourSightDao {
	public TourSight findById(String id);
	public List<TourSight> findByName(String name);
}
