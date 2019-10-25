package com.travelmaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travelmaker.dto.Accompany;
import com.travelmaker.service.AccompanyService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class AccompanyController {

	@Autowired
	private AccompanyService accompanyService;

	@RequestMapping(value = "/accompany/{cid}", method = RequestMethod.GET)
	@ApiOperation(value = "동행 리스트 by cid")
	public Accompany selectAccompanybyTid(@PathVariable int cid) throws Exception {
		return accompanyService.selectAccompanybyTid(cid);
	}
	
	@RequestMapping(value = "/selectAccompany/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "동행 리스트 by id")
	public Accompany selectAccompanyPartiByTid(@PathVariable int id) throws Exception {
		return accompanyService.selectAccompany(id);
	}

	@RequestMapping(value = "/accompany", method = RequestMethod.POST)
	@ApiOperation(value = "동행 추가")
	public int insertAccompanyParti(@RequestBody Accompany accompany) throws Exception {
		return accompanyService.insertAccompanyRegist(accompany);
	}

	@RequestMapping(value = "/accompany", method = RequestMethod.PUT)
	@ApiOperation(value = "동행 수정")
	public int updateAccompanyRegist(@RequestBody Accompany accompany) {
		return accompanyService.updateAccompanyRegist(accompany);
	}
}
