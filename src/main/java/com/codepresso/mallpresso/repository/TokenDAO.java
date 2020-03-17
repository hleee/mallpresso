package com.codepresso.mallpresso.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.mallpresso.domain.TokenVO;

@Repository
public class TokenDAO {

	@Autowired
	private SqlSession sqlSession;

	public int insertOneLogInToken(TokenVO tokenVO) throws DataAccessException {
		return sqlSession.insert("mapper.token.insertOneLogInToken", tokenVO);
	}

	public TokenVO selectOneRowByLogInToken(String logInToken) {
		return sqlSession.selectOne("mapper.token.selectOneRowByLogInToken", logInToken);
	}
}
