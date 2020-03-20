package com.codepresso.mallpresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.mallpresso.domain.BasketVO;
import com.codepresso.mallpresso.domain.ResponseVO;
import com.codepresso.mallpresso.service.BasketService;

@RestController
@RequestMapping("/*")
public class BasketController {

	@Autowired
	public BasketService basketService;

	// 장바구니 조회
	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public ResponseVO selectBasketByMemberID(@CookieValue(value = "accesstoken", required = false) String logInToken)
			throws Exception {
		return basketService.selectBasketByMemberID(logInToken);
	}

	// 장바구니에 추가
	@RequestMapping(value = "/basket", method = RequestMethod.POST)
	public ResponseVO insertToBasket(@CookieValue(value = "accesstoken", required = false) String logInToken,
			@RequestBody BasketVO basketVO) throws Exception {
		return basketService.insertToBasket(logInToken, basketVO);
	}

	// 장바구니에서 삭제
	@RequestMapping(value = "/basket", method = RequestMethod.DELETE)
	public ResponseVO deleteFromBasket(@CookieValue(value = "accesstoken", required = false) String logInToken,
			@RequestBody BasketVO basketVO) throws Exception {
		return basketService.deleteFromBasket(logInToken, basketVO);
	}
}
