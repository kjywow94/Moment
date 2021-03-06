package com.travelmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travelmaker.dto.TravelReview;
import com.travelmaker.dto.UserReview;
import com.travelmaker.service.UserReviewService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class UserReviewController {
	
	@Autowired
	private UserReviewService userReviewService;

	@RequestMapping(value = "/userReviewAdmin", method = RequestMethod.GET)
	@ApiOperation(value="모든 유저 리뷰 리턴")
	public List<UserReview> selectAllReview() throws Exception {
		return userReviewService.selectAllReview();
	}	
	@RequestMapping(value = "/userReview", method = RequestMethod.GET)
	@ApiOperation(value="삭제되지 않은 여행 리뷰 리턴")
	public List<UserReview> selectNotRemovedReview() throws Exception {
		return userReviewService.selectNotRemovedReview();
	}
	
	@RequestMapping(value = "/userReviewTid/{tid}", method = RequestMethod.GET)
	@ApiOperation(value="동행에 해당하는 유저 리뷰 리턴")
	public List<UserReview> selectAllReviewByTid(@PathVariable int tid) throws Exception {
		return userReviewService.selectAllReviewByTid(tid);
	}
	
	@RequestMapping(value = "/userReviewUid/{uid}", method = RequestMethod.GET)
	@ApiOperation(value="유저의 리뷰 리턴")
	public List<UserReview> selectAllReviewByUid(@PathVariable int uid) throws Exception {
		return userReviewService.selectAllReviewByUid(uid);
	}
	
	@RequestMapping(value ="/userReview", method = RequestMethod.PUT)
	@ApiOperation(value="유저 리뷰 수정")
	public int updateReview(@RequestBody UserReview tr) {
		return userReviewService.updateReview(tr);
	}
	
	@RequestMapping(value = "/userReview/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value="유저 리뷰 삭제")
	public int deleteReviewById(@PathVariable int id) {
		return userReviewService.deleteReviewById(id);
	}
	
	@RequestMapping(value = "/userReview", method = RequestMethod.POST)
	@ApiOperation(value="유저 리뷰 생성")
	public int insertReviewById(@RequestBody UserReview ur) {
		return userReviewService.insertReview(ur);
	}

}
