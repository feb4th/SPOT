package com.ssafy.spot.dao;

import java.util.List;

import com.ssafy.spot.dto.TourReview;

public interface TourReviewDao {
	public List<TourReview> findById(String id);
	public void insertReview(TourReview review);
	public void updateReview(TourReview review);
}
