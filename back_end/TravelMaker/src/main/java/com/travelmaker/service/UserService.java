package com.travelmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelmaker.dao.UserMapper;
import com.travelmaker.dto.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	//회원 전체 조회
	public List<User> selectAllUser(){
		return userMapper.selectAllUser();
	}
	
	//정상 회원 전체 조회
	public List<User> selectNotRemovedAllUser(){
		return userMapper.selectNotRemovedAllUser();
	}
	
	//로그인
	public User loginUser(User user) {
		return userMapper.loginUser(user);
	}
	
	//회원조회
	public User selectOneUser(int id) {
		return userMapper.selectOneUser(id);
	}
	
	//회원가입
	public int insertUser(User user) {
		try {
			return userMapper.insertUser(user);
		} catch(DuplicateKeyException e) {
			System.out.println("Duplicate error : "+e);
			return 0;
		}
	}
	
	//회원수정/회원삭제
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}
	
	//회원탈퇴(보류)
	public int deleteUser(int id) {
		return userMapper.deleteUser(id);
	}

}
