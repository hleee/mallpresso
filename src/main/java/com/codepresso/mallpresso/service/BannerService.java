package com.codepresso.mallpresso.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codepresso.mallpresso.domain.BannerVO;
import com.codepresso.mallpresso.domain.ResponseVO;
import com.codepresso.mallpresso.repository.BannerDAO;

@Service
public class BannerService {

	static Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	public BannerDAO bannerDAO;

	@Autowired
	public BannerVO bannerVO;

	@Autowired
	public ResponseVO responseVO;

	public ResponseVO selectFiveLatestBanners() throws Exception {
		List<BannerVO> bannerVOList = bannerDAO.selectFiveLatestBanners();
		BannerVO[] bannerVOArray = new BannerVO[bannerVOList.size()];
		for (int i = 0; i < bannerVOList.size(); i++) {
			bannerVOArray[i] = bannerVOList.get(i);
		}
		responseVO.setCode(HttpStatus.OK.value());
		responseVO.setMessage("Success");
		responseVO.setData(bannerVOArray);
		return responseVO;
	}

}