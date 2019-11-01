package com.travelmaker.dao;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.Accompany;
import com.travelmaker.dto.Like;

@Mapper
public interface LikeItMapper {
	
	public Like selectLike(Like like);
	
	public int insertLike(Like like);
	
	public int deleteLike(int id);

}
