package com.nullthinker.mentaldummy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nullthinker.mentaldummy.model.dao.DAO;

@Controller
public class SubjectDetailsController {
	
	@Autowired
	private DAO mentalDummyDAO;
	
	@RequestMapping(value="/getSubjects",method=RequestMethod.GET)
	@ResponseBody
	public String registerPage(){
		return mentalDummyDAO.getSubjects().toString();
	}
}
