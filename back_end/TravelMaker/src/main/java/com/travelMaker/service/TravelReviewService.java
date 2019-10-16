package com.travelmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmaker.dao.TravelReviewMapper;
import com.travelmaker.dao.UserMapper;
import com.travelmaker.dto.TravelReview;
import com.travelmaker.dto.User;

@Service
public class TravelReviewService {
	
	@Autowired
	private TravelReviewMapper travelReviewMapper;

	public List<TravelReview> selectAllReview(){
		return travelReviewMapper.selectAllReview();
	}
	
//	public UserDto searchOnceUser(String eamil) {
//		return userMapper.searchOnceUser(eamil);
//	}
//	
//	public int deleteUser(String email) {
//		return userMapper.deleteUser(email);
//	}
//	
//	public int updateUser(UserDto user) {
//		return userMapper.updateUser(user);
//	}
//	
//	public int insertUser(UserDto user) {
//		return userMapper.insertUser(user);
//	}
//	public int countUserAdmin() {
//		return userMapper.countUserAdmin();
//	}

}
