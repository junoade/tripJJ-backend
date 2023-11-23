package com.trip.member;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trip.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class MemberController {

	@Autowired
	MemberService memberService;
	
//	로그인 페이지로 이동
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
//	로그인
	@PostMapping("/login")
	public String login(MemberDto member, HttpSession session, Model model) throws Exception {
		log.debug(member.toString());
		String id = member.getUserId();
		String password = member.getUserPass();
		if(!id.isEmpty() && !password.isEmpty()) {
			MemberDto loginMember = memberService.login(id, password);
			if(loginMember!=null) {
				log.debug("로그인 : " + loginMember.toString());
				session.setAttribute("member", loginMember);
				return "redirect:/";
			}
		}
		String failedMsg = "로그인 실패! 아이디 또는 비밀번호를 확인해주세요"; // js에 보내서 alert() 창에 띄울 msg
		model.addAttribute("msg", failedMsg);
		return "login";
	}
	
//	로그 아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
//	회원 가입 페이지로 이동
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
//	회원 가입
	@PostMapping("/register")
	public String register(@ModelAttribute MemberDto member, Model model) throws Exception {
		String id = member.getUserId();
		String password = member.getUserPass();
		String name = member.getUserName();
		String email = member.getUserEmail();
		log.debug("회원 등록 : " + member.toString());
		if(!id.isEmpty() && !password.isEmpty() && !name.isEmpty() && !email.isEmpty()) {
			MemberDto loginMember = memberService.login(id, password);
			if(loginMember==null) {
				memberService.registerMember(member);
				return "redirect:/";
			}
		}
		String failedMsg = "회원가입 실패! 입력 폼을 확인해주세요!"; // js에 보내서 alert() 창에 띄울 msg
		model.addAttribute("msg", failedMsg);
		return "register";
	}
	
//	회원 정보 페이지로 이동
	@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
	}
	
//	회원 정보 수정
	@PostMapping("/memberUpdate")
	public String memberUpdate(@ModelAttribute MemberDto member, @RequestParam String newPass, @RequestParam String newPass2
			, Model model, HttpSession session) {
		log.debug(member.toString());
		final MemberDto loginMember = (MemberDto) session.getAttribute("member");
		if(!member.getUserPass().isEmpty() && !newPass.isEmpty() && !newPass2.isEmpty() &&
				BCrypt.checkpw(member.getUserPass(), loginMember.getUserPass()) && newPass.equals(newPass2)) {
			member.setUserPass(BCrypt.hashpw(newPass, BCrypt.gensalt()));
			memberService.modifyMember(member);
			session.setAttribute("member", member);
			log.debug("수정 완료! " + member.toString());
			model.addAttribute("msg", "회원정보 수정 성공!");
			return "redirect:/";
		}
		
		log.debug("수정 실패! " + member.toString());
		model.addAttribute("msg", "회원정보 수정 실패! 잘못된 값을 입력하였습니다.");
		return "mypage";
	}
	
//	회원 탈퇴
	@PostMapping("/memberDelete")
	public String memberDelete(HttpSession session) {
		MemberDto member = (MemberDto) session.getAttribute("member");
		memberService.deleteMember(member.getUserId());
		session.invalidate();
		return "redirect:/";
	}
}
