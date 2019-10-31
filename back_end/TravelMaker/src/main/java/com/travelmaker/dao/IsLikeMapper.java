package com.travelmaker.dao;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.IsLike;
import com.travelmaker.dto.ReviewPoint;

@Mapper
public interface IsLikeMapper {

	public IsLike selectIsLike(IsLike isLike);

	public int insertIsLike(IsLike isLike);

	public ReviewPoint getPoint(int rid);

	public int insertPoint(int rid);

	public int updatePoint(int rid);

}
