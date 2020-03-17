package com.codepresso.mallpresso.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codepresso.mallpresso.domain.MemberVO;
import com.codepresso.mallpresso.domain.ResponseVO;
import com.codepresso.mallpresso.repository.MemberDAO;
import com.codepresso.mallpresso.repository.TokenDAO;

@Service
public class MemberService {

	static Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	public MemberDAO memberDAO;

	@Autowired
	public TokenDAO tokenDAO;

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
