package com.travelmaker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.TravelReview;
import com.travelmaker.dto.UserReview;



@Mapper
public interface UserReviewMapper {
	public List<UserReview> selectAllReview();
	public List<UserReview> selectAllReviewByTid(int tid);
	public List<UserReview> selectAllReviewByUid(int uid);
	public int updateReview(UserReview ur);
	public int deleteReviewById(int id);
	public int insertReview(UserReview ur);
	public List<UserReview> selectAllNotRemovedReview();

}
