package com.travelmaker.dao;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.Accompany;

@Mapper
public interface AccompanyMapper {
	// 동행 cid로 조회
	public Accompany selectAccompanybyCid(int cid);
	
	// 동행 id로 조회
	public Accompany selectAccompany(int id);


	// 동행 등록
	public int insertAccompany(Accompany accompany);

	// 동행 수정
	public int updateAccompany(Accompany accompany);

}
