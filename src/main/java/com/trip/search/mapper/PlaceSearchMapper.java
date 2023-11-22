package com.trip.search.mapper;

import com.trip.search.dto.AttractionInfo;
import com.trip.search.dto.AttractionInfo_DB;
import com.trip.search.dto.AttractionInfo_Kakao;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface PlaceSearchMapper {
    int findAttractionIdByAddress(String road_address_name, String place_name) throws SQLException;
    Optional<AttractionInfo_DB> findAttractionByAddress(String road_address_name, String place_name) throws SQLException;
    int insertKakaoAttraction(Map<String, Object> area) throws SQLException;

    Optional<AttractionInfo_Kakao> selectKakaoAttraction(String road_address_name, String place_name) throws SQLException;

    List<AttractionInfo_DB> findNearestAttraction(Double longitude, Double latitude, String addr1) throws SQLException;
}
