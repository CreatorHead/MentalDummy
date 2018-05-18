package com.nullthinker.mentaldummy.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ex.printStackTrace();
		ModelAndView model = new ModelAndView("errMsg");
		return model;
	}
	
}