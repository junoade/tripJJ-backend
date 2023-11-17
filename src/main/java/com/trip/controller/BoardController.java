package com.trip.controller;

import java.sql.SQLException;
import java.util.List;

import com.trip.member.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trip.board.BoardDto;
import com.trip.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/board") // 리소스
public class BoardController {
	
	// 의존성 주입 필요
	@Autowired
	private BoardService boardService;

	@GetMapping("/")
	public String list(Model model) throws SQLException {
		log.debug(">>> BoardController - list()"); 		
		//TODO 페이징 처리
		List<BoardDto> boards = boardService.searchListAll();
		
		log.debug(boards.toString());
	
		model.addAttribute("boards", boards);
		
		return "board/list";
	}
		
	@GetMapping("/{no}")
	public String detail(@PathVariable("no") Integer articleNo, Model model) throws SQLException {
		log.debug(">>> BoardController - detail()"); 
		BoardDto board = boardService.viewArticle(articleNo);
		model.addAttribute("board", board);
		return "board/detail";
	}
	
	@GetMapping("/create")
	public String viewCreateForm() {
		log.debug(">>> BoardController - viewCreateForm()"); 
		return "board/boardForm";
	}
	
	@PostMapping("/")
	public String create(@ModelAttribute BoardDto board, HttpSession session) throws SQLException {
		log.debug(">>> BoardController - create()");
		MemberDto member = (MemberDto) session.getAttribute("member");
		log.debug(member.toString());
		board.setUserId(member.getUserId());
		log.debug(board.toString());
		boardService.registerArticle(board);
		return "redirect:/board/";
	}
	
	
	@GetMapping("/modify/{no}")
	public String viewModify(@PathVariable("no") Integer articleNo, Model model) throws SQLException {
		log.debug(">>> BoardController - viewModify"); 
		BoardDto board = boardService.viewArticle(articleNo);
		model.addAttribute("board", board);
		return "/board/modify";
	}
	
	
	/**
	 * 화면(form)에서 전송된 데이터를 기반으로 modify
	 * 
	 * @future_work PUT, PATCH등으로 RESTful하게 수정
	 * @param articleNo
	 * @return
	 */
	@PostMapping("/modify")
	public String modify(@ModelAttribute BoardDto board) throws SQLException {
		log.debug(">>> BoardController - modify");
		boardService.modifyArticle(board);
		return "redirect:/board/";
	}
	
	/**
	 * 화면(form)에서 전송된 데이터를 기반으로 delete
	 * @future_work DELETE로 RESTful하게 수정
	 * @param articleNo
	 * @return
	 */
	/*
	 * @PostMapping("/delete/{no}") public String delete(@PathVariable("no") Integer
	 * articleNo) { log.debug(">>> BoardController - delete");
	 * boardService.deleteArticle(articleNo); return "redirect:board/list"; }
	 */
	
}
