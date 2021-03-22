package com.ssafy.spot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ssafy.spot.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	User findByName(String name);
}
