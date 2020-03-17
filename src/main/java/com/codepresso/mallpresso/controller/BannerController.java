package com.codepresso.mallpresso.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.mallpresso.domain.ResponseVO;
import com.codepresso.mallpresso.service.BannerService;

@RestController
@RequestMapping("/*")
public class BannerController {

	static Logger logger = LoggerFactory.getLogger(BannerController.class);

	@Autowired
	public BannerService bannerService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseVO selectFiveLatestBanners() throws Exception {
		return bannerService.selectFiveLatestBanners();
	}
}
