package com.trip.attraction.service;

import java.sql.SQLException;
import java.util.List;

import com.trip.attraction.AttractionInfoDto;
import com.trip.attraction.HotplaceDto;

public interface AttractionService {

	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto);

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode);
	
	int saveHotplace(HotplaceDto dto);
	
	List<HotplaceDto> hotPlaceList(String userId);

    int deleteHotPlace(int placeNo);

	HotplaceDto select(int placeNo) throws SQLException;
}
