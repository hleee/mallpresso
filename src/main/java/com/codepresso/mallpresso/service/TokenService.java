package com.codepresso.mallpresso.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codepresso.mallpresso.domain.MemberVO;
import com.codepresso.mallpresso.domain.ResponseVO;
import com.codepresso.mallpresso.domain.TokenVO;
import com.codepresso.mallpresso.repository.MemberDAO;
import com.codepresso.mallpresso.repository.TokenDAO;
import com.codepresso.mallpresso.util.TokenMaker;

@Service
public class TokenService {

	static Logger logger = LoggerFactory.getLogger(TokenService.class);

	@Autowired
	public TokenDAO tokenDAO;

	@Autowired
	public TokenVO tokenVO;

	@Autowired
	public MemberDAO memberDAO;

	@Autowired
	public MemberVO memberVO;

	@Autowired
	public ResponseVO responseVO;

	public ResponseVO insertOneToken(MemberVO memberVO) throws Exception {
		tokenVO.setEmail(memberVO.getEmail());
		String token = TokenMaker.makeToken();
		tokenVO.setToken(token);
		tokenDAO.insertOneToken(tokenVO);
		tokenVO = tokenDAO.selectOneTokenRowByToken(token);
		responseVO.setCode(HttpStatus.OK.value());
		responseVO.setMessage("Success");
		responseVO.setData(tokenVO);
		return responseVO;
	}

	public TokenVO selectOneTokenRowByToken(String token) {
		return tokenDAO.selectOneTokenRowByToken(token);
	}

}
