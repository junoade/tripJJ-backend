//package com.trip.servlet;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.trip.attraction.HotplaceDto;
//import com.trip.attraction.service.AttractionService;
//import com.trip.attraction.service.AttractionServiceImpl;
//import com.trip.member.MemberDto;
//
//
///**
// * Servlet implementation class AttractionServlet
// */
//@WebServlet("/attraction")
//public class AttractionServlet extends HttpServlet {
//       
//	AttractionService service = AttractionServiceImpl.getInstance();
//	
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		process(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		process(request, response);
//	}
//	
//	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println(">");
//		String action = request.getParameter("action");
//		try {
//			if ("save".equals(action)) {
//				save(request, response);
//			} else if ("searchAllHotplace".equals(action)) {
//				searchAllHotplace(request, response);
//			} else if ("delete".equals(action)) {
//				deletePlace(request, response);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HotplaceDto dto = new HotplaceDto();
//		
//		HttpSession session = request.getSession();
//		
//		MemberDto currentMember = (MemberDto)session.getAttribute("member");
//		dto.setUserId(currentMember.getUserId());
//		dto.setPlaceName(request.getParameter("hotPlaceName"));
//		dto.setVisitedDate(request.getParameter("hotPlaceDate"));
//		dto.setDetail(request.getParameter("memo"));
//		
//		if(service.saveHotplace(dto)) {
//			request.setAttribute("msg","핫플레이스 저장 완료");
//			request.getRequestDispatcher("/myplace.jsp").forward(request, response);
//		} else {
//			String failedMsg = "핫플레이스 등록에 실패했습니다. 입력 폼을 확인해주세요.";
//			request.setAttribute("msg", failedMsg);
//			request.getRequestDispatcher("/myplace.jsp").forward(request, response);
//		}
//
//	}
//	
//	private void searchAllHotplace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
//		HttpSession session = request.getSession();
//		MemberDto currentMember = (MemberDto)session.getAttribute("member");
//		String userId = currentMember.getUserId();
//		
//		
//		List<HotplaceDto> list = service.hotPlaceList(userId);
//		
//		request.setAttribute("hotplaces", list);
//		request.getRequestDispatcher("/savedplace.jsp").forward(request, response);
//	}
//	private void deletePlace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String hId = request.getParameter("hId");
//		System.out.println(hId);
//		service.deleteHotPlace(hId);
//
//		request.setAttribute("msg","게시글 삭제 완료");
//		request.getRequestDispatcher("/attraction?action=searchAllHotplace").forward(request,response);
//
//	}
//
//}
