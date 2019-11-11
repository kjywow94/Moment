package com.travelmaker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelmaker.dao.AccompanyMapper;
import com.travelmaker.dto.Accompany;

@Service
@Transactional
public class AccompanyService {

	@Autowired
	private AccompanyMapper accompanyMapper;

	public Accompany selectAccompanybyTid(int cid) {
		return accompanyMapper.selectAccompanybyCid(cid);
	}

	// 동행 id로 조회
	public Accompany selectAccompany(int id) {
		return accompanyMapper.selectAccompany(id);
	}

	public int insertAccompanyRegist(Accompany accompany) {
		return accompanyMapper.insertAccompany(accompany);
	}

	public int updateAccompanyRegist(Accompany accompany) {
		return accompanyMapper.updateAccompany(accompany);
	}
}
