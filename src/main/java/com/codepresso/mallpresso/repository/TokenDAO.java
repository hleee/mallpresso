package com.codepresso.mallpresso.repository;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.mallpresso.domain.TokenVO;

@Repository
public class TokenDAO {

	static Logger logger = LoggerFactory.getLogger(TokenDAO.class);

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	public TokenVO tokenVO;

	public int insertOneToken(TokenVO tokenVO) throws DataAccessException {
		return sqlSession.insert("mapper.token.insertOneToken", tokenVO);
	}

	public TokenVO selectOneTokenRowByToken(String token) {
		return sqlSession.selectOne("mapper.token.selectOneTokenRowByToken", token);
	}

}
