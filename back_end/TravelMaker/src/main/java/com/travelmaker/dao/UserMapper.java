package com.travelmaker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.User;
import com.travelmaker.dto.UserImage;



@Mapper
public interface UserMapper {
	//회원 전체 조회
	public List<User> selectAllUser();
	//정상 회원 전체 조회 
	public List<User> selectNotRemovedAllUser();
	//로그인
	public User loginUser(User user);
	//회원조회-아이디
	public User selectOneUser(int id);
	//회원조회-이메일
	public User selectOneUserByEmail(String email);
	//회원가입
	public int insertUser(User user);
	//회원수정/회원삭제
	public int updateUser(User user);
	//회원탈퇴(보류)
	public int deleteUser(int id);
	//아이디 찾기
	public User findId(User user);
	//비밀번호 찾기
	public User findPass(User user);
	//회원 사진 카운트 조회
	public int selectCountUserImage(String email);
	//회원 사진 삭제
	public int deleteUserImage(String email);
	//회원 사진 등록
	public int insertUserImage(UserImage userImage);
	//회원 사진 조회
	public List<UserImage> selectUserImage(String email);
	//회원 인증키 난수 값 생성 
	public void updateCertKey(User user);
	//회원 인증키(난수) Y로 변경
	public void updateCert(User user);
}
