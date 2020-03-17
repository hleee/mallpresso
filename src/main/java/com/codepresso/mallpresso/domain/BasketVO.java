package com.codepresso.mallpresso.domain;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
public class BasketVO {

	private long id;
	private long memberId;
	private long productId;
	private String createdAt;

}
