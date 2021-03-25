package com.ssafy.spot.service;

import java.util.List;

import com.ssafy.spot.dto.TourReview;

public interface TourReviewService {
	public List<TourReview> findById(String id);
	public void insertReview(TourReview review);
	public void updateReview(TourReview review);
	public void deleteReview(String id);
}
