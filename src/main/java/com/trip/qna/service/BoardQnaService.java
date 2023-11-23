package com.trip.qna.service;

import java.sql.SQLException;
import com.trip.qna.BoardQnaDto;
import com.trip.qna.BoardQnaPagingList;
import com.trip.qna.ReplyDto;

import java.util.*;

public interface BoardQnaService {
	
//	등록
	int writeQna(BoardQnaDto boardQnaDto) throws SQLException;
	int writeReply(ReplyDto reply) throws SQLException;
	
//	조회
	BoardQnaPagingList listQna(Map<String, Object> param) throws SQLException;
	BoardQnaDto getQna(int articleNo) throws SQLException;
	List<ReplyDto> listReply(int articleNo) throws SQLException;
	ReplyDto getReply(int replyNo) throws SQLException;

//	수정
	void updateHit(int articleNo) throws SQLException;
	void modifyQna(BoardQnaDto boardQnaDto) throws SQLException;
	void modifyReply(ReplyDto Reply) throws SQLException;

//	삭제
	void deleteQna(int articleNo) throws SQLException;
	void deleteReply(int replyNo) throws SQLException;
}
