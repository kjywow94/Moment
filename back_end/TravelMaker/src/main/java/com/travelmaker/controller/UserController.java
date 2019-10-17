package com.travelmaker.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelmaker.dto.User;
import com.travelmaker.service.UserService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;

	//회원전체조회
	@RequestMapping(value = "/userAll", method = RequestMethod.GET)
	public List<User> userAll() throws Exception {
		return userService.selectAllUser();
	}
	
	//로그인&회원조회
	@RequestMapping(value = "/userSelect", method = RequestMethod.GET)
		public User userSelect(@RequestBody User user) throws Exception {
		return userService.selectOneUser(user);
	}
	
	//회원가입
	@RequestMapping(value = "/userInsert", method = RequestMethod.POST)
	public HashMap<String, Object> userInsert(@RequestBody User user) throws Exception {
		int res = userService.insertUser(user);
		HashMap<String, Object> result = new HashMap<>();
		if (res > 0) {
			result.put("state", "1");
		} else {
			result.put("state", "-1");
		}
		return result;
	}
	
	//회원수정
	@RequestMapping(value = "/userUpdate", method = RequestMethod.PUT)
	public HashMap<String, Object> userUpdate(@RequestBody User user) throws Exception {
		int res = userService.updateUser(user);
		HashMap<String, Object> result = new HashMap<>();
		if (res > 0) {
			result.put("state", "1");
		} else {
			result.put("state", "-1");
		}
		return result;
	}
	
	//회원탈퇴
	@RequestMapping(value = "/userDelete", method = RequestMethod.DELETE)
	public HashMap<String, Object> userDelect(@RequestParam int id) throws Exception {
		int res = userService.deleteUser(id);
		HashMap<String, Object> result = new HashMap<>();
		if (res > 0) {
			result.put("state", "1");
		} else {
			result.put("state", "-1");
		}
		return result;
	}
}
