package com.trip.practice.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trip.practice.snapshot.SnapshotMapperTest;
import com.trip.search.mapper.PlaceSearchMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest // Spring 환경에서 테스트
public class PlaceSearchMapperTest {
	
	@Autowired
	private PlaceSearchMapper mapper;
	
	@Test
	public void testGetAttractionId() {
		String road_address_name = "서울 종로구 사직로 161";
		String place_name = "광화문";
		
		int expected = 126512;
		int actual = 0;
		try {
			actual = mapper.findAttractionIdByAddress(road_address_name, place_name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(expected, actual);
	}
}
