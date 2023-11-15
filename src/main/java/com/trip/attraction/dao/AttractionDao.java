package com.trip.attraction.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.trip.attraction.AttractionInfoDto;
import com.trip.attraction.HotplaceDto;
import com.trip.attraction.SearchConditionDto;

@Mapper
public interface AttractionDao {

	List<AttractionInfoDto> attractionList(SearchConditionDto searchConditionDto);
	
	List<HotplaceDto> hotPlaceList(String userId);
	
	int saveHotplace(HotplaceDto dto);

	int deleteHotPlace(int placeNo);

	HotplaceDto select(int placeNo) throws SQLException;
}
