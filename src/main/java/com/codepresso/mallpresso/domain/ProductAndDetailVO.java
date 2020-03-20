package com.codepresso.mallpresso.domain;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
public class ProductAndDetailVO {

	private long id;
	private String name;
	private String image;
	private String description;
	private long originalPrice;
	private long discountedPrice;
	private String createdAt;
	private Boolean isAdded;
	private Object detail;

}
