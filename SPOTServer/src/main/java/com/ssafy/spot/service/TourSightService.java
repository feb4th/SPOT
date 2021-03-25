package com.ssafy.spot.service;

import java.util.List;

import com.ssafy.spot.dto.TourSight;

public interface TourSightService {
	public TourSight findById(String id);
	public List<TourSight> findByName(String name);
}
