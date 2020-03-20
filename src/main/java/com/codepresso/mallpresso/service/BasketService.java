package com.codepresso.mallpresso.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codepresso.mallpresso.domain.BasketVO;
import com.codepresso.mallpresso.domain.LogInTokenVO;
import com.codepresso.mallpresso.domain.ProductVO;
import com.codepresso.mallpresso.domain.ResponseVO;
import com.codepresso.mallpresso.repository.BasketDAO;
import com.codepresso.mallpresso.repository.ProductDAO;
import com.codepresso.mallpresso.repository.TokenDAO;

@Service
public class BasketService {

	static Logger logger = LoggerFactory.getLogger(BasketService.class);

	@Autowired
	public BasketDAO basketDAO;

	@Autowired
	public TokenDAO tokenDAO;

	@Autowired
	public ProductDAO productDAO;

	// 장바구니 조회
	public ResponseVO selectBasketByMemberID(String logInToken) throws Exception {
		ResponseVO responseVO = new ResponseVO();
		LogInTokenVO logInTokenVO = tokenDAO.selectOneRowByLogInToken(logInToken);
		long memberID = logInTokenVO.getMemberID();
		List<ProductVO> basketList = basketDAO.selectBasketByMemberID(memberID);
		ProductVO[] basketArray = new ProductVO[basketList.size()];
		for (int i = 0; i < basketList.size(); i++) {
			basketList.get(i).setIsAdded(true);
			basketArray[i] = basketList.get(i);
		}
		responseVO.setCode(HttpStatus.OK.value());
		responseVO.setMessage("Success");
		responseVO.setData(basketArray);
		return responseVO;
	}

	// 장바구니에 추가
	public ResponseVO insertToBasket(String logInToken, BasketVO basketVO) throws Exception {
		ResponseVO responseVO = new ResponseVO();
		LogInTokenVO logInTokenVO = tokenDAO.selectOneRowByLogInToken(logInToken);
		long memberID = logInTokenVO.getMemberID();
		basketVO.setMemberID(memberID);
		long productID = basketVO.getProductID();
		basketDAO.insertToBasket(basketVO);
		ProductVO productVO = productDAO.selectOneProductByID(productID);
		productVO.setIsAdded(true);
		responseVO.setCode(HttpStatus.OK.value());
		responseVO.setMessage("Success");
		responseVO.setData(productVO);
		return responseVO;
	}

	// 장바구니에서 삭제
	public ResponseVO deleteFromBasket(String logInToken, BasketVO basketVO) throws Exception {
		ResponseVO responseVO = new ResponseVO();
		LogInTokenVO logInTokenVO = tokenDAO.selectOneRowByLogInToken(logInToken);
		long memberID = logInTokenVO.getMemberID();
		basketVO.setMemberID(memberID);
		long productID = basketVO.getProductID();
		basketDAO.deleteFromBasket(basketVO);
		ProductVO productVO = productDAO.selectOneProductByID(productID);
		productVO.setIsAdded(false);
		responseVO.setCode(HttpStatus.OK.value());
		responseVO.setMessage("Success");
		responseVO.setData(productVO);
		return responseVO;
	}
}
