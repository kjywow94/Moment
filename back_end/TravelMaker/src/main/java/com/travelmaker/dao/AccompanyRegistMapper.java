package com.travelmaker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.AccompanyRegist;

@Mapper
public interface AccompanyRegistMapper {
	// 동행 모집 목록 조회
	public List<AccompanyRegist> selectAllAccompanyRegist();
	
	public List<AccompanyRegist> selectAllAccompanyRegistNotRemoved();

	// 동행 모집 선택
	public AccompanyRegist selectAccompanyRegistByID(int id);

	// 동행 모집 등록
	public int insertAccompanyRegist(AccompanyRegist accompanyRegist);

	// 동행 모집 수정
	public int updateAccompanyRegist(AccompanyRegist accompanyRegist);

	// 동행 모집 삭제
	public int deleteAccompanyRegist(int id);
	
	// 동행 모집 완료로 변경
	public int successAccompanyRegist(int id);
}
