package com.travelmaker.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelmaker.dto.User;
import com.travelmaker.dto.UserImage;
import com.travelmaker.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;

	//회원 전체 조회
	@RequestMapping(value = "/userAllAdmin", method = RequestMethod.GET)
	@ApiOperation(value = "회원전체조회")
	public List<User> selectAllUser() throws Exception {
		return userService.selectAllUser();
	}
	
	//정상 회원 전체 조회
	@RequestMapping(value = "/userAll", method = RequestMethod.GET)
	@ApiOperation(value = "정상 회원 전체 조회")
	public List<User> selectNotRemovedAllUser() throws Exception {
		return userService.selectNotRemovedAllUser();
	}
	
	//로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "로그인")
	public User userSelect(@RequestBody User user) throws Exception {
		return userService.loginUser(user);
	}
	
	//회원 조회
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "회원조회")
	public User selectOneUser(@PathVariable int id) throws Exception {
		return userService.selectOneUser(id);
	}
	
	//회원가입
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ApiOperation(value = "회원가입")
	public int userInsert(@RequestBody User user) throws Exception {
		return userService.insertUser(user);
	}
	
	//회원수정/회원삭제
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	@ApiOperation(value = "회원수정/삭제")
	public int userUpdate(@RequestBody User user) throws Exception {
		return userService.updateUser(user);
	}
	
	//회원삭제
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	@ApiOperation(value = "회원삭제")
	public int userDelect(@RequestParam int id) throws Exception {
		return userService.deleteUser(id);
	}
	
	//회원 이미지 등록
	@RequestMapping(value = "/user/image", method = RequestMethod.POST)
	@ApiOperation(value = "회원 이미지 등록")
	public int uploadImage(@RequestBody UserImage image) {
		return userService.uploadImage(image);
	}
}
