package com.ssafy.spot.mapper;

import java.util.List;

import com.ssafy.spot.dto.TourReview;

public interface TourReviewMapper {
	public List<TourReview> findById(String id);
}
