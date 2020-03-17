package com.codepresso.mallpresso.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.mallpresso.domain.ProductVO;

@Repository
public class ProductDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<ProductVO> selectSixProducts(long lastProductID) throws DataAccessException {
		return sqlSession.selectList("mapper.product.selectSixProducts", lastProductID);
	}

	public List<ProductVO> selectAllProducts() throws DataAccessException {
		return sqlSession.selectList("mapper.product.selectAllProducts");
	}
}
