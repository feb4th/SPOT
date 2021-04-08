package com.ssafy.spot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spot.dto.Store;
import com.ssafy.spot.repository.StoreRepository;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class StoreController {
	@Autowired
	private StoreRepository storeRepository;
	
	@GetMapping("/store")
	@ApiOperation(value = "해당 이름에 대한 storeList 반환", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<List<Store>> findByName (@RequestParam String name) {
		List<Store> storeList = storeRepository.findByName(name);
		for(Store store : storeList){
			  System.out.println(store);
			}
		return new ResponseEntity<>(storeList, HttpStatus.OK);
	}

}
