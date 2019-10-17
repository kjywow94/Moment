package com.travelmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travelmaker.dto.UserReview;
import com.travelmaker.service.UserReviewService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class UserReviewController {
	
	@Autowired
	private UserReviewService userReviewService;

	@RequestMapping(value = "/userReviewAll", method = RequestMethod.GET)
	@ApiOperation(value="모든 유저 리뷰 리턴")
	public List<UserReview> selectAllReview() throws Exception {
		return userReviewService.selectAllReview();
	}
	
	@RequestMapping(value = "/userReviewAllByTid/{tid}", method = RequestMethod.GET)
	@ApiOperation(value="동행에 해당하는 유저 리뷰 리턴")
	public List<UserReview> selectAllReviewByTid(@PathVariable int tid) throws Exception {
		return userReviewService.selectAllReviewByTid(tid);
	}
	
	@RequestMapping(value = "/userReviewAllByUid/{uid}", method = RequestMethod.GET)
	@ApiOperation(value="유저의 리뷰 리턴")
	public List<UserReview> selectAllReviewByUid(@PathVariable int uid) throws Exception {
		return userReviewService.selectAllReviewByTid(uid);
	}
	
	@RequestMapping(value ="/updateUserReview", method = RequestMethod.PUT)
	@ApiOperation(value="유저 리뷰 수정")
	public int updateReview(@RequestBody UserReview tr) {
		return userReviewService.updateReview(tr);
	}
	
	@RequestMapping(value = "/deleteUserReview/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value="유저 리뷰 삭제")
	public int deleteReviewById(@PathVariable int id) {
		return userReviewService.deleteReviewById(id);
	}

}