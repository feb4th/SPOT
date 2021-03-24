package com.ssafy.spot.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "store")
public class Store {
	@Id
	private String _id;
	private int id;
	private String name;
	private String branch;
	private String area;
	private String tel;
	private String address;
	private String latitude;
	private String longitude;
	private String[] category_list;
	private String[] menu_list;
	private String[] bhour_list;
	private int review_cnt;
	private String[] review_list;
	
}
