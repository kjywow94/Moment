package com.travelmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelmaker.dao.AccompanyPartiMapper;
import com.travelmaker.dto.AccompanyParti;

@Service
@Transactional
public class AccompanyPartiService {

	@Autowired
	AccompanyPartiMapper accompanyPartiMapper;

	public List<AccompanyParti> selectAccompanyPartiByUid(int uid) {
		return accompanyPartiMapper.selectAccompanyPartiByUid(uid);
	}
	
	public List<AccompanyParti> selectAccompanyPartiByTid(int tid) {
		return accompanyPartiMapper.selectAccompanyPartiByTid(tid);
	}

	public int updateAccompanyParti(AccompanyParti accompanyParti) {
		return accompanyPartiMapper.updateAccompanyParti(accompanyParti);
	}

	public int insertAccompanyParti(AccompanyParti accompanyParti) {
		return accompanyPartiMapper.insertAccompanyParti(accompanyParti);
	}
}
