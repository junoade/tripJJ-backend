package com.trip.qna.dao;

import java.sql.SQLException;
import org.apache.ibatis.annotations.Mapper;
import com.trip.qna.BoardQnaDto;
import java.util.*;

@Mapper
public interface BoardQnaDao {
	
//	등록
	int writeQna(BoardQnaDto boardQnaDto) throws SQLException;
	
//	조회
	List<BoardQnaDto> listQna(Map<String, Object> param) throws SQLException;
	List<BoardQnaDto> listQna2(Map<String, Object> param) throws SQLException;
	int getTotalQnaCount(Map<String, Object> param) throws SQLException;
	BoardQnaDto getQna(int articleNo) throws SQLException;

//	수정
	void updateHit(int articleNo) throws SQLException;
	void modifyQna(BoardQnaDto boardQnaDto) throws SQLException;

//	삭제
	void deleteQna(int articleNo) throws SQLException;
}
