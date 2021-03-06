package com.travelmaker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.TravelReview;



@Mapper
public interface TravelReviewMapper {
	public List<TravelReview> selectAllReview();
	public List<TravelReview> selectAllReviewByTid(int tid);
	public int updateReview(TravelReview tr);
	public int deleteReviewById(int id);
	public int insertReview(TravelReview tr);
	public List<TravelReview> selectAllNotRemovedReview();
	public TravelReview selectReview(int id);
}
