package com.travelmaker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.User;



@Mapper
public interface UserMapper {
	//회원전체조회
	public List<User> selectAllUser();
	//로그인
	public User selectOneUser(User user);
	//회원가입
	public int insertUser(User user);
	//회원수정
	public int updateUser(User user);
	//회원탈퇴
	public int deleteUser(int id);
}