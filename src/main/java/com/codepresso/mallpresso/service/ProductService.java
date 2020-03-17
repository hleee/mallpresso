package com.codepresso.mallpresso.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codepresso.mallpresso.domain.BasketVO;
import com.codepresso.mallpresso.domain.LogInTokenVO;
import com.codepresso.mallpresso.domain.MemberVO;
import com.codepresso.mallpresso.domain.ProductVO;
import com.codepresso.mallpresso.domain.ResponseVO;
import com.codepresso.mallpresso.repository.BasketDAO;
import com.codepresso.mallpresso.repository.MemberDAO;
import com.codepresso.mallpresso.repository.ProductDAO;
import com.codepresso.mallpresso.repository.TokenDAO;

@Service
public class ProductService {

	static Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	public MemberDAO memberDAO;

	@Autowired
	public ProductDAO productDAO;

	@Autowired
	public TokenDAO tokenDAO;

	@Autowired
	public BasketDAO basketDAO;

	// 여섯 개씩 조회
	public ResponseVO selectSixProducts(String logInToken, long lastProductID) throws Exception {
		ResponseVO responseVO = new ResponseVO();
		LogInTokenVO logInTokenVO = new LogInTokenVO();
		MemberVO memberVO = new MemberVO();
		logInTokenVO = tokenDAO.selectOneRowByLogInToken(logInToken);
		String email = logInTokenVO.getEmail();
		memberVO = memberDAO.selectOneMemberByEmail(email);
		long memberID = memberVO.getId();
		List<BasketVO> basketVOList = basketDAO.selectBasketByMemberID(memberID);
		List<ProductVO> productVOList = productDAO.selectSixProducts(lastProductID);
		for (int i = 0; i < basketVOList.size(); i++) {

		}
		return responseVO;
	}
}
