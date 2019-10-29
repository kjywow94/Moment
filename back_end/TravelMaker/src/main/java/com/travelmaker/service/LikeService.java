package com.travelmaker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmaker.dao.LikeMapper;
import com.travelmaker.dto.Like;

@Service
public class LikeService {

	@Autowired
	LikeMapper likeMapper;

	public int isLike(Like like) {
		Like selected = likeMapper.selectLike(like);
		if (selected == null) {
			return 0;
		} else {
			return selected.getId();
		}
	}

	public int like(Like like) {
		likeMapper.insertLike(like);
		return like.getId();
	}

	public int unLike(int id) {
		return likeMapper.deleteLike(id);
	}

}
