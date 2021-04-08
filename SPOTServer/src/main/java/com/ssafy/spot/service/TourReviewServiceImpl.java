package com.ssafy.spot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.spot.dao.TourReviewDao;
import com.ssafy.spot.dto.TourReview;

@Service
public class TourReviewServiceImpl implements TourReviewService {
	
	@Autowired
	TourReviewDao dao;
	
	@Override
	public List<TourReview> findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public void insertReview(TourReview review) {
		dao.insertReview(review);
	}
	
	@Override
	public void updateReview(TourReview review) {
		dao.updateReview(review);
	}
	
	@Override
	public void deleteReview(String id) {
		dao.deleteReview(id);
	}
}
