package com.trip.search.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trip.attraction.AttractionInfoDto;
import com.trip.exceptions.InvalidPlaceException;

@Service
public interface SearchService {
	Optional<AttractionInfoDto> getAttractionInfo(Map<String, Object> area) throws InvalidPlaceException;
}
