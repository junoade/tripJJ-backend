package com.trip.controller;

import com.trip.board.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trip.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
public class RestBoardController {

	@Autowired
	private BoardService boardService;
	
	@DeleteMapping("/{no}")
	public ResponseEntity<?> delete(@PathVariable("no") Integer articleNo) throws SQLException {
		log.debug("RestBoardController - delete");
		boardService.deleteArticle(articleNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/top/{no}")
	public ResponseEntity<?> mostTop(@PathVariable("no") Integer n) throws SQLException {
		log.debug("RestBoardController - mostTop");
		List<BoardDto> list = boardService.searchListByTopN(n);
		return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
	}

	@GetMapping("/recent/{no}")
	public ResponseEntity<?> recent(@PathVariable("no") Integer n) throws SQLException {
		log.debug("RestBoardController - recent");
		List<BoardDto> list = boardService.searchListByMostRecentN(n);
		return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
	}
}
