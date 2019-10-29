package com.travelmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travelmaker.dto.Location;
import com.travelmaker.dto.Review;
import com.travelmaker.dto.ReviewImage;
import com.travelmaker.dto.ReviewWithDistanceImg;
import com.travelmaker.dto.ReviewWithImage;
import com.travelmaker.service.ReviewImageService;
import com.travelmaker.service.ReviewService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewImageService reviewImageService;

	@RequestMapping(value = "/review", method = RequestMethod.POST)
	@ApiOperation(value = "위도, 경도로 5km 이내의 리뷰 반환")
	public List<ReviewWithDistanceImg> reviewListByLocation(@RequestBody Location location) throws Exception {
		return reviewService.reviewListByLocation(location);
	}

	@RequestMapping(value = "/review", method = RequestMethod.GET)
	@ApiOperation(value = "리뷰 목록 전체 반환")
	public List<ReviewWithImage> reviewList() throws Exception {
		return reviewService.reviewList();
	}

	@RequestMapping(value = "/review/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "리뷰 아이디로 리뷰 반환")
	public Review selectReviewById(@PathVariable int id) throws Exception {
		return reviewService.selectReviewById(id);
	}

	@RequestMapping(value = "/reviewByUser/{uid}", method = RequestMethod.GET)
	@ApiOperation(value = "유저 아이디로 리뷰리스트 반환")
	public List<ReviewWithImage> selectReviewByUid(@PathVariable int uid) throws Exception {
		return reviewService.selectReviewByUid(uid);
	}

	@RequestMapping(value = "/writeReview", method = RequestMethod.POST)
	@ApiOperation(value = "리뷰 등록")
	public int insertReview(@RequestBody Review review) throws Exception {
		return reviewService.insertReview(review);
	}

	@RequestMapping(value = "/likeIt/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "좋아요")
	public int likeIt(@PathVariable int id) throws Exception {
		return reviewService.likeIt(id);
	}

	@RequestMapping(value = "/reviewImg", method = RequestMethod.POST)
	@ApiOperation(value = "리뷰 이미지")
	public boolean uploadImg(@RequestBody ReviewImage reviewImage) throws Exception {
		return reviewImageService.uploadImage(reviewImage);
	}

}