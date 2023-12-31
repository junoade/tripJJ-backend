package com.trip.practice.snapshot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trip.attraction.dao.AttractionDao;
import com.trip.practice.board.repo.BoardDaoTest;
import com.trip.search.mapper.PlaceSearchMapper;
import com.trip.snapshot.dto.Snapshot;
import com.trip.snapshot.mapper.SnapshotMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest // Spring 환경에서 테스트
public class SnapshotMapperTest {
	
	@Autowired
	private SnapshotMapper mapper;
	
	@Test
	public void testFindSnapshotList() {
		try {
			List<Snapshot> result = mapper.findSnapshotList();
			log.debug("result : {}", result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
