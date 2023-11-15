package com.trip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.trip.attraction.SidoGugunDto;
import com.trip.attraction.service.SidoGugunService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/map")
@Slf4j
public class RestSearchController {

	@Autowired
	private SidoGugunService service;
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunDto>> sido() throws Exception {
		log.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunDto>>(service.getSido(), HttpStatus.OK);
	}

	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunDto>> gugun(@RequestParam("sidoCode") String sidoCode) throws Exception {
		log.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunDto>>(service.getGugun(sidoCode), HttpStatus.OK);
	}
	
	
}
