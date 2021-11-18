package com.yazbyz.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 어떤 곳에서 exception이 발생하던 이 클래스에서 처리한다.
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value=IllegalArgumentException.class) // 특정 exception 명시 
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + " </h1>";
	}
}
