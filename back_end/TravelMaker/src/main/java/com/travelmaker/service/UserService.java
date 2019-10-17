package com.travelmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmaker.dao.UserMapper;
import com.travelmaker.dto.User;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	//회원전체조회
	public List<User> selectAllUser(){
		return userMapper.selectAllUser();
	}
	//로그인&회원조회
	public User selectOneUser(User user) {
		return userMapper.selectOneUser(user);
	}
	
	//회원가입
	public int insertUser(User user) {
		return userMapper.insertUser(user);
	}
	
	//회원수정
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}
	
	//회원탈퇴
	public int deleteUser(int id) {
		return userMapper.deleteUser(id);
	}
}
