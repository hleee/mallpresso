package com.codepresso.mallpresso.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.mallpresso.domain.MemberVO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSession sqlSession;

	public int insertOneMember(MemberVO memberVO) throws DataAccessException {
		return sqlSession.insert("mapper.member.insertOneMember", memberVO);
	}

	public MemberVO selectOneMemberByID(long id) throws DataAccessException {
		return sqlSession.selectOne("mapper.member.selectOneMemberByID", id);
	}

	public MemberVO selectOneMemberByEmailAndPassword(MemberVO memberVO) throws DataAccessException {
		return sqlSession.selectOne("mapper.member.selectOneMemberByEmailAndPassword", memberVO);
	}
	
	public MemberVO selectOneMemberByEmail(String email) throws DataAccessException {
		return sqlSession.selectOne("mapper.member.selectOneMemberByEmail", email);
	}

	public List<MemberVO> selectAllMembers() throws DataAccessException {
		return sqlSession.selectList("mapper.member.selectAllMembers");
	}
}
