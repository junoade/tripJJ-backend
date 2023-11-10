package com.trip.practice.qna.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.SQLException;
import java.util.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.trip.qna.BoardQnaDto;
import com.trip.qna.dao.BoardQnaDao;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@SpringBootTest // Spring 환경에서 테스트
public class BoardQnaDao2Test {
	
	@Autowired
	private BoardQnaDao dao;
	
//	조회
	@Test
	@DisplayName("게시판의 모든 게시글을 조회")
	void testSearchAll() throws SQLException {
		Map<String, Object> map = new HashMap<>();
		List<BoardQnaDto> list = dao.listQna(map);
		
		System.out.println("[전체 검색]\n" + list);
		assertThat(!list.isEmpty());
	}
	
	@Test
	@DisplayName("게시판 글번호 검색")
	void testSearchByArticleNo() throws SQLException {
		Map<String, Object> map = new HashMap<>();
		
		map.put("key", "articleNo");
		map.put("word", 0);
		List<BoardQnaDto> list = dao.listQna(map);
		log.debug("[글번호 검색1]\n", list.toString());
		assertThat(list.isEmpty());
		
		map.clear();
		map.put("key", "articleNo");
		map.put("word", 2);
		list = dao.listQna(map);
		log.debug("[글번호 검색2]\n", list.toString());
		assertThat(!list.isEmpty());
	}
	
	@Test
	@DisplayName("게시판 유저 아이디 검색")
	void testSearchByUserId() throws SQLException {
		Map<String, Object> map = new HashMap<>();
		
		map.put("key", "userId");
		map.put("word", "test");
		List<BoardQnaDto> list = dao.listQna(map);
		log.debug("[유저 아이디 검색1]\n", list);
		assertThat(list.isEmpty());
		
		map.clear();
		map.put("key", "userId");
		map.put("word", "ss");
		list = dao.listQna(map);
		log.debug("[유저 아이디 검색2]\n", list);
		assertThat(!list.isEmpty());
	}
	
	@Test
	@DisplayName("게시판 제목 검색")
	void testSearchByTitle() throws SQLException {
		Map<String, Object> map = new HashMap<>();
		
		map.put("key", "title");
		map.put("word", "없음");
		List<BoardQnaDto> list = dao.listQna(map);
		log.debug("[title 검색1]\n", list);
		assertThat(list.isEmpty());
		
		map.clear();
		map.put("key", "title");
		map.put("word", "가나요");
		list = dao.listQna(map);
		log.debug("[title 검색2]\n", list);
		assertThat(!list.isEmpty());
	}
	
	@Test
	@DisplayName("게시판 내용 검색")
	void testSearchByComment() throws SQLException {
		Map<String, Object> map = new HashMap<>();
		
		map.put("key", "content");
		map.put("word", "test");
		List<BoardQnaDto> list = dao.listQna(map);
		log.debug("[content 검색]\n", list);
		assertThat(list.isEmpty());
		
		map.clear();
		map.put("key", "content");
		map.put("word", "con");
		list = dao.listQna(map);
		log.debug("[content 검색]\n", list);
		assertThat(!list.isEmpty());
	}
	
//	등록
	@Test
    @DisplayName("게시글 등록 테스트")
    public void insertTest() {
        BoardQnaDto board = new BoardQnaDto();
        board.setUserId("zzafy");
        board.setTitle("테스트입니다");
        board.setContent("테스트 코드 작성합니다.");

        assertDoesNotThrow(() -> dao.registerQna(board));
    }

//  수정
    @Test
    @DisplayName("특정 게시글 수정")
    public void modifyArticleTest() throws SQLException {
        // given
        BoardQnaDto before = dao.getQna(1);  

        // when
        before.setTitle("제목 변경");
        before.setContent("내용 변경");
        log.debug("after changed : {}", before);
        dao.modifyQna(before);
        
        // then
        BoardQnaDto after = dao.getQna(1);
        assertEquals(before.getTitle(), after.getTitle());
        assertEquals(before.getContent(), after.getContent());
    }
	
//	삭제
	@Test
	@DisplayName("게시판 삭제")
	void testDelete() throws SQLException {
		assertDoesNotThrow(() -> dao.deleteQna(1));
	}
}
