package com.trip.qna2.dao;

import com.trip.qna2.BoardQnaDto2;
import com.trip.qna2.FileInfoDto2;
import com.trip.qna2.ReplyDto2;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardQnaDao2 {
	
	void writeQna(BoardQnaDto2 boardQnaDto) throws SQLException;

	void registerFile(BoardQnaDto2 boardQnaDto) throws Exception;

	List<BoardQnaDto2> listQna(Map<String, Object> param) throws SQLException;

	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

    BoardQnaDto2 getQna(int articleNo) throws SQLException;

	void updateHit(int articleNo) throws SQLException;

	void modifyQna(BoardQnaDto2 boardQnaDto) throws SQLException;

	void deleteFile(int articleNo) throws Exception;

	void deleteQna(int articleNo) throws SQLException;

	List<FileInfoDto2> fileInfoList(int articleNo) throws Exception;


//	등록
	int registerReply(ReplyDto2 reply) throws SQLException;

//	조회
	List<ReplyDto2> listReply(int articleNo) throws SQLException;
    ReplyDto2 getReply(int replyNo) throws SQLException;

//	수정
	void modifyReply(ReplyDto2 Reply) throws SQLException;

//	삭제
	void deleteReply(int replyNo) throws SQLException;
}
