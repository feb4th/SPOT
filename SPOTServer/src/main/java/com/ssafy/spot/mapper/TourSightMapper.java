package com.ssafy.spot.mapper;

import java.util.List;

import com.ssafy.spot.dto.TourSight;

public interface TourSightMapper {
	public TourSight findById(String id);
	public List<TourSight> findByName(String name);
	public List<TourSight> findByRange(String lat, String lng, String range);
}
