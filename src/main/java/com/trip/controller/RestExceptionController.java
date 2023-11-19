package com.trip.controller;

import com.trip.security.exception.UnAuthroizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionController {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionHandler(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Exception: " + e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
	}

	@ExceptionHandler(UnAuthroizedException.class)
	public ResponseEntity<?> unAuthroized(UnAuthroizedException e) {
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
