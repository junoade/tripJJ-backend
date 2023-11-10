package com.trip.practice.board.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trip.board.BoardDto;
import com.trip.board.dao.BoardDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest // Spring 환경에서 테스트
public class BoardDaoTest {
	
	@Autowired
	private BoardDao dao;
	
	
	@Test
	@DisplayName("게시판의 모든 게시글을 조회")
	void testSearchAll() throws SQLException {
		List<BoardDto> list = dao.searchListAll();
		log.debug("list size : {}", list.size());
		assertNotNull(list);
	}

}
