package com.codepresso.mallpresso.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codepresso.mallpresso.domain.BasketVO;
import com.codepresso.mallpresso.domain.DetailVO;
import com.codepresso.mallpresso.domain.LogInTokenVO;
import com.codepresso.mallpresso.domain.ProductAndBasketVO;
import com.codepresso.mallpresso.domain.ProductAndDetailVO;
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
		List<ProductVO> tokenNullProductVOList = productDAO.selectSixProducts(offsetValue);
		if (logInToken == null) {
			responseVO.setCode(HttpStatus.OK.value());
			responseVO.setMessage("Success");
			responseVO.setData(tokenNullProductVOList);
		} else {
			ProductAndBasketVO productAndBasketVO = new ProductAndBasketVO();
			LogInTokenVO logInTokenVO = tokenDAO.selectOneRowByLogInToken(logInToken);
			long memberID = logInTokenVO.getMemberID();
			productAndBasketVO.setOffsetValue(offsetValue);
			productAndBasketVO.setMemberID(memberID);
			List<ProductVO> tokenNotNullProductVOList = productDAO.selectSixProductsWithBasketInfo(productAndBasketVO);
			responseVO.setCode(HttpStatus.OK.value());
			responseVO.setMessage("Success");
			responseVO.setData(tokenNotNullProductVOList);
		}
		return responseVO;
	}

	// 상세 조회
	public ResponseVO selectOneDetail(String logInToken, long productID) throws Exception {
		ResponseVO responseVO = new ResponseVO();
		BasketVO basketVO = new BasketVO();
		LogInTokenVO logInTokenVO = new LogInTokenVO();
		ProductVO productVO = productDAO.selectOneProductByID(productID);
		ProductAndDetailVO productAndDetailVO = new ProductAndDetailVO();
		productAndDetailVO.setId(productVO.getId());
		productAndDetailVO.setName(productVO.getName());
		productAndDetailVO.setImage(productVO.getImage());
		productAndDetailVO.setDescription(productVO.getDescription());
		productAndDetailVO.setOriginalPrice(productVO.getOriginalPrice());
		productAndDetailVO.setDiscountedPrice(productVO.getDiscountedPrice());
		productAndDetailVO.setCreatedAt(productVO.getCreatedAt());
		if (logInToken == null) {
			productAndDetailVO.setIsAdded(null);
		} else {
			logInTokenVO = tokenDAO.selectOneRowByLogInToken(logInToken);
			long memberID = logInTokenVO.getMemberID();
			basketVO.setMemberID(memberID);
			basketVO.setProductID(productID);
			basketVO = basketDAO.selectBasketByMemberIDAndProductID(basketVO);
			if (basketVO != null) {
				productAndDetailVO.setIsAdded(true);
			} else {
				productAndDetailVO.setIsAdded(false);
			}
		}
		List<DetailVO> detailVOList = productDAO.selectAllDetails(productID);
		productAndDetailVO.setDetail(detailVOList);
		responseVO.setCode(HttpStatus.OK.value());
		responseVO.setMessage("Success");
		responseVO.setData(productAndDetailVO);
		return responseVO;
	}
}
