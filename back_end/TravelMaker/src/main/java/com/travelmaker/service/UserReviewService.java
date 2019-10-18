package com.travelmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmaker.dao.UserReviewMapper;
import com.travelmaker.dto.TravelReview;
import com.travelmaker.dto.UserReview;

@Service
public class UserReviewService {
	
	@Autowired
	private UserReviewMapper userReviewMapper;

	public List<UserReview> selectAllReview(){
		return userReviewMapper.selectAllReview();
	}
	
	public List<UserReview> selectAllReviewByTid(int tid){
		return userReviewMapper.selectAllReviewByTid(tid);
	}
	
	public List<UserReview> selectAllReviewByUid(int uid){
		return userReviewMapper.selectAllReviewByUid(uid);
	}
	
	public int updateReview(UserReview tr) {
		return userReviewMapper.updateReview(tr);
	}
	
	public int deleteReviewById(int id) {
		return userReviewMapper.deleteReviewById(id);
	}
	


}
