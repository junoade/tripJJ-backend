package com.trip.practice.board.repo;

import com.trip.board.BoardDto;
import com.trip.board.dao.BoardDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
public class BoardMapperTest {

    @Autowired
    private BoardDao mapper;


    /*@Test
    public void selectTest() {
        List<BoardDto> list = mapper.searchListAll();
        assertNotNull(list);
    }
*/
    @Test
    @DisplayName("게시글 등록 테스트")
    public void insertTest() {
        BoardDto board = new BoardDto();
        board.setUserId("ssafy");
        board.setTitle("테스트입니다");
        board.setContent("테스트 코드 작성합니다.");
        board.setVisitedArea("경복궁");
        board.setVisitedDate("2023-11-03");

        assertDoesNotThrow(() -> mapper.registerArticle(board));
    }

    @Test
    @DisplayName("게시글 전체 조회 테스트")
    public void searchListAllTest() throws SQLException {
        List<BoardDto> list = mapper.searchListAll();
        for(BoardDto b : list) {
            log.debug(b.toString());
        }
        assertNotNull(list);
    }

    @Test
    @DisplayName("게시글 제목 검색")
    public void searchListByTitleTest() throws SQLException {
        String str = "야호";
        List<BoardDto> list = mapper.searchListByTitle(str);
        for(BoardDto b : list) {
            log.debug(b.toString());
        }
        assertNotNull(list);
    }

    @Test
    @DisplayName("툭정 게시글 조회")
    public void viewArticleTest() throws SQLException {
        BoardDto board = mapper.viewArticle(1);
        log.debug(board.toString());
        assertNotNull(board);
    }

    @Test
    @DisplayName("툭정 게시글 수정")
    public void modifyArticleTest() throws SQLException {
        // given
        BoardDto before = mapper.viewArticle(1);

        // when
        before.setTitle("제목 변경");
        before.setContent("내용 변경");
        log.debug("after changed : {}", before);
        mapper.modifyArticle(before);

        // then
        BoardDto after = mapper.viewArticle(1);
        assertEquals(before.getTitle(), after.getTitle());
        assertEquals(before.getContent(), after.getContent());
    }

    @Test
    @DisplayName("툭정 게시글 삭제")
    public void deleteArticleTest() {
        assertDoesNotThrow(() -> mapper.deleteArticle(1));
    }

    @Test
    @DisplayName("조회수 높은 게시글 조회")
    public void topNTest() throws SQLException {
        List<BoardDto> list = mapper.searchListByTopN( 5);
        for(BoardDto b : list) {
            log.debug(b.toString());
        }
        assertNotNull(list);
    }
}
