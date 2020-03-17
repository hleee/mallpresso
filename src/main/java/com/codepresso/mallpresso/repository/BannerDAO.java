package com.codepresso.mallpresso.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.mallpresso.domain.BannerVO;

@Repository
public class BannerDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<BannerVO> selectFiveLatestBanners() throws DataAccessException {
		return sqlSession.selectList("mapper.banner.selectFiveLatestBanners");
	}
}
