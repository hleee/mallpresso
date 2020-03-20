package com.codepresso.mallpresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.mallpresso.domain.MemberVO;
import com.codepresso.mallpresso.domain.ResponseVO;
import com.codepresso.mallpresso.service.MemberService;

@RestController
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	public MemberService memberService;

	// 이메일 중복 확인
	@PostMapping("/checkEmail")
	public ResponseVO insertOneEmailCheckToken(@RequestBody MemberVO emailOnlyVO) throws Exception {
		return memberService.insertOneEmailCheckToken(emailOnlyVO);
	}

	// 로그인
	@PostMapping("/logIn")
	public ResponseVO insertOneLogInToken(@RequestBody MemberVO memberVO) throws Exception {
		return memberService.insertOneLogInToken(memberVO);
	}

	// 회원 가입
	@PostMapping("/signUp")
	public ResponseVO insertOneMember(@CookieValue(value = "checktoken", required = false) String emailCheckToken,
			@RequestBody MemberVO memberVO) throws Exception {
		return memberService.insertOneMember(emailCheckToken, memberVO);
	}
}
