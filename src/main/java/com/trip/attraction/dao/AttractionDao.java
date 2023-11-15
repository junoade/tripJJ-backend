package com.trip.attraction.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.trip.attraction.AttractionInfoDto;
import com.trip.attraction.HotplaceDto;
import com.trip.attraction.SearchConditionDto;

@Mapper
public interface AttractionDao {

	List<AttractionInfoDto> listAttraction(Map<String, String> param) throws SQLException;
	
	List<HotplaceDto> hotPlaceList(String userId);
	
	int saveHotplace(HotplaceDto dto);

	int deleteHotPlace(int placeNo);

	HotplaceDto select(int placeNo) throws SQLException;
}
