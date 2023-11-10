package com.trip.qna.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.trip.qna.BoardQnaDto;
import com.trip.qna.BoardQnaPagingList;
import com.trip.qna.dao.BoardQnaDao;

@Service
@Transactional
public class BoardQnaServiceImpl implements BoardQnaService {

	@Autowired
	private BoardQnaDao dao;
	
	@Override
	public int registerQna(BoardQnaDto boardQnaDto) throws SQLException {
		return dao.registerQna(boardQnaDto);
	}

//	@Override
//	public BoardQnaPagingList listQna(Map<String, Object> map) throws SQLException {
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int currentPage = Integer.parseInt((String)map.get("pgno") == null ? "1" : (String)map.get("pgno"));
//		int sizePerPage = Integer.parseInt((String)map.get("spp") == null ? "20" : (String)map.get("spp"));
//		int start = currentPage * sizePerPage - sizePerPage;
//		param.put("start", start);
//		param.put("listsize", sizePerPage);
//
//		String key = (String)map.get("key");
//		param.put("key", key == null ? "" : key);
//		if ("user_id".equals(key))
//			param.put("key", key == null ? "" : "b.user_id");
//		List<BoardQnaDto> list = dao.listQna(param);
//
//		if ("user_id".equals(key))
//			param.put("key", key == null ? "" : "user_id");
//		int totalArticleCount = dao.getTotalArticleCount(param);
//		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;
//
//		BoardListDto boardListDto = new BoardListDto();
//		boardListDto.setArticles(list);
//		boardListDto.setCurrentPage(currentPage);
//		boardListDto.setTotalPageCount(totalPageCount);
//
//		return boardListDto;
//	}
	
	@Override
	public List<BoardQnaDto> listQna(Map<String, Object> param) throws SQLException {
		// TODO Auto-generated method stub
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
