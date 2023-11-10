package com.trip.qna.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.trip.qna.Reply;

@Mapper
public interface ReplyDao {
	
//	등록
	int registerReply(Reply reply) throws SQLException;
	
//	조회
	List<Reply> listReply() throws SQLException;
	Reply getReply(int replyNo) throws SQLException;
	
//	수정
	void modifyReply(Reply Reply) throws SQLException;

//	삭제
	void deleteReply(int replyNo) throws SQLException;
}
