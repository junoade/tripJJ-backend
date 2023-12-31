package com.trip.attraction.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import com.trip.attraction.AttractionInfoDto;
import com.trip.attraction.HotplaceDto;
import com.trip.attraction.InterestDto;
import com.trip.attraction.SearchConditionDto;

@Mapper
public interface AttractionDao {

//	조회
	List<AttractionInfoDto> listAttraction(Map<String, Object> param) throws SQLException;
	int getTotalAttractionCount(Map<String, Object> param) throws SQLException;
	
	List<HotplaceDto> hotPlaceList(String userId);
	HotplaceDto select(int placeNo) throws SQLException;
	
	List<AttractionInfoDto> listInterest(Map<String, Object> param);
	List<Integer> listInterestContentId(String userId);
	int getTotalInterestCount(Map<String, Object> param);
	
//	등록
	int saveHotplace(HotplaceDto dto);
	int insertInterests(Map<String, Object> param);

//	삭제
	int deleteHotPlace(int placeNo);
	void deleteInterests(Map<String, Object> param);
}
