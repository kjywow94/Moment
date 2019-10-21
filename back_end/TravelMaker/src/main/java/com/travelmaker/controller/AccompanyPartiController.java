package com.travelmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travelmaker.dto.AccompanyParti;
import com.travelmaker.dto.AccompanyRegist;
import com.travelmaker.service.AccompanyPartiService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class AccompanyPartiController {

	@Autowired
	private AccompanyPartiService accompanyPartiService;

	@RequestMapping(value = "/accompanyParti/{uid}", method = RequestMethod.GET)
	@ApiOperation(value = "동행 파티 리스트 by Uid")
	public List<AccompanyParti> selectAccompanyPartiByUid(@PathVariable int uid) throws Exception {
		return accompanyPartiService.selectAccompanyPartiByUid(uid);
	}
	
	@RequestMapping(value = "/accompanyPartiByTid/{tid}", method = RequestMethod.GET)
	@ApiOperation(value = "동행 파티 리스트 by Tid")
	public List<AccompanyParti> selectAccompanyPartiByTid(@PathVariable int tid) throws Exception {
		return accompanyPartiService.selectAccompanyPartiByTid(tid);
	}

	@RequestMapping(value = "/accompanyParti", method = RequestMethod.POST)
	@ApiOperation(value = "동행 파티 추가")
	public int insertAccompanyParti(@RequestBody AccompanyParti accompanyParti) throws Exception {
		return accompanyPartiService.insertAccompanyParti(accompanyParti);
	}

	@RequestMapping(value = "/accompanyParti", method = RequestMethod.PUT)
	@ApiOperation(value = "동행 파티 수정")
	public int accompanyRegist(@RequestBody AccompanyParti accompanyParti) {
		return accompanyPartiService.updateAccompanyParti(accompanyParti);
	}
}
