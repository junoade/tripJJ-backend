package com.trip.controller;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.trip.qna.BoardQnaDto;
import com.trip.qna.service.BoardQnaServiceImpl;
import com.trip.qna.service.ReplyServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/qna")
public class RestBoardQnaController {

	@Autowired
	private BoardQnaServiceImpl bs;
	
	@Autowired
	private ReplyServiceImpl rs;
	
//	QNA 등록
	@PostMapping("/write")
	public ResponseEntity<?> registerQna(@RequestBody BoardQnaDto boardQnaDto) {
		log.debug("게시글 작성 : " + boardQnaDto.toString());
		try {
			int result = bs.registerQna(boardQnaDto);
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
//			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
//	조회 - 검색
	@GetMapping
	public ResponseEntity<?> listArticle(@RequestParam Map<String, Object> map) {
		log.info("listArticle map - {}", map);
		try {
			List<BoardQnaDto> list = bs.listQna(map);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			Map<String, List<BoardQnaDto>> result = new HashMap<>();
			result.put("articles", list);
			return ResponseEntity.ok().headers(header).body(result);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

//	조회
//	@GetMapping("/{articleNo}")
//	public ResponseEntity<BoardQna> getArticle(@PathVariable int articleNo) throws Exception {
//		log.info("getArticle - 호출 : " + articleNo);
//		bs.updateHit(articleNo);
//		return new ResponseEntity<BoardQnaDto>(bs.getQna(articleNo), HttpStatus.OK);
//	}
	
	@GetMapping("/{articleNo}")
	public ResponseEntity<?> getArticle(@PathVariable int articleNo) throws Exception {
		log.info("getArticle - 호출 : " + articleNo);
		bs.updateHit(articleNo);
		
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("article", bs.getQna(articleNo));
		map.put("replies", rs.listReply(articleNo));
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

//	수정할 Q&A 조회
	@GetMapping("/modify/{articleno}")
	public ResponseEntity<BoardQnaDto> getModifyArticle(
			@PathVariable("articleno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int articleno)
			throws Exception {
		log.info("getModifyArticle - 호출 : " + articleno);
		return new ResponseEntity<BoardQnaDto>(bs.getQna(articleno), HttpStatus.OK);
	}

//	수정
	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyArticle(
			@RequestBody @ApiParam(value = "수정할 글정보.", required = true) BoardQnaDto boardQnaDto) throws Exception {
		log.info("modifyArticle - 호출 {}", boardQnaDto);
		bs.modifyQna(boardQnaDto);
		return ResponseEntity.ok().build();
	}
	
//	삭제
	@DeleteMapping("{articleNo}")
	public ResponseEntity<?> deleteQna(@PathVariable int articleNo) throws SQLException {
		log.debug("QNA 삭제 : ", articleNo);
		bs.deleteQna(articleNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
