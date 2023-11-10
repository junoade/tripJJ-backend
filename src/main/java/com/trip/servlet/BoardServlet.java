package com.trip.servlet;
//package com.trip.controller;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.trip.board.BoardDto;
//import com.trip.board.service.BoardService;
//import com.trip.board.service.BoardServiceImpl;
//import com.trip.member.MemberDto;
//import com.trip.prevUtil.Sort;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@WebServlet("/board")
//public class BoardServlet extends HttpServlet {
//	
//	private static BoardService service = BoardServiceImpl.getInstance();
//	
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        process(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	request.setCharacterEncoding("UTF-8");
//    	process(request, response);
//    }
//    
//    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	System.out.println(">>> /board 요청 처리");
//    	String action = request.getParameter("action");
//    	try {
//			if ("write".equals(action)) {
//				write(request, response);
//			} else if ("list".equals(action)) {
//				list(request, response);
//			} else if ("detail".equals(action)) {
//				detail(request, response);
//			} else if ("modifyForm".equals(action)) {
//				modifyForm(request, response);
//			} else if ("modify".equals(action)) {
//				modify(request, response);
//			} else if ("delete".equals(action)) {
//				delete(request, response);
//			} 
//			
//			// 231006 구현
//			else if("sortBy".equals(action)) {
//				sortBy(request, response);
//			}
//			// Future Work
//    		/*case "recent":
//				recent(request, response);
//			break;
//			case "top":
//				top(request, response);
//			break;*/
//			
//    	} catch(Exception e) {
//    		e.printStackTrace();
//    	}
//    }
//       
//    /**
//     * 게시판 이동하면 list() -> list.jsp로 이동하도록 구현
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	// board 
//    	List<BoardDto> boards = service.searchListAll();
//    	
//    	request.setAttribute("boards", boards);
//    	request.getRequestDispatcher("/board/list.jsp").forward(request, response);
//    }
//    
//    /**
//     * 게시글 등록
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    private void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	// board 
//    	System.out.println(">> write");
//    	
//    	HttpSession session = request.getSession();
//    	MemberDto member = (MemberDto)session.getAttribute("member");
//    	
//    	if(member == null) {
//    		String failedMsg = "로그인 필수!";
//    		request.setAttribute("msg", failedMsg);
//    		request.getRequestDispatcher("/board/list.jsp");
//    		return;
//    	}
//    	
//    	// attribute가 아니라 파라미터인데 위에서 세션에서 가져올 땐 getAttribute로 가져와서 왜 잘못되었는지 몰랐다..!
//    	BoardDto dto = new BoardDto();
//    	dto.setSubject(request.getParameter("title"));
//    	dto.setUserId(member.getUserId());
//    	dto.setContent(request.getParameter("memo"));
//    	dto.setHit(0);
//    	
//    	System.out.println(dto);
//    	service.registerArticle(dto);
//    	
//    	// 서블릿이 데이터 담아서 뷰로 전달하도록
//    	response.sendRedirect(request.getContextPath() + "/board?action=list");
//    }
//    
//    /**
//     * 게시글 세부 정보 조회
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	// board 
//    	System.out.println(">>> detail 수행");
//    	int articleNo = Integer.parseInt(request.getParameter("articleNo"));
//    	System.out.println(articleNo);
//    	
//    	BoardDto dto = service.viewArticle(articleNo);
//    	
//    	request.setAttribute("board", dto);
//    	request.getRequestDispatcher("/board/detail.jsp").forward(request, response);
//    }
//    
//    /**
//     * 게시글 수정 페이지 이동
//     * @param request 
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    private void modifyForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	// board 
//    	System.out.println(">>> modifyForm 수행");
//    	int articleNo = Integer.parseInt(request.getParameter("articleNo"));
//    	
//    	BoardDto dto = service.viewArticle(articleNo);
//    	
//    	request.setAttribute("board", dto);
//    	request.getRequestDispatcher("/board/modify.jsp").forward(request, response);
//    }
//    
//    /**
//     * 게시글 수정 - service
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	// board 
//    	System.out.println(">>> modify 수행");
//    	int articleNo = Integer.parseInt(request.getParameter("articleNo"));
//    	
//    	BoardDto dto = service.viewArticle(articleNo);
//    	dto.setSubject(request.getParameter("title"));
//    	dto.setContent(request.getParameter("content"));
//    	
//    	service.modifyArticle(dto);
//    	
//    	response.sendRedirect(request.getContextPath() + "/board?action=detail&articleNo=" + articleNo);
//    }
//    
//    /**
//     * 게시글 삭제
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	// board 
//    	System.out.println(">>> delete 수행");
//    	int articleNo = Integer.parseInt(request.getParameter("articleNo"));
//    	
//    	service.deleteArticle(articleNo);
//    	
//    	response.sendRedirect(request.getContextPath() + "/board?action=list");
//    }
//    
///*    private void recent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	// board 
//    	System.out.println(">>> recent 수행");
//    	int articleNo = Integer.parseInt(request.getParameter("articleNo"));
//    	
//    	BoardDto dto = service.viewArticle(articleNo);
//    	
//    	request.setAttribute("board", dto);
//    	request.getRequestDispatcher("/board/detail.jsp").forward(request, response);
//    }
//    
//    private void top(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	// board 
//    	System.out.println(">>> top 수행");
//    	int articleNo = Integer.parseInt(request.getParameter("articleNo"));
//    	
//    	BoardDto dto = service.viewArticle(articleNo);
//    	
//    	request.setAttribute("board", dto);
//    	request.getRequestDispatcher("/board/detail.jsp").forward(request, response);
//    }*/
//    
//    /**
//     * 2023-10-06 게시판 정렬 구현
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    private void sortBy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	
//    	String sortBy = request.getParameter("sortBy");
//    	System.out.println(">>> sort 수행 param :" + sortBy);
//    	
//    	List<BoardDto> boards = service.searchListAll();
//    	List<BoardDto> sorted = null;
//    	try {
//			sorted = Sort.sort(boards, sortBy);
//		} catch (Exception e) {
//			String failedMsg = "올바르지 않은 정렬 방식입니다.";
//			request.setAttribute("msg", failedMsg);
//			request.getRequestDispatcher("/board/list").forward(request, response);
//			e.printStackTrace();
//		}
//    	
//    	request.setAttribute("boards", sorted);
//    	request.getRequestDispatcher("/board/list.jsp").forward(request, response);
//    }
//}
