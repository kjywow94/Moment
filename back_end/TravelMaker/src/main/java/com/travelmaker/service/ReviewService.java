package com.travelmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmaker.dao.ReviewMapper;
import com.travelmaker.dto.Location;
import com.travelmaker.dto.Review;

@Service
public class ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;

	/**
	 * 위도와 경도로 5km이내의 리뷰 리스트 리턴
	 * 
	 * @param location (위도, 경도)
	 * @return List<Review>
	 */
	public List<Review> reviewListByLocation(Location location) {
		return reviewMapper.reviewListByLocation(location);
	}

	/**
	 * 전체 리뷰리스트 반환
	 * 
	 * @return List<Review>
	 */
	public List<Review> reviewList() {
		return reviewMapper.reviewList();
	}

	/**
	 * 리뷰 id로 리뷰 리스트 검색후 반환
	 * 
	 * @param id 리뷰 테이블의 id
	 * @return Review
	 */
	public Review selectReviewById(int id) {
		return reviewMapper.selectReviewById(id);
	}

	/**
	 * 유저 아이디로 리뷰 리스트 검색 후 반환
	 * 
	 * @param uid 유저 아이디
	 * @return List<Review>
	 */
	public List<Review> selectReviewByUid(int uid) {
		return reviewMapper.selectReviewByUid(uid);
	}

	/**
	 * 리뷰 입력, 성공시 1, 실패시 0 반환
	 * 
	 * @param Review
	 * @return int 1 / 0
	 */
	public int insertReview(Review review) {
		return reviewMapper.insertReview(review);
	}
}
