package com.codepresso.mallpresso.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.mallpresso.domain.ResponseVO;
import com.codepresso.mallpresso.service.ProductService;

@RestController
@RequestMapping("/*")
public class ProductController {

	static Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	public ProductService productService;

	// 여섯 개씩 조회 (한 페이지 호출)
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ResponseVO selectSixProducts(@CookieValue(value = "accesstoken", required = false) String logInToken,
			@RequestParam("page") Long page) throws Exception {
		return productService.selectSixProducts(logInToken, page);
	}

	// 상세 조회

	
}
