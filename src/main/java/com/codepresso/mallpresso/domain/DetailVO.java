package com.codepresso.mallpresso.domain;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
public class DetailVO {

	private long id;
	private long productID;
	private String description;
	private String image;
	private String createdAt;
	
}
