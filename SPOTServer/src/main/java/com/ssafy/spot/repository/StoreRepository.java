package com.ssafy.spot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ssafy.spot.dto.Store;

public interface StoreRepository extends MongoRepository<Store, String> {
	List<Store> findByName(String name);
}
