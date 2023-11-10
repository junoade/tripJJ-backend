package com.trip.board.service;

import java.sql.SQLException;
import java.util.List;

import com.trip.board.BoardDto;

public interface BoardService {

	void registerArticle(BoardDto boardDto) throws SQLException;

	List<BoardDto> searchListAll() throws SQLException;

	List<BoardDto> searchListBySubject(String subject) throws SQLException;

	BoardDto viewArticle(int no) throws SQLException;

	void modifyArticle(BoardDto boardDto) throws SQLException;

	void deleteArticle(int no) throws SQLException;

	List<BoardDto> searchListByMostRecentN(int count) throws SQLException;
	
	List<BoardDto> searchListByTopN(int count) throws SQLException;
}
