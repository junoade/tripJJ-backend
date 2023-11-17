package com.trip.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trip.attraction.HotplaceDto;
import com.trip.attraction.service.AttractionService;
import com.trip.member.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/attraction")
public class AttractionController {

	@Autowired
	private AttractionService service;

	@GetMapping("/list")
	public String searchByArea(@RequestParam("area") String area, Model model) {
		log.debug(">>> AttractionController - searchByArea()");
		model.addAttribute("area", area);
		return "attraction/list";
	}

	@GetMapping("/")
	public String searchAllHotplace(HttpSession session, Model model) {
		log.debug(">>> AttractionController - searchAllHotplace()");
		MemberDto member = (MemberDto) session.getAttribute("member");
		List<HotplaceDto> list = service.hotPlaceList(member.getUserId());
		model.addAttribute("hotplaces", list);
		log.debug(list.toString());
		return "savedplace";
	}
	
	@GetMapping("/regist")
	public String regist() {
		return "myplace";
	}

	@PostMapping("/")
	public String save(@RequestParam Map<String, String> map, HttpSession session, Model model) {
		log.debug(">>> AttractionController - save()");
		log.debug(map.toString());
		MemberDto member = (MemberDto) session.getAttribute("member");
		
		HotplaceDto dto = new HotplaceDto();
		dto.setUserId(member.getUserId());
		dto.setPlaceName(map.get("hotPlaceName"));
		dto.setVisitedDate(map.get("hotPlaceDate"));
		dto.setDetail(map.get("memo"));

		if (service.saveHotplace(dto)==1) {
			// request.setAttribute("msg", "핫플레이스 저장 완료");
			return "redirect:/attraction/"; // GET MAPPING 과 연결
		} else {
			String failedMsg = "핫플레이스 등록에 실패했습니다. 입력 폼을 확인해주세요.";
			model.addAttribute("msg", failedMsg);
			return "myplace";
		}
	}
}
