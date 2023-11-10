package com.trip.qna.service;

import java.sql.SQLException;
import org.apache.ibatis.annotations.Mapper;
import com.trip.qna.BoardQnaDto;
import com.trip.qna.BoardQnaPagingList;

import java.util.*;

public interface BoardQnaService {
	
//	등록
	int registerQna(BoardQnaDto boardQnaDto) throws SQLException;
	
//	조회
//	BoardQnaPagingList listQna(Map<String, Object> param) throws SQLException;
	List<BoardQnaDto> listQna(Map<String, Object> param) throws SQLException;
	BoardQnaDto getQna(int articleNo) throws SQLException;

//	수정
	void updateHit(int articleNo) throws SQLException;
	void modifyQna(BoardQnaDto boardQnaDto) throws SQLException;

//	삭제
	void deleteQna(int articleNo) throws SQLException;
}
