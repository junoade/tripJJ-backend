package com.trip.search.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.trip.search.dto.AttractionInfo;
import com.trip.search.dto.AttractionInfo_DB;
import com.trip.search.mapper.PlaceSearchMapper;
import org.springframework.stereotype.Service;

import com.trip.attraction.dao.AttractionDao;
import com.trip.exceptions.InvalidPlaceException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
	
	private final PlaceSearchMapper placeSearchMapper;
	private final AttractionFactory factory;


	/**
	 * 관광지 정보, 가까운 관광지를 반환
	 * @param area
	 * @return
	 * @throws InvalidPlaceException
	 * @throws SQLException
	 */
	@Transactional
	public Map<String, Object> getRecommendInfo(Map<String, Object> area) throws InvalidPlaceException, SQLException {
		Map<String, Object> resultMap = new HashMap<>();
		AttractionInfo info = getAttractionInfo(area);

		resultMap.put("attraction_info", info);
		log.debug("attraction_info : {}", info);
		resultMap.put("nearPlaces", getCloseAttraction(info.getLongitude(), info.getLatitude(), info.getAddr1()));
		return resultMap;
	}


	/**
	 * 도로명 주소, 장소 이름을 바탕으로 DB에 저장된 관광지 정보 id를 기록
	 * @param area
	 * @return
	 * @throws InvalidPlaceException
	 * @throws SQLException
	 */
	@Transactional
	@Override
	public AttractionInfo getAttractionInfo(Map<String, Object> area) throws InvalidPlaceException, SQLException {
		AttractionInfo info = factory.createAttractionInfo(area);
		log.debug(info.toString());
		return info;
	}

	public List<AttractionInfo_DB> getCloseAttraction(Double longitude, Double latitude, String addr1) throws SQLException {
		return placeSearchMapper.findNearestAttraction(longitude, latitude, addr1);
	}

}
