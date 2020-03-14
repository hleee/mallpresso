package com.codepresso.mallpresso.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.mallpresso.domain.MemberVO;
import com.codepresso.mallpresso.domain.ResponseVO;
import com.codepresso.mallpresso.domain.TokenVO;
import com.codepresso.mallpresso.service.TokenService;

@RestController
@RequestMapping("/*")
public class TokenController {

	static Logger logger = LoggerFactory.getLogger(TokenController.class);

	@Autowired
	public TokenVO tokenVO;

	@Autowired
	public TokenService tokenService;

	@Autowired
	public ResponseVO responseVO;

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseVO insertOneToken(@RequestBody MemberVO memberVO) throws Exception {
		return tokenService.insertOneToken(memberVO);
	}
}
