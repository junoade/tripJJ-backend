package com.trip.attraction.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import com.trip.attraction.AttractionInfoDto;
import com.trip.attraction.HotplaceDto;
import com.trip.attraction.SearchConditionDto;

@Mapper
public interface AttractionDao {

//	조회
	List<AttractionInfoDto> listAttraction(Map<String, Object> param) throws SQLException;
	int getTotalAttractionCount(Map<String, Object> param) throws SQLException;
	
	List<HotplaceDto> hotPlaceList(String userId);
	HotplaceDto select(int placeNo) throws SQLException;
	
//	등록
	int saveHotplace(HotplaceDto dto);

//	삭제
	int deleteHotPlace(int placeNo);
	
	// 특정 주소 또는 경도를 바탕으로 pk 반환
	int findAttractionIdByAddress(String road_address_name, String place_name);
	Optional<AttractionInfoDto> findAttractionByAddress(String road_address_name, String place_name);
	
}
