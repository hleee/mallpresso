package com.codepresso.mallpresso.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.mallpresso.domain.BasketVO;
import com.codepresso.mallpresso.domain.ProductVO;


@Repository
public class BasketDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<ProductVO> selectBasketByMemberID(long memberID) throws DataAccessException {
		return sqlSession.selectList("mapper.basket.selectBasketByMemberID", memberID);
	}

	public long insertToBasket(BasketVO basketVO) throws DataAccessException {
		return sqlSession.insert("mapper.basket.insertToBasket", basketVO);
	}

	public long deleteFromBasket(BasketVO basketVO) throws DataAccessException {
		return sqlSession.delete("mapper.basket.deleteFromBasket", basketVO);
	}

	public BasketVO selectBasketByMemberIDAndProductID(BasketVO basketVO) throws DataAccessException {
		return sqlSession.selectOne("mapper.basket.selectBasketByMemberIDAndProductID", basketVO);
	}
}
