package com.trip.qna.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trip.qna.BoardQnaDto;
import com.trip.qna.dao.BoardQnaDao;

@Service
public class BoardQnaServiceImpl implements BoardQnaService {

	@Autowired
	private BoardQnaDao dao;
	
	@Override
	public int registerQna(BoardQnaDto boardQnaDto) throws SQLException {
		return dao.registerQna(boardQnaDto);
	}

	@Override
	public List<BoardQnaDto> listQna(Map<String, Object> param) throws SQLException {
		return dao.listQna(param);
	}

	@Override
	public BoardQnaDto getQna(int articleNo) throws SQLException {
		return dao.getQna(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws SQLException {
		dao.updateHit(articleNo);

	}

	@Override
	public void modifyQna(BoardQnaDto boardQnaDto) throws SQLException {
		dao.modifyQna(boardQnaDto);

	}

	@Override
	public void deleteQna(int articleNo) throws SQLException {
		dao.deleteQna(articleNo);
	}

}
