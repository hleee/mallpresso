package com.codepresso.mallpresso.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codepresso.mallpresso.domain.MemberVO;
import com.codepresso.mallpresso.domain.ResponseVO;
import com.codepresso.mallpresso.repository.MemberDAO;

@Service
public class MemberService {

	static Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	public MemberDAO memberDAO;

	@Autowired
	public MemberVO memberVO;

	@Autowired
	public ResponseVO responseVO;

	public ResponseVO insertOneMember(MemberVO memberVO) throws Exception {
		String emailEntered = memberVO.getEmail();
		List<MemberVO> memberVOList = memberDAO.selectAllMembers();
		for (int i = 0; i < memberVOList.size(); i++) {
			String email = memberVOList.get(i).getEmail();
			if (emailEntered == email) {
				logger.info("This email already exists.");
			} else {
				logger.info("This email is available.");
			}
		}
		if (memberVO.getPassword() == memberVO.getPasswordReentered()) {
			logger.info("OK.");
		} else {
			logger.info("Please confirm your password.");
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date todaysDate = new Date();
		String todaysDateFormatted = dateFormat.format(todaysDate);
		int todaysDateInt = Integer.parseInt(todaysDateFormatted);
		Date birthdayEntered = memberVO.getBirthday();
		String birthdayEnteredFormatted = dateFormat.format(birthdayEntered);
		int birthdayEnteredInt = Integer.parseInt(birthdayEnteredFormatted);
		if (todaysDateInt - birthdayEnteredInt <= 70000) {
			logger.info("You do not meet the minimum age requirement of our terms and conditions.");
		} else {
			logger.info("OK.");
		}
		memberDAO.insertOneMember(memberVO);
		memberVO = memberDAO.selectOneMemberByID(memberVO.getId());
		responseVO.setCode(HttpStatus.OK.value());
		responseVO.setMessage("Success");
		responseVO.setData(memberVO);
		return responseVO;
	}

}
