package com.codepresso.mallpresso.domain;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
public class TokenVO {

	private String token;
	private String email;
	private String createdAt;

}
