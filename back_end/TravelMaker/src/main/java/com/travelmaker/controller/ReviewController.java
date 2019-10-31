package com.travelmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travelmaker.dto.Like;
import com.travelmaker.dto.Location;
import com.travelmaker.dto.MaxLike;
import com.travelmaker.dto.ResponseLikeIt;
import com.travelmaker.dto.Review;
import com.travelmaker.dto.ReviewImage;
import com.travelmaker.dto.ReviewListDTO;
import com.travelmaker.dto.ReviewWithImage;
import com.travelmaker.service.LikeService;
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

	@Autowired
	private LikeService likeService;

	@RequestMapping(value = "/review", method = RequestMethod.POST)
	@ApiOperation(value = "위도, 경도로 distance km 이내의 리뷰 반환")
	public List<ReviewListDTO> reviewListByLocation(@RequestBody Location location) throws Exception {
		return reviewService.mainReviewList(location);
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

	@RequestMapping(value = "/reviewImg", method = RequestMethod.POST)
	@ApiOperation(value = "리뷰 이미지")
	public boolean uploadImg(@RequestBody ReviewImage reviewImage) throws Exception {
		return reviewImageService.uploadImage(reviewImage);
	}

	@RequestMapping(value = "/likeIt", method = RequestMethod.POST)
	@ApiOperation(value = "좋아요")
	public ResponseLikeIt likeIt(@RequestBody Like like) throws Exception {
		reviewService.likeIt(like.getRid());
		return likeService.like(like);
	}

	@RequestMapping(value = "/unLike", method = RequestMethod.POST)
	@ApiOperation(value = "안좋아요")
	public int unLike(@RequestBody Like like) throws Exception {
		reviewService.unLike(like.getRid());
		return likeService.unLike(like.getId());
	}

	@RequestMapping(value = "/isLike", method = RequestMethod.POST)
	@ApiOperation(value = "like 판단")
	public int isLike(@RequestBody Like like) throws Exception {
		return likeService.isLike(like);
	}
}
