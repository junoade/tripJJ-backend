package com.trip.practice.snapshot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trip.exceptions.InvalidPlaceException;
import com.trip.snapshot.dto.Snapshot;
import com.trip.snapshot.service.SnapshotService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SnapshotServiceTest {
	
	@Autowired
	private SnapshotService service;
	
	@Test
	void testUploadSnapshot() {
		// given
		Snapshot dto = new Snapshot();
		dto.setUserId("test");
		dto.setTag("#여행 #즐거움 #좋아");
		dto.setStartDate("2023-11-22");
		dto.setEndDate("2023-11-22");
	
		String road_address_name = "서울 종로구 사직로 161";
		String place_name = "광화문";
		Map<String, Object> area = new HashMap<>();
		area.put("road_address_name", "서울 종로구 사직로 161");
		area.put("place_name", "광화문");
		
		// when 
		int expected = 1;
		int actual = -1;
		
		try {
			actual = service.uploadSnapshot(dto, area);
		} catch (SQLException | InvalidPlaceException e) {
			// TODO Auto-generated catch block
			log.debug(e.getMessage());
		}
		
		// then
		assertEquals(expected, actual);
	}
}
