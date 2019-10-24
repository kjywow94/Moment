package com.travelmaker.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelmaker.dao.UserMapper;
import com.travelmaker.dto.User;
import com.travelmaker.dto.UserImage;

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

	//회원 이미지 등록
	public int uploadImage(UserImage image) {
		int result = 0;
		String imgData = image.getImgData();
		String imgName = image.getImgName();
		Decoder decoder = Base64.getDecoder(); 
		byte[] decoderByte = decoder.decode(imgData.split(",")[1]);
		try {
			FileOutputStream fos = new FileOutputStream("image/"+imgName);
			fos.write(decoderByte);
			fos.close();
			result = userMapper.insertUserImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//회원 이미지 수정 전 기존 이미지 삭제
	public int deleteUploadImage(UserImage image) {
		int result = 0;
		int cnt = userMapper.selectCountUserImage(image.getEmail());
		if(cnt > 0) {
			userMapper.deleteUserImage(image.getEmail());
			result = 1;
		}
		return result;
	}
	
}
