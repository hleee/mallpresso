package com.codepresso.mallpresso.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codepresso.mallpresso.domain.BasketVO;
import com.codepresso.mallpresso.domain.MemberVO;
import com.codepresso.mallpresso.domain.ProductVO;
import com.codepresso.mallpresso.domain.ResponseVO;
import com.codepresso.mallpresso.domain.TokenVO;
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
	public MemberVO memberVO;

	@Autowired
	public ProductVO productVO;

	@Autowired
	public TokenDAO tokenDAO;

	@Autowired
	public BasketDAO basketDAO;

	@Autowired
	public BasketVO basketVO;

	@Autowired
	public TokenVO tokenVO;

	@Autowired
	public ResponseVO responseVO;

	public ResponseVO selectSixProducts(String logInToken, long lastProductID) throws Exception {
		tokenVO = tokenDAO.selectOneRowByLogInToken(logInToken);
		String email = tokenVO.getEmail();
		memberVO = memberDAO.selectOneMemberByEmail(email);
		long memberID = memberVO.getId();
		List<BasketVO> basketVOList = basketDAO.selectBasketByMemberID(memberID);
		List<ProductVO> productVOList = productDAO.selectSixProducts(lastProductID);
		for (int i = 0; i < basketVOList.size(); i++) {
			
		}
		return responseVO;
	}
}
