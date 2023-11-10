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
import com.trip.qna.ReplyDto;
import com.trip.qna.dao.ReplyDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@SpringBootTest // Spring 환경에서 테스트
public class ReplyDaoTest {
	
	@Autowired
	private ReplyDao dao;
	
//	조회
	@Test
	@DisplayName("모든 댓글을 조회")
	void testSearchAll() throws SQLException {
		List<ReplyDto> list = dao.listReply(1);
		System.out.println("[전체 검색]\n" + list);
		assertThat(!list.isEmpty());
	}
	
//	등록
	@Test
    @DisplayName("댓글 등록 테스트")
    public void insertTest() {
        ReplyDto reply = new ReplyDto();
        reply.setComment("테스트 등록");
        reply.setArticleNo(1);
        reply.setUserId("zzafy");

        assertDoesNotThrow(() -> dao.registerReply(reply));
    }

//  수정
    @Test
    @DisplayName("특정 댓글 수정")
    public void modifyArticleTest() throws SQLException {
        // given
        ReplyDto before = dao.getReply(3);  

        // when
        before.setComment("내용 변경");
        log.debug("after changed : {}", before);
        dao.modifyReply(before);
        
        // then
        ReplyDto after = dao.getReply(3);
        assertEquals(before.getComment(), after.getComment());
    }
	
//	삭제
	@Test
	@DisplayName("게시판 삭제")
	void testDelete() throws SQLException {
		assertDoesNotThrow(() -> dao.deleteReply(1));
	}
}
