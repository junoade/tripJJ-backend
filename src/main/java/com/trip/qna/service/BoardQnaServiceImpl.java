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
import com.trip.qna.ReplyDto;
import com.trip.qna.dao.BoardQnaDao;

@Service
@Transactional
public class BoardQnaServiceImpl implements BoardQnaService {

	@Autowired
	private BoardQnaDao dao;
	
	@Override
	public int writeQna(BoardQnaDto boardQnaDto) throws SQLException {
		return dao.writeQna(boardQnaDto);
	}
	
	@Override
	public int writeReply(ReplyDto reply) throws SQLException {
		return dao.writeReply(reply);
	}
	
	@Override
	public BoardQnaPagingList listQna(Map<String, Object> param) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		String key = param.get("key")==null ? "" : (String)param.get("key");
		Object word = param.get("word")==null ? "" : param.get("word");
		int currentPage = Integer.parseInt(param.get("pgno") == null ? "1" : (String)param.get("pgno"));
		int sizePerPage = Integer.parseInt(param.get("spp") == null ? "20" : (String)param.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;
		
		map.put("key", key);
		map.put("word", word);
		map.put("start", start);
		map.put("listsize", sizePerPage);

		List<BoardQnaDto> list = dao.listQna(map);
		int totalArticleCount = dao.getTotalQnaCount(map);
		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;
		
		BoardQnaPagingList boardQnaPagingList = new BoardQnaPagingList();
		boardQnaPagingList.setArticles(list);
		boardQnaPagingList.setTotalPageCount(totalPageCount);
		boardQnaPagingList.setCurrentPage(currentPage);

		return boardQnaPagingList;
	}
	
	@Override
	public List<ReplyDto> listReply(int articleNo) throws SQLException {
		return dao.listReply(articleNo);
	}

	@Override
	public BoardQnaDto getQna(int articleNo) throws SQLException {
		return dao.getQna(articleNo);
	}

	@Override
	public ReplyDto getReply(int replyNo) throws SQLException {
		return dao.getReply(replyNo);
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
	public void modifyReply(ReplyDto Reply) throws SQLException {
		dao.modifyReply(Reply);
	}

	@Override
	public void deleteQna(int articleNo) throws SQLException {
		dao.deleteQna(articleNo);
	}
	
	@Override
	public void deleteReply(int replyNo) throws SQLException {
		dao.deleteReply(replyNo);
	}

}
