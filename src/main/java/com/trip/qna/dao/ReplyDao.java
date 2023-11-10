package com.trip.qna.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.trip.qna.ReplyDto;

@Mapper
public interface ReplyDao {
	
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
