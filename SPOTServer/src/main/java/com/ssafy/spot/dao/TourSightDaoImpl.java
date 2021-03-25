package com.ssafy.spot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.spot.dto.TourSight;
import com.ssafy.spot.mapper.TourSightMapper;

@Repository
public class TourSightDaoImpl implements TourSightDao {
	
	@Autowired
	TourSightMapper mapper;
	
	@Override
	public TourSight findById(String id) {
		return mapper.findById(id);
	}
}
