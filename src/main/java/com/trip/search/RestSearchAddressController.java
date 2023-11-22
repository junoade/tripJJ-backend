package com.trip.search;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trip.attraction.AttractionInfoDto;
import com.trip.exceptions.InvalidPlaceException;
import com.trip.search.service.SearchService;
import com.trip.snapshot.RestSnapshotController;
import com.trip.snapshot.service.SnapshotService;

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
	public ResponseEntity<?> searchArea(@RequestBody Map<String, Object> area) {
		
		HttpStatus status = HttpStatus.ACCEPTED;
		log.debug(area.toString());
		
		try {
			Optional<AttractionInfoDto> dto = searchService.getAttractionInfo(area);
			status = HttpStatus.OK;
			return new ResponseEntity<>(dto.get(), status);			
		} catch (InvalidPlaceException e) {
			status = HttpStatus.BAD_REQUEST;
			log.debug(e.getMessage());
		}
		return new ResponseEntity<>(status);
	}
}
