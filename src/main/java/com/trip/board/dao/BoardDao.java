package com.trip.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.trip.board.BoardDto;
import com.trip.exceptions.DuplicatedException;
import org.apache.ibatis.annotations.Mapper;

 @Mapper
public interface BoardDao {

	int registerArticle(BoardDto boardDto) throws SQLException;

	List<BoardDto> searchListAll() throws SQLException;

	List<BoardDto> searchListByTitle(String title) throws SQLException;

	BoardDto viewArticle(int no) throws SQLException;

	int updateHit(int no) throws SQLException;

	int modifyArticle(BoardDto boardDto) throws SQLException;

	int deleteArticle(int no) throws SQLException;

	List<BoardDto> searchListByMostRecentN(int count) throws SQLException;
	
	List<BoardDto> searchListByTopN(int count) throws SQLException;
}
