package com.ssafy.spot.dto;

public class WishList {
	private String wishlist_id;
	private String user_id;
	private String spot_id;
	private String type;
	
	public WishList() {
	}

	public WishList(String wishlist_id, String user_id, String spot_id, String type) {
		this.wishlist_id = wishlist_id;
		this.user_id = user_id;
		this.spot_id = spot_id;
		this.type = type;
	}

	public String getWishlist_id() {
		return wishlist_id;
	}

	public void setWishlist_id(String wishlist_id) {
		this.wishlist_id = wishlist_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSpot_id() {
		return spot_id;
	}

	public void setSpot_id(String spot_id) {
		this.spot_id = spot_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
