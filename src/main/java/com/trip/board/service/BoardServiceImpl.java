package com.trip.board.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.board.BoardDto;
import com.trip.board.dao.BoardDao;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao dao;

	@Transactional
	@Override
	public void registerArticle(BoardDto boardDto) throws SQLException {
		dao.registerArticle(boardDto);
	}

	@Override
	public List<BoardDto> searchListAll() throws SQLException {
		return dao.searchListAll();
	}

	@Override
	public List<BoardDto> searchListBySubject(String subject) throws SQLException {
		return dao.searchListByTitle(subject);
	}

	@Transactional
	@Override
	public BoardDto viewArticle(int no) throws SQLException {
		// Transactional 하게, 한 커넥션에서 조회수 증가까지
		BoardDto dto = dao.viewArticle(no);
		dao.updateHit(no);
		// <input type="date"> 에서 value 속성의 경우 값으로 "YYYY-MM-DD"를 받음
		String date = dto.getVisitedDate().split(" ")[0];
		dto.setVisitedDate(date);
		return dto;
	}

	@Transactional
	@Override
	public void modifyArticle(BoardDto boardDto) throws SQLException {
		dao.modifyArticle(boardDto);
	}

	@Transactional
	@Override
	public void deleteArticle(int no) throws SQLException {
		dao.deleteArticle(no);
	}
	
	@Override
	public List<BoardDto> searchListByMostRecentN(int count) throws SQLException {
		return dao.searchListByMostRecentN(count);
	}
	
	@Override
	public List<BoardDto> searchListByTopN(int count) throws SQLException {
		return dao.searchListByTopN(count);
	}
}
