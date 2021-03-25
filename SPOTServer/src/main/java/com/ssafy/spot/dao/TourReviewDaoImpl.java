package com.ssafy.spot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.spot.dto.TourReview;
import com.ssafy.spot.mapper.TourReviewMapper;

@Repository
public class TourReviewDaoImpl implements TourReviewDao {
	
	@Autowired
	TourReviewMapper mapper;
	
	@Override
	public List<TourReview> findById(String id) {
		return mapper.findById(id);
	}
	
	@Override
	public void insertReview(TourReview review) {
		mapper.insertReview(review);
	}
	
	@Override
	public void updateReview(TourReview review) {
		mapper.updateReview(review);
	}
	
	@Override
	public void deleteReview(String id) {
		mapper.deleteReview(id);
	}
}
