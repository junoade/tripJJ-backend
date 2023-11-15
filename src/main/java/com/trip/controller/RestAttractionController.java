package com.trip.controller;

import com.trip.attraction.AttractionInfoDto;
import com.trip.attraction.HotplaceDto;
import com.trip.attraction.SearchConditionDto;
import com.trip.attraction.SidoGugunDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trip.attraction.service.AttractionService;
import com.trip.attraction.service.SidoGugunService;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/attraction")
public class RestAttractionController {
	
	@Autowired
	private AttractionService service;
	
	@Autowired
	private SidoGugunService searchService;
	
//	GetMapping에는 @RequestBody 불가!!!
//	@GetMapping("/search")
//	public ResponseEntity<?> listAttractions(SearchConditionDto searchConditionDto){
//		log.debug("[RestAttractionController]: /attraction/search with searchConditionDto = {}", searchConditionDto);
//		return new ResponseEntity<List<AttractionInfoDto>>(service.attractionList(searchConditionDto), HttpStatus.OK);
////		return new ResponseEntity<>(1, HttpStatus.OK);
//	}
	
	@GetMapping("/search")
	public ResponseEntity<?> listAttractions(@RequestParam SearchConditionDto searchConditionDto){
		log.debug("[RestAttractionController]: /attraction/search with searchConditionDto = {}", searchConditionDto);
		try {
			List<AttractionInfoDto> list = service.attractionList(searchConditionDto);
			
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(list);
		} catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	@DeleteMapping("/{no}")
	public ResponseEntity<?> delete(@PathVariable("no") int placeNo) {
		log.debug("RestAttractionController - delete");
		service.deleteHotPlace(placeNo);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{no}")
	public ResponseEntity<?> getDetail(@PathVariable("no") int placeNo) throws SQLException {
		log.debug("RestAttractionController - getDetail");
		HotplaceDto dto = service.select(placeNo);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunDto>> sido() throws Exception {
		log.info("sido - 호출");
		return new ResponseEntity<>(searchService.getSido(), HttpStatus.OK);
	}

	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunDto>> gugun(@RequestParam("sidoCode") String sidoCode) throws Exception {
		log.info("gugun - 호출");
		return new ResponseEntity<>(searchService.getGugun(sidoCode), HttpStatus.OK);
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
