package com.trip.search.service;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

import com.trip.search.dto.AttractionInfo;
import org.springframework.stereotype.Service;

import com.trip.exceptions.InvalidPlaceException;

@Service
public interface SearchService {
	AttractionInfo getAttractionInfo(Map<String, Object> area) throws InvalidPlaceException, SQLException;
	Map<String, Object> getRecommendInfo(Map<String, Object> area) throws InvalidPlaceException, SQLException;
}
