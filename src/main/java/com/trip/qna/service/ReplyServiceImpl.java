package com.trip.qna.service;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trip.qna.ReplyDto;
import com.trip.qna.dao.ReplyDao;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDao dao;
	
	@Override
	public int registerReply(ReplyDto reply) throws SQLException {
		return dao.registerReply(reply);
	}

	@Override
	public List<ReplyDto> listReply(int articleNo) throws SQLException {
		return dao.listReply(articleNo);
	}

	@Override
	public ReplyDto getReply(int replyNo) throws SQLException {
		return dao.getReply(replyNo);
	}

	@Override
	public void modifyReply(ReplyDto Reply) throws SQLException {
		dao.modifyReply(Reply);
	}

	@Override
	public void deleteReply(int replyNo) throws SQLException {
		dao.deleteReply(replyNo);
	}

}
