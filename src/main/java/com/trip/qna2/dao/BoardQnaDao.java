package com.trip.qna2.dao;

import java.sql.SQLException;
import java.util.*;
import org.apache.ibatis.annotations.Mapper;
import com.trip.qna2.BoardQnaDto;
import com.trip.qna2.FileInfoDto;
import com.trip.qna2.ReplyDto;

@Mapper
public interface BoardQnaDao {
	
	void writeQna(BoardQnaDto boardQnaDto) throws SQLException;

	void registerFile(BoardQnaDto boardQnaDto) throws Exception;

	List<BoardQnaDto> listQna(Map<String, Object> param) throws SQLException;

	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	BoardQnaDto getQna(int articleNo) throws SQLException;

	void updateHit(int articleNo) throws SQLException;

	void modifyQna(BoardQnaDto boardQnaDto) throws SQLException;

	void deleteFile(int articleNo) throws Exception;

	void deleteQna(int articleNo) throws SQLException;

	List<FileInfoDto> fileInfoList(int articleNo) throws Exception;
	
	
//	등록
	int registerReply(ReplyDto reply) throws SQLException;
	
//	조회
	List<ReplyDto> listReply(int articleNo) throws SQLException;
	ReplyDto getReply(int replyNo) throws SQLException;
	
//	수정
	void modifyReply(ReplyDto Reply) throws SQLException;

//	삭제
	void deleteReply(int replyNo) throws SQLException;
}
