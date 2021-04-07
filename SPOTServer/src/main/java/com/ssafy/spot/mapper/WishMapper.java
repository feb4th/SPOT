package com.ssafy.spot.mapper;

import java.util.HashMap;
import java.util.List;

import com.ssafy.spot.dto.AddWish;
import com.ssafy.spot.dto.WishList;

public interface WishMapper {
	public void insertWish(AddWish wish);
	public HashMap<String, String> checkWish(String user_id, String spot_id);
	public void deleteWish(String wishlist_id);
	public List<HashMap<String, String>> wishlist(String user_id);
	public HashMap<String, String> findLat(String spot_id);
}
