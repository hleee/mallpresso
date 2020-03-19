package com.codepresso.mallpresso.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codepresso.mallpresso.domain.LogInTokenVO;
import com.codepresso.mallpresso.domain.ProductAndBasketVO;
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

	// 여섯 개씩 조회 (한 페이지 호출)
	public ResponseVO selectSixProducts(String logInToken, long page) throws Exception {
		ResponseVO responseVO = new ResponseVO();
		long offsetValue = (page - 1) * 6;
		List<ProductVO> productVOList = productDAO.selectSixProducts(offsetValue);
		if (logInToken == null) {
			responseVO.setCode(HttpStatus.OK.value());
			responseVO.setMessage("Success");
			responseVO.setData(productVOList);
		} else {
			ProductAndBasketVO productAndBasketVO = new ProductAndBasketVO();
			LogInTokenVO logInTokenVO = tokenDAO.selectOneRowByLogInToken(logInToken);
			long memberID = logInTokenVO.getMemberID();
			productAndBasketVO.setOffsetValue(offsetValue);
			productAndBasketVO.setMemberID(memberID);
			List<ProductVO> productAndBasketVOList = productDAO.selectSixProductsWithBasketInfo(productAndBasketVO);
			responseVO.setCode(HttpStatus.OK.value());
			responseVO.setMessage("Success");
			responseVO.setData(productAndBasketVOList);
		}
		return responseVO;
	}
	
	// 상세 보기
	
	
}
