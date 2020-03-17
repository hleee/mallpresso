package com.codepresso.mallpresso.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.mallpresso.domain.EmailCheckTokenVO;
import com.codepresso.mallpresso.domain.LogInTokenVO;

@Repository
public class TokenDAO {

	@Autowired
	private SqlSession sqlSession;

	public int insertOneLogInToken(LogInTokenVO logInTokenVO) throws DataAccessException {
		return sqlSession.insert("mapper.logInToken.insertOneLogInToken", logInTokenVO);
	}

	public LogInTokenVO selectOneRowByLogInToken(String logInToken) {
		return sqlSession.selectOne("mapper.logInToken.selectOneRowByLogInToken", logInToken);
	}
	
	public int insertOneEmailCheckToken(EmailCheckTokenVO emailCheckTokenVO) throws DataAccessException {
		return sqlSession.insert("mapper.emailCheckToken.insertOneEmailCheckToken", emailCheckTokenVO);
	}

	public EmailCheckTokenVO selectOneRowByEmailCheckToken(String emailCheckToken) {
		return sqlSession.selectOne("mapper.emailCheckToken.selectOneRowByEmailCheckToken", emailCheckToken);
	}
}
