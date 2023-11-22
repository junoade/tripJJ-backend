package com.trip.search.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trip.attraction.AttractionInfoDto;
import com.trip.attraction.dao.AttractionDao;
import com.trip.exceptions.InvalidPlaceException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
	
	private final AttractionDao attractionMapper;

	@Override
	public Optional<AttractionInfoDto> getAttractionInfo(Map<String, Object> area) throws InvalidPlaceException {
		String road_address_name = (String)area.get("road_address_name");
		String place_name = (String)area.get("place_name");
		validateAddressInfo(road_address_name, place_name);
		
		Optional<AttractionInfoDto> attractionInfo = attractionMapper.findAttractionByAddress(road_address_name, place_name);
		
		return attractionInfo;
	}
	
	private void validateAddressInfo(String road_address_name, String place_name) throws InvalidPlaceException{
		if(road_address_name.equals("") || place_name.equals("")) {
			throw new InvalidPlaceException();
		}
	}


	
}
