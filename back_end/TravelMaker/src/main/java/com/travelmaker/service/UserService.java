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

	public List<User> selectAllUser(){
		return userMapper.selectAllUser();
	}
	
//	public UserDto searchOnceUser(String eamil) {
//		return userMapper.searchOnceUser(eamil);
//	}
//	
//	public int deleteUser(String email) {
//		return userMapper.deleteUser(email);
//	}
//	
//	public int updateUser(UserDto user) {
//		return userMapper.updateUser(user);
//	}
//	
//	public int insertUser(UserDto user) {
//		return userMapper.insertUser(user);
//	}
//	public int countUserAdmin() {
//		return userMapper.countUserAdmin();
//	}

}
