package com.trip.controller;

import com.trip.attraction.HotplaceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trip.attraction.service.AttractionService;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
@RestController
@RequestMapping("/attraction")
public class RestAttractionController {
	
	@Autowired
	private AttractionService service;
	
	@DeleteMapping("/{no}")
	public ResponseEntity<?> delete(@PathVariable("no") int placeNo) {
		log.debug("RestAttractionController - delete");
		service.deleteHotPlace(placeNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/{no}")
	public ResponseEntity<?> getDetail(@PathVariable("no") int placeNo) throws SQLException {
		log.debug("RestAttractionController - getDetail");
		HotplaceDto dto = service.select(placeNo);
		return new ResponseEntity<HotplaceDto>(dto, HttpStatus.OK);
	}
}
