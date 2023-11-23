package com.trip.practice.snapshot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trip.attraction.dao.AttractionDao;
import com.trip.practice.board.repo.BoardDaoTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest // Spring 환경에서 테스트
public class SnapshotMapperTest {
	
	@Autowired
	private AttractionDao attractionDao;
	
	@Test
	public void testGetAttractionId() {
		String road_address_name = "서울 종로구 사직로 161";
		String place_name = "광화문";
		
		int expected = 126512;
		int actual = attractionDao.findAttractionIdByAddress(road_address_name, place_name);
		
		assertEquals(expected, actual);
	}
}
