package com.trip.search;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

import com.trip.search.dto.AttractionInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trip.exceptions.InvalidPlaceException;
import com.trip.search.service.SearchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class RestSearchAddressController {
	
	private final SearchService searchService;
	
	@PostMapping("")
	public ResponseEntity<?> searchArea(@RequestBody Map<String, Object> area) throws InvalidPlaceException, SQLException {
		log.debug(area.toString());
		Map<String, Object> resultMap = searchService.getRecommendInfo(area);
		log.debug("searchArea : {}", resultMap);
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}
}
