package com.codepresso.mallpresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.mallpresso.domain.ResponseVO;
import com.codepresso.mallpresso.service.BannerService;

@RestController
@RequestMapping("/*")
public class BannerController {

	@Autowired
	public BannerService bannerService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseVO selectFiveLatestBanners() throws Exception {
		return bannerService.selectFiveLatestBanners();
	}
}
