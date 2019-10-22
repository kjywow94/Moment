package com.travelmaker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.AccompanyParti;

@Mapper
public interface AccompanyPartiMapper {
	public List<AccompanyParti> selectAccompanyPartiByUid(int uid);
	
	public List<AccompanyParti> selectAccompanyPartiByTid(int tid);
	
	public int updateAccompanyParti(AccompanyParti accompanyParti);
	
	public int insertAccompanyParti(AccompanyParti accompanyParti);
}
