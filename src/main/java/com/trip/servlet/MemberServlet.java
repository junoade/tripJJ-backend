package com.trip.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trip.member.MemberDto;
import com.trip.member.service.MemberService;
import com.trip.member.service.MemberServiceImpl;

import java.io.IOException;

public class MemberServlet extends HttpServlet {
	
//	MemberService memberService = MemberServiceImpl.getInstance();
//	
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        process(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	request.setCharacterEncoding("UTF-8");
//        process(request, response);
//    }
//    
//    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	System.out.println(">> 클라이언트 요청");
//    	String action = request.getParameter("action");
//    	try {
//			if ("login".equals(action)) {
//				login(request, response);
//			} else if ("logout".equals(action)) {
//				logout(request, response);
//			} else if ("register".equals(action)) {
//				regist(request, response);
//			} else if ("memberUpdate".equals(action)) {
//				update(request, response);
//			} else if ("memberDelete".equals(action)) {
//				delete(request, response);
//			}
//    		
//    	} catch (Exception e) {
//			request.setAttribute("msg","내부 에러가 발생했습니다. 잠시 후에 요청해주세요.");
//    		e.printStackTrace();
//    	}
//    }
//    
//    /**
//     * 로그인 처리
//     * @param request
//     * @param response
//     * @throws Exception 
//     */
//    private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
//    	String id = request.getParameter("id");
//    	String password = request.getParameter("password");
//    	
//    	// memberService, dao 이용하여 회원 정보 확인
//    	MemberDto member = memberService.login(id, password);
//    	
//    	if(member == null) {
//    		String failedMsg = "로그인 실패! 아이디 또는 비밀번호를 확인해주세요"; // js에 보내서 alert() 창에 띄울 msg
//    		// forward()
//    		request.setAttribute("msg", failedMsg);
//    		request.getRequestDispatcher("/login.jsp").forward(request, response);
//    		return;
//    	}
//    	
//    	// 세션 만들어주고 
//    	HttpSession session = request.getSession();
//    	session.setAttribute("member", member);
//    	response.sendRedirect(request.getContextPath() + "/index.jsp");
//    }
//    
//    /**
//     * 로그아웃 구현
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	HttpSession session = request.getSession();
//		session.invalidate();
//		response.sendRedirect(request.getContextPath() + "/index.jsp");
//    }
//    
//    /**
//     * 회원가입 구현
//     * @param request
//     * @param response
//     * @throws Exception 
//     */
//    private void regist(HttpServletRequest request, HttpServletResponse response) throws Exception {
//    	
//    	MemberDto dto = new MemberDto();
//    	dto.setUserId(request.getParameter("id"));
//    	dto.setUserPass(request.getParameter("password"));
//    	dto.setUserName(request.getParameter("name"));
//    	dto.setUserEmail(request.getParameter("email"));
//    	
//    	// 서비스, dao 이용
//    	memberService.registerMember(dto);
//    	
//    	// redirect
//    	response.sendRedirect(request.getContextPath() + "/index.jsp");
//    }
//    
//    /**
//     * 회원정보 수정
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	MemberDto dto = new MemberDto();
//    	
//    	String userId = request.getParameter("id");
//    	String userPass = request.getParameter("password");
//    	
//    	String newUserPass = request.getParameter("new_password");
//    	String newUserPassVerify = request.getParameter("new_password_verify");
//    	
//    	dto.setUserId(userId);
//    	dto.setUserPass(userPass);
//    	dto.setUserName(request.getParameter("name"));
//    	dto.setUserEmail(request.getParameter("email"));
//		System.out.println(dto);
//		
//		try {
//			if(memberService.authUser(userId, userPass) && newUserPass.equals(newUserPassVerify) ) {
//				memberService.modifyMember(dto);
//		    	
//		    	request.setAttribute("msg","회원 수정 완료");
//				request.getRequestDispatcher("/index.jsp").forward(request,response);
//			} else {
//				throw new Exception("[info] 사용자 회원정보수정 실패;");
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			String failedMsg = "회원정보 검증 실패, 현재 비밀번호를 확인해주세요"; // js에 보내서 alert() 창에 띄울 msg
//    		// forward()
//    		request.setAttribute("msg", failedMsg);
//    		request.getRequestDispatcher("/mypage.jsp").forward(request, response);
//			
//			e.printStackTrace();
//		}
//		
//    }
//    
//    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	String userId = request.getParameter("id");
//    	memberService.deleteMember(userId);
//		HttpSession session = request.getSession();
//		session.invalidate();
//
//		request.setAttribute("msg","회원 탈퇴 완료");
//		request.getRequestDispatcher("/index.jsp").forward(request,response);
//    }
}
