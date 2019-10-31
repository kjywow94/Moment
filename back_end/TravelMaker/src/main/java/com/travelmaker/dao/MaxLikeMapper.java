package com.travelmaker.dao;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.Accompany;
import com.travelmaker.dto.MaxLike;

@Mapper
public interface MaxLikeMapper {

	public MaxLike getMaxId(int rid);
	
	public int upMaxLike(MaxLike maxLike);

	public int insertMaxLike(MaxLike maxLike);

}
