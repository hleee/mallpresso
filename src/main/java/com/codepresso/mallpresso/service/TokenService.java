package com.codepresso.mallpresso.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codepresso.mallpresso.domain.DuplicateVO;
import com.codepresso.mallpresso.domain.MemberVO;
import com.codepresso.mallpresso.domain.ResponseVO;
import com.codepresso.mallpresso.domain.TokenVO;
import com.codepresso.mallpresso.repository.DuplicateDAO;
import com.codepresso.mallpresso.repository.MemberDAO;
import com.codepresso.mallpresso.repository.TokenDAO;
import com.codepresso.mallpresso.util.TokenMaker;

@Service
public class TokenService {

	static Logger logger = LoggerFactory.getLogger(TokenService.class);

	@Autowired
	public TokenDAO tokenDAO;

	@Autowired
	public DuplicateDAO duplicateDAO;

	@Autowired
	public MemberDAO memberDAO;

	// 이메일 중복 확인 토큰
	public ResponseVO insertOneEmailCheckToken(MemberVO emailOnlyVO) throws Exception {
		ResponseVO responseVO = new ResponseVO();
		DuplicateVO duplicateVO = new DuplicateVO();
		MemberVO memberVO = new MemberVO();
		String emailEntered = emailOnlyVO.getEmail();
		memberVO = memberDAO.selectOneMemberByEmail(emailEntered);
		if (memberVO == null) {
			String emailCheckToken = TokenMaker.makeToken();
			duplicateVO.setEmail(emailEntered);
			duplicateVO.setEmailCheckToken(emailCheckToken);
			duplicateDAO.insertOneEmailCheckToken(duplicateVO);
			duplicateVO = duplicateDAO.selectOneRowByEmailCheckToken(emailCheckToken);
			responseVO.setCode(HttpStatus.OK.value());
			responseVO.setMessage("Success");
			responseVO.setData(duplicateVO);
		} else {
			responseVO.setCode(HttpStatus.BAD_REQUEST.value());
			responseVO.setMessage("Failure");
			responseVO.setData(duplicateVO);
		}
		return responseVO;
	}

	// 로그인 토큰
	public ResponseVO insertOneLogInToken(MemberVO memberVO) throws Exception {
		TokenVO tokenVO = new TokenVO();
		ResponseVO responseVO = new ResponseVO();
		MemberVO memberVOInDB = memberDAO.selectOneMemberByEmailAndPassword(memberVO);
		if (memberVOInDB == null) {
			logger.info("ID-password pair not found.");
			responseVO.setCode(HttpStatus.BAD_REQUEST.value());
			responseVO.setMessage("Failure");
			responseVO.setData(tokenVO);
			return responseVO;
		} else {
			tokenVO.setEmail(memberVO.getEmail());
			String logInToken = TokenMaker.makeToken();
			tokenVO.setLogInToken(logInToken);
			tokenDAO.insertOneLogInToken(tokenVO);
			tokenVO = tokenDAO.selectOneRowByLogInToken(logInToken);
			responseVO.setCode(HttpStatus.OK.value());
			responseVO.setMessage("Success");
			responseVO.setData(tokenVO);
			return responseVO;
		}
	}
}
