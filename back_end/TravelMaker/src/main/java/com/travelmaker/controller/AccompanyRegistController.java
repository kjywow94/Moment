package com.travelmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travelmaker.dto.AccompanyRegist;
import com.travelmaker.service.AccompanyRegistService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class AccompanyRegistController {

	@Autowired
	private AccompanyRegistService accompanyRegistService;

	@RequestMapping(value = "/accompanyRegist", method = RequestMethod.GET)
	@ApiOperation(value = "동행 등록 리스트")
	public List<AccompanyRegist> selectAllAccompanyRegistNotRemoved() throws Exception {
		return accompanyRegistService.selectAllAccompanyRegistNotRemoved();
	}
	
	@RequestMapping(value = "/accompanyRegistAdmin", method = RequestMethod.GET)
	@ApiOperation(value = "동행 등록 리스트 관리자")
	public List<AccompanyRegist> selectAllAccompanyRegist() throws Exception {
		return accompanyRegistService.selectAllAccompanyRegist();
	}

	@RequestMapping(value = "/accompanyRegist/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "동행 등록 하나 선택")
	public AccompanyRegist selectByid(@PathVariable int id) throws Exception {
		return accompanyRegistService.selectAccompanyRegistByID(id);
	}

	@RequestMapping(value = "/accompanyRegist", method = RequestMethod.POST)
	@ApiOperation(value = "동행 등록 추가")
	public int accompanyRegistInsert(@RequestBody AccompanyRegist accompanyRegist) throws Exception {
		return accompanyRegistService.insertAccompanyRegist(accompanyRegist);
	}

	@RequestMapping(value = "/accompanyRegist", method = RequestMethod.PUT)
	@ApiOperation(value = "동행 등록 수정")
	public int accompanyRegist(@RequestBody AccompanyRegist accompanyRegist) {
		return accompanyRegistService.updateAccompanyRegist(accompanyRegist);
	}
}
