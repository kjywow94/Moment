package com.travelmaker.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelmaker.dao.UserMapper;
import com.travelmaker.dto.User;


@Service
@Transactional
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private UserMapper userMapper;

//	private JavaMailSender sender;
//	private MimeMessage message;
//	private MimeMessageHelper messageHelper;

	// 이메일 난수 만드는 메서드
	private String init() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;

		do {
			num = ran.nextInt(75) + 48;
			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char) num);
			} else {
				continue;
			}

		} while (sb.length() < size);
		if (lowerCheck) {
			return sb.toString().toLowerCase();
		}
		return sb.toString();
	}

	// 난수를 이용한 키 생성
	private boolean lowerCheck;
	private int size;
	
	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		return init();
	}
	
	// 회원가입 발송 이메일(인증키 발송)
	public int mailSendWithUserKey(User user) {
		int result = 0;
		String key = getKey(false, 20);
		user.setCert(key);
		userMapper.updateCertKey(user); 
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>안녕하세요 보라코인입니다!</h2><br><br>" 
				+ "<h3>" + user.getNickname() + "님</h3>" + "<p>인증하기 버튼을 누르시면 로그인을 하실 수 있습니다 : " 
				+ "<a href='http://localhost:9090/api/user/cert?email="+ user.getEmail() +"&cert="+key+"'>인증하기</a></p>";
		try {
			mail.setSubject("[본인인증] 보라코인 회원가입 인증메일입니다", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
			mailSender.send(mail);
			result = 1;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return result;
	}
}