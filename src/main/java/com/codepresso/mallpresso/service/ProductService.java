package com.codepresso.mallpresso.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
			String email = tokenDAO.selectOneRowByLogInToken(logInToken).getEmail();
			logger.info("User logged in: " + memberDAO.selectOneMemberByEmail(email).getName());
			long memberID = memberDAO.selectOneMemberByEmail(email).getId();
			productAndBasketVO.setOffsetValue(offsetValue);
			productAndBasketVO.setMemberID(memberID);
			List<ProductVO> productAndBasketVOList = productDAO.selectSixProductsWithBasketInfo(productAndBasketVO);
			logger.info("ProductVO list: " + productAndBasketVOList);
			ProductVO[] productAndBasketVOArray = new ProductVO[productAndBasketVOList.size()];
			for (int i = 0; i < productAndBasketVOList.size(); i++) {
				ProductVO productVO = new ProductVO();
				productVO = productAndBasketVOList.get(i);
				if (productVO.getIsAdded() != null) {
					productVO.setIsAdded(true);
				} else {
					productVO.setIsAdded(false);
				}
				productAndBasketVOArray[i] = productVO;
			}
			responseVO.setCode(HttpStatus.OK.value());
			responseVO.setMessage("Success");
			responseVO.setData(productAndBasketVOArray);
		}
		return responseVO;
	}
}
