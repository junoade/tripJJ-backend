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
	List<Integer> listInterestContentId(String userId);
	
//	등록
	int saveHotplace(HotplaceDto dto);

//	삭제
    int deleteHotPlace(int placeNo);
    
//  수정
    void updateInterests(Map<String, Object> param);
    
}
