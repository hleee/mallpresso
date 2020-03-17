package com.codepresso.mallpresso.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.mallpresso.domain.BasketVO;

@Repository
public class BasketDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<BasketVO> selectBasketByMemberID(long memberID) throws DataAccessException {
		return sqlSession.selectList("mapper.basket.selectBasketByMemberID", memberID);
	}
}
