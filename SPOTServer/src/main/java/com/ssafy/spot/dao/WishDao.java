package com.ssafy.spot.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.spot.dto.WishList;

public interface WishDao {
	public void insertWish(WishList wish);
	public HashMap<String, String> checkWish(String user_id, String spot_id);
	public void deleteWish(String wishlist_id);
	public List<HashMap<String, String>> wishlist(String user_id);
}
