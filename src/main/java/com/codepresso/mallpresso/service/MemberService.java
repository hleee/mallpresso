package com.codepresso.mallpresso.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codepresso.mallpresso.domain.EmailCheckTokenVO;
import com.codepresso.mallpresso.domain.LogInTokenVO;
import com.codepresso.mallpresso.domain.MemberVO;
import com.codepresso.mallpresso.domain.ResponseVO;
import com.codepresso.mallpresso.repository.MemberDAO;
import com.codepresso.mallpresso.repository.TokenDAO;
import com.codepresso.mallpresso.util.TokenMaker;

@Service
public class MemberService {

	static Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	public MemberDAO memberDAO;

	@Autowired
	public TokenDAO tokenDAO;

	// 이메일 중복 확인
	public ResponseVO insertOneEmailCheckToken(MemberVO emailOnlyVO) throws Exception {
		ResponseVO responseVO = new ResponseVO();
		EmailCheckTokenVO emailCheckTokenVO = new EmailCheckTokenVO();
		MemberVO memberVO = new MemberVO();
		String email = emailOnlyVO.getEmail();
		memberVO = memberDAO.selectOneMemberByEmail(email);
		if (memberVO == null) {
			String emailCheckToken = TokenMaker.makeToken();
			emailCheckTokenVO.setEmail(email);
			emailCheckTokenVO.setEmailCheckToken(emailCheckToken);
			tokenDAO.insertOneEmailCheckToken(emailCheckTokenVO);
			emailCheckTokenVO = tokenDAO.selectOneRowByEmailCheckToken(emailCheckToken);
			responseVO.setCode(HttpStatus.OK.value());
			responseVO.setMessage("Success");
			responseVO.setData(emailCheckTokenVO);
		} else {
			responseVO.setCode(HttpStatus.BAD_REQUEST.value());
			responseVO.setMessage("Failure");
			responseVO.setData(emailCheckTokenVO);
		}
		return responseVO;
	}

	// 로그인
	public ResponseVO insertOneLogInToken(MemberVO memberVO) throws Exception {
		LogInTokenVO logInTokenVO = new LogInTokenVO();
		ResponseVO responseVO = new ResponseVO();
		MemberVO memberVOInDB = memberDAO.selectOneMemberByEmailAndPassword(memberVO);
		if (memberVOInDB == null) {
			logger.info("ID-password pair not found.");
			responseVO.setCode(HttpStatus.BAD_REQUEST.value());
			responseVO.setMessage("Failure");
			responseVO.setData(logInTokenVO);
			return responseVO;
		} else {
			String email = memberVO.getEmail();
			logInTokenVO.setEmail(email);
			String logInToken = TokenMaker.makeToken();
			logInTokenVO.setLogInToken(logInToken);
			long memberID = memberVOInDB.getId();
			logger.info("memberID: " + memberID);
			logInTokenVO.setMemberID(memberID);
			tokenDAO.insertOneLogInToken(logInTokenVO);
			logInTokenVO = tokenDAO.selectOneRowByLogInToken(logInToken);
			logger.info("logintoke: " + logInTokenVO);
			responseVO.setCode(HttpStatus.OK.value());
			responseVO.setMessage("Success");
			responseVO.setData(logInTokenVO);
			return responseVO;
		}
	}

	// 회원 가입
	public ResponseVO insertOneMember(String emailCheckToken, MemberVO memberVO) throws Exception {
		ResponseVO responseVO = new ResponseVO();
		MemberVO emptyMemberVO = new MemberVO();
		if (emailCheckToken == null) {
			logger.info("Check for email duplication.");
			responseVO.setCode(HttpStatus.BAD_REQUEST.value());
			responseVO.setMessage("Failure");
			responseVO.setData(emptyMemberVO);
			return responseVO;
		} else {
			logger.info("Email OK.");
		}
		if (memberVO.getPassword().equals(memberVO.getPasswordReentered())) {
			logger.info("Password OK.");
		} else {
			logger.info("Password doesn't match.");
			responseVO.setCode(HttpStatus.BAD_REQUEST.value());
			responseVO.setMessage("Failure");
			responseVO.setData(emptyMemberVO);
			return responseVO;
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date todaysDate = new Date();
		String todaysDateFormatted = dateFormat.format(todaysDate);
		int todaysDateInt = Integer.parseInt(todaysDateFormatted);
		String birthdayEntered = memberVO.getBirthday().replaceAll("\\p{Punct}", "");
		int birthdayEnteredInt = Integer.parseInt(birthdayEntered);
		if (todaysDateInt - birthdayEnteredInt < 80000) {
			logger.info("Underage.");
			responseVO.setCode(HttpStatus.BAD_REQUEST.value());
			responseVO.setMessage("Failure");
			responseVO.setData(emptyMemberVO);
			return responseVO;
		} else {
			logger.info("Age OK.");
		}
		memberDAO.insertOneMember(memberVO);
		memberVO = memberDAO.selectOneMemberByID(memberVO.getId());
		responseVO.setCode(HttpStatus.OK.value());
		responseVO.setMessage("Success");
		responseVO.setData(memberVO);
		return responseVO;
	}
}
