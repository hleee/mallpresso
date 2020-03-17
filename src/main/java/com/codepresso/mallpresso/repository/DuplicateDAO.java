package com.codepresso.mallpresso.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.mallpresso.domain.DuplicateVO;

@Repository
public class DuplicateDAO {

	@Autowired
	private SqlSession sqlSession;

	public int insertOneEmailCheckToken(DuplicateVO duplicateVO) throws DataAccessException {
		return sqlSession.insert("mapper.duplicate.insertOneEmailCheckToken", duplicateVO);
	}

	public DuplicateVO selectOneRowByEmailCheckToken(String emailCheckToken) {
		return sqlSession.selectOne("mapper.duplicate.selectOneRowByEmailCheckToken", emailCheckToken);
	}
}
