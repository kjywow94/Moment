package com.travelmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelmaker.dao.AccompanyRegistMapper;
import com.travelmaker.dto.AccompanyRegist;

@Service
@Transactional
public class AccompanyRegistService {

	@Autowired
	private AccompanyRegistMapper accompanyRegistMapper;

	public List<AccompanyRegist> selectAllAccompanyRegistNotRemoved() {
		return accompanyRegistMapper.selectAllAccompanyRegistNotRemoved();
	}

	public List<AccompanyRegist> selectAllAccompanyRegist() {
		return accompanyRegistMapper.selectAllAccompanyRegist();
	}

	public AccompanyRegist selectAccompanyRegistByID(int id) {
		return accompanyRegistMapper.selectAccompanyRegistByID(id);
	}

	public int insertAccompanyRegist(AccompanyRegist accompanyRegist) {
		return accompanyRegistMapper.insertAccompanyRegist(accompanyRegist);
	}

	public int updateAccompanyRegist(AccompanyRegist accompanyRegist) {
		return accompanyRegistMapper.updateAccompanyRegist(accompanyRegist);
	}

	public int deleteAccompanyRegist(int id) {
		return accompanyRegistMapper.deleteAccompanyRegist(id);
	}

	public int successAccompanyRegist(int id) {
		return accompanyRegistMapper.successAccompanyRegist(id);
	}
}
