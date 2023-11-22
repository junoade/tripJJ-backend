package com.trip.controller;

import com.trip.attraction.AttractionInfoPagingList;
import com.trip.attraction.HotplaceDto;
import com.trip.attraction.InterestDto;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/attraction")
public class RestAttractionController {
	
	@Autowired
	private AttractionService service;
	
	@Autowired
	private SidoGugunService searchService;
	
//	관광지 조회
	@GetMapping("/search")
	public ResponseEntity<?> listAttractions(@RequestParam Map<String, Object> param){
		log.debug("[RestAttractionController]: /attraction/search with searchConditionDto = {}", param);
		try {
//			관광지 리스트, 현재 페이지, 전체 페이지 수 받아오기
			AttractionInfoPagingList attractionInfoPagingList = service.listAttraction(param);
			
//			반환할 HTTP의 헤더 설정
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			
//			바디 설정
			Map<String, Object> result = new HashMap();
			result.put("attractions", attractionInfoPagingList.getAttractions());
			result.put("currentPage", attractionInfoPagingList.getCurrentPage());
			result.put("totalPageCount", attractionInfoPagingList.getTotalPage());
			
			return ResponseEntity.ok().headers(header).body(result);
		} catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
//	관심 관광지 조회 with 검색 조건
	@GetMapping("/interests")
	public ResponseEntity<?> listInterests(@RequestParam Map<String, Object> param) {
		log.debug("RestAttractionController - listInterests ", param);		
		try {
//			관광지 리스트, 현재 페이지, 전체 페이지 수 받아오기
			AttractionInfoPagingList attractionInfoPagingList = service.listInterest(param);
			
//			반환할 HTTP의 헤더 설정
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			
//			바디 설정
			Map<String, Object> result = new HashMap();
			result.put("attractions", attractionInfoPagingList.getAttractions());
			result.put("currentPage", attractionInfoPagingList.getCurrentPage());
			result.put("totalPageCount", attractionInfoPagingList.getTotalPage());
			
			return ResponseEntity.ok().headers(header).body(result);
		} catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
//	특정 사용자의 모든 관심 관광지 조회
	@GetMapping("/interests/contentIds")
	public ResponseEntity<List<Integer>> listInterestsContentId(@RequestParam String userId){
		log.debug("RestAttractionController - listInterestsContentId ".concat(userId));
		return new ResponseEntity<>(service.listInterestContentId(userId), HttpStatus.OK);
	}
	
//	관심 관광지 업데이트
	@PostMapping("/interests")
	public ResponseEntity<?> updateInterests(@RequestBody InterestDto interests) {
		log.debug("관심 관광지 삭제 및 추가 : ", interests);
		try {
			service.updateInterests(interests);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch(Exception e) {
			return exceptionHandling(e);
		}
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
	
//	===========================================================================
//	핫 플레이스 삭제
	@DeleteMapping("/{no}")
	public ResponseEntity<?> delete(@PathVariable("no") int placeNo) {
		log.debug("RestAttractionController - delete");
		service.deleteHotPlace(placeNo);
		return new ResponseEntity<>(HttpStatus.OK);
	}

//	핫 플레이스 조회
	@GetMapping("/{no}")
	public ResponseEntity<?> getDetail(@PathVariable("no") int placeNo) throws SQLException {
		log.debug("RestAttractionController - getDetail");
		HotplaceDto dto = service.select(placeNo);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
