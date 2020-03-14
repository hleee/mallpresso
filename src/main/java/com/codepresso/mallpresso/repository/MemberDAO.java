package com.codepresso.mallpresso.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.mallpresso.domain.MemberVO;

@Repository
public class MemberDAO {

	static Logger logger = LoggerFactory.getLogger(MemberDAO.class);

	@Autowired
	private SqlSession sqlSession;

	public int insertOneMember(MemberVO memberVO) throws DataAccessException {
		return sqlSession.insert("mapper.member.insertOneMember", memberVO);
	}

	public MemberVO selectOneMemberByID(long id) throws DataAccessException {
		return sqlSession.selectOne("mapper.member.selectOneMemberByID", id);
	}

	public List<MemberVO> selectAllMembers() throws DataAccessException {
		return sqlSession.selectList("mapper.member.selectAllMembers");
	}

}
