package com.codepresso.mallpresso.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codepresso.mallpresso.repository.BasketDAO;

@Service
public class BasketService {

	static Logger logger = LoggerFactory.getLogger(BannerService.class);

	@Autowired
	public BasketDAO basketDAO;

}
