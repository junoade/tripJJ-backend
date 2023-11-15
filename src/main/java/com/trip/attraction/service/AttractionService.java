package com.trip.attraction.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.trip.attraction.AttractionInfoDto;
import com.trip.attraction.HotplaceDto;
import com.trip.attraction.SearchConditionDto;

public interface AttractionService {

	List<AttractionInfoDto> listAttraction(Map<String, String> param) throws SQLException;
	
	int saveHotplace(HotplaceDto dto);
	
	List<HotplaceDto> hotPlaceList(String userId);

    int deleteHotPlace(int placeNo);

	HotplaceDto select(int placeNo) throws SQLException;
}
