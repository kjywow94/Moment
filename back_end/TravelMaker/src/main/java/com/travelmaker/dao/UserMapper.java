package com.travelmaker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.User;



@Mapper
public interface UserMapper {
	//회원 전체 조회
	public List<User> selectAllUser();
	//정상 회원 전체 조회 
	public List<User> selectNotRemovedAllUser();
	//로그인
	public User loginUser(User user);
	//회원조회
	public User selectOneUser(int id);
	//회원가입
	public int insertUser(User user);
	//회원수정/회원삭제
	public int updateUser(User user);
	//회원탈퇴(보류)
	public int deleteUser(int id);
	//아이디 찾기
	public User findId(User user);
	//비밀번호 찾기
	public User findpaswword(User user);
}
