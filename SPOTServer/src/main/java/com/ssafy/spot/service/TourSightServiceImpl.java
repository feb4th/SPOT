package com.ssafy.spot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.spot.dao.TourSightDao;
import com.ssafy.spot.dto.TourSight;

@Service
public class TourSightServiceImpl implements TourSightService {
	
	@Autowired
	TourSightDao dao;
	
	@Override
	public TourSight findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public List<TourSight> findByName(String name) {
		return dao.findByName(name);
	}
	
	@Override
	public List<TourSight> findByRange(String lat, String lng, String range) {
		return dao.findByRange(lat, lng, range);
	}
}
