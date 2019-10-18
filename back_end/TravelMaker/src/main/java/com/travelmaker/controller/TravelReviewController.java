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
import com.travelmaker.service.TravelReviewService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class TravelReviewController {
	
	@Autowired
	private TravelReviewService travelReviewService;

	@RequestMapping(value = "/travelReviewAll", method = RequestMethod.GET)
	@ApiOperation(value="모든 여행 리뷰 리턴")
	public List<TravelReview> selectAllReview() throws Exception {
		return travelReviewService.selectAllReview();
	}
	
	@RequestMapping(value = "/travelReviewAllByTid/{tid}", method = RequestMethod.GET)
	@ApiOperation(value="동행에 해당하는 리뷰 리턴")
	public List<TravelReview> selectAllReviewByTid(@PathVariable int tid) throws Exception {
		return travelReviewService.selectAllReviewByTid(tid);
	}
	
	@RequestMapping(value ="/updateReview", method = RequestMethod.PUT)
	@ApiOperation(value="여행 리뷰 수정")
	public int updateReview(@RequestBody TravelReview tr) {
		return travelReviewService.updateReview(tr);
	}
	
	@RequestMapping(value = "/deleteReview/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value="여행 리뷰 삭제")
	public int deleteReviewById(@PathVariable int id) {
		return travelReviewService.deleteReviewById(id);
	}
//	@RequestMapping(value = "/userSelect", method = RequestMethod.GET)
//	public UserDto userSelect(@RequestParam String email) throws Exception {
//		return userService.searchOnceUser(email);
//	}
//
//	@RequestMapping(value = "/userDelete", method = RequestMethod.DELETE)
//	public HashMap<String, Object> userDelect(@RequestParam String email) throws Exception {
//		int res = userService.deleteUser(email);
//		HashMap<String, Object> result = new HashMap<>();
//		if (res > 0) {
//			result.put("state", "1");
//		} else {
//			result.put("state", "-1");
//		}
//		return result;
//	}
//
//	@RequestMapping(value = "/userUpdate", method = RequestMethod.PUT)
//	public HashMap<String, Object> userUpdate(@RequestBody UserDto user) throws Exception {
//		String user_authority = userService.searchOnceUser(user.getEmail()).getAuthority();
//		int res = -1;
//		HashMap<String, Object> result = new HashMap<>();
//		result.put("state", "-1");
//		if (user_authority.equals("admin")) {
//			int admin_num = userService.countUserAdmin();
//			if (admin_num == 1) {
//				return result;
//			}
//		}
//		user.setEmail(user.getEmail());
//		user.setAuthority(user.getAuthority());
//		res = userService.updateUser(user);
//		if (res > 0) {
//			result.put("state", "1");
//		}
//		return result;
//
//	}
//
//	@RequestMapping(value = "/userInsert", method = RequestMethod.POST)
//	public HashMap<String, Object> userInsert(@RequestBody UserDto user) throws Exception {
//		int res = userService.insertUser(user);
//		HashMap<String, Object> result = new HashMap<>();
//		if (res > 0) {
//			result.put("state", "1");
//		} else {
//			result.put("state", "-1");
//		}
//		return result;
//	}

}
