package com.trip.attraction.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.trip.attraction.AttractionInfoDto;
import com.trip.attraction.AttractionInfoPagingList;
import com.trip.attraction.HotplaceDto;
import com.trip.attraction.InterestDto;
import com.trip.attraction.SearchConditionDto;

public interface AttractionService {

//	조회
	AttractionInfoPagingList listAttraction(Map<String, Object> param) throws SQLException;
	
	List<HotplaceDto> hotPlaceList(String userId);
	HotplaceDto select(int placeNo) throws SQLException;
	
	AttractionInfoPagingList listInterest(Map<String, Object> param);
	
//	등록
	int saveHotplace(HotplaceDto dto);
	int saveInterest(InterestDto dto);

//	삭제
    int deleteHotPlace(int placeNo);
    int deleteInterest(InterestDto dto);
}
