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
import com.codepresso.mallpresso.service.MemberService;

@RestController
@RequestMapping("/*")
public class MemberController {

	static Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	public ResponseVO responseVO;

	@Autowired
	public MemberVO memberVO;

	@Autowired
	public MemberService memberService;

	@RequestMapping(value = "/member", method = RequestMethod.POST)
	public ResponseVO insertOneMember(@RequestBody MemberVO memberVO) throws Exception {
		return memberService.insertOneMember(memberVO);
	}

}
