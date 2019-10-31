package com.travelmaker.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmaker.dao.IsLikeMapper;
import com.travelmaker.dao.LikeItMapper;
import com.travelmaker.dao.MaxLikeMapper;
import com.travelmaker.dto.IsLike;
import com.travelmaker.dto.Like;
import com.travelmaker.dto.MaxLike;
import com.travelmaker.dto.ResponseLikeIt;

@Service
public class LikeService {

	@Autowired
	LikeItMapper likeMapper;

	@Autowired
	MaxLikeMapper maxLikeMapper;

	@Autowired
	IsLikeMapper isLikeMapper;

	public int isLike(Like like) {
		Like selected = likeMapper.selectLike(like);
		if (selected == null) {
			return 0;
		} else {
			return selected.getId();
		}
	}

	public ResponseLikeIt like(Like like) {
		boolean isPoint = false;
		likeMapper.insertLike(like);
		IsLike curIsLike = new IsLike(0, like.getUid(), like.getRid());
		int max = maxLikeMapper.getMaxId(like.getRid()).getMax();
		if (isLikeMapper.selectIsLike(curIsLike) == null) {
			isLikeMapper.insertIsLike(curIsLike);
			MaxLike maxLike = new MaxLike(0, 0, like.getRid(), 0);
			maxLikeMapper.upMaxLike(maxLike);
			max += 1;
			int point = isLikeMapper.getPoint(like.getRid()).getPoint();
			if (max == point) {
				isLikeMapper.updatePoint(like.getRid());
				isPoint = true;
			}

		}
		ResponseLikeIt rli = new ResponseLikeIt(max, like.getId(), isPoint);
		return rli;
	}

	public int unLike(int id) {
		return likeMapper.deleteLike(id);
	}

}
