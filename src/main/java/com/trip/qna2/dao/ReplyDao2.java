package com.trip.qna2.dao;

import com.trip.qna2.ReplyDto2;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface ReplyDao2 {

//	등록
	int registerReply(ReplyDto2 reply) throws SQLException;
//
//	조회
	List<ReplyDto2> listReply(int articleNo) throws SQLException;
    ReplyDto2 getReply(int replyNo) throws SQLException;

//	수정
	void modifyReply(ReplyDto2 Reply) throws SQLException;

//	삭제
	void deleteReply(int replyNo) throws SQLException;
}
