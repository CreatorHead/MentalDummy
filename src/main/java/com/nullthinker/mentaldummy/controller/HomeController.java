package com.nullthinker.mentaldummy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value="/user/home",method=RequestMethod.GET)
	public String userHome() {
		return "HomeUser";
	}
	 
	@RequestMapping(value="/examiner/home",method=RequestMethod.GET)
	public String examinerHome() {
		return "HomeExaminer";
	}
}
