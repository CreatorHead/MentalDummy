package com.nullthinker.mentaldummy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nullthinker.mentaldummy.beans.User;
import com.nullthinker.mentaldummy.mode.services.SHAHashing;
import com.nullthinker.mentaldummy.model.dao.DAO;

@Controller
@RequestMapping("/signup")
@PropertySource(value = { "classpath:user_types.properties" })
public class RegistrationController {

	@Autowired
	private DAO mentalDummyDAO;

	@RequestMapping(value="/page",method=RequestMethod.GET)
	public String registerPage(){
		return "signup";
	}

	@RequestMapping(value="/submit",method=RequestMethod.POST)
	public String register(User user,
			@RequestParam("passwordrepeat")String pswrepeat,
			Model model)
	{	
		user.setType("normal");
		if(user.getPassword().equals(pswrepeat)) {
			String hashPasswd = "";
			try {
				hashPasswd = new SHAHashing().hashIt(pswrepeat);
			} catch (Exception e) {
				e.printStackTrace();
			}
			user.setPassword(hashPasswd);
			user = mentalDummyDAO.createProfile(user);
			if(user != null) {
				model.addAttribute("msg", "Profile Creation Succesfull. Please Login wiht Your Credentials");
			}else {
				model.addAttribute("msg", "Profile Creation UnSuccesfull");
			}
		}
		return "redirect:/login/page"; 
	}
}
