package com.ssafy.spot.dto;

public class TourReview {

	private String tour_review_id;
	private String user_id;
	private String toursight_id;
	private String content;
	private String date;
	private String score;
	
	public TourReview(String tour_review_id, String user_id, String toursight_id, String content, String date,
			String score) {

	}

	public String getTour_review_id() {
		return tour_review_id;
	}

	public void setTour_review_id(String tour_review_id) {
		this.tour_review_id = tour_review_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getToursight_id() {
		return toursight_id;
	}

	public void setToursight_id(String toursight_id) {
		this.toursight_id = toursight_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	
}
