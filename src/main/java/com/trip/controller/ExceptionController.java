package com.trip.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Model m, Exception e) {
		m.addAttribute("msg", e.getMessage());
		e.printStackTrace();
		
		return "error/500";
	}
}
