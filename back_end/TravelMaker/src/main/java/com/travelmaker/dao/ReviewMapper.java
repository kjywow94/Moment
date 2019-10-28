package com.travelmaker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.Review;
import com.travelmaker.dto.Location;

@Mapper
public interface ReviewMapper {

	/**
	 * 위도와 경도로 가까운 리뷰 반환
	 * 
	 * @param Location 위도 경도
	 * @return List<Review> 가까운 5km 이내 리뷰를 반환
	 */
	public List<Review> reviewListByLocation(Location location);

	/**
	 * 리뷰 리스트
	 * 
	 * @return List<Review>
	 */
	public List<Review> reviewList();

	/**
	 * id로 조회
	 * 
	 * @param id
	 * @return Review
	 */
	public Review selectReviewById(int id);

	/**
	 * uid로 조회
	 * 
	 * @param accompany
	 * @return List<Review>
	 */
	public List<Review> selectReviewByUid(int uid);

	/**
	 * 리뷰 입력, 성공시 1, 실패시 0 반환
	 * 
	 * @param Review
	 * @return int 1 / 0
	 */
	public int insertReview(Review review);

}
