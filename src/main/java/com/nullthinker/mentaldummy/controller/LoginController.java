package com.nullthinker.mentaldummy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nullthinker.mentaldummy.beans.User;
import com.nullthinker.mentaldummy.model.dao.MentalDummyDAO;
import com.nullthinker.mentaldummy.model.helper.SHAHashing;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private MentalDummyDAO mentalDummyDAO;

	@Autowired
	private Environment environment;

	@RequestMapping(value="/page",method=RequestMethod.GET)
	public String loginPage(){
		return "login";
	}
	
	
	@RequestMapping(value="/submit",method=RequestMethod.POST)
	public String login(@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model, 
			HttpSession httpSession) throws Exception{
		String redirect = "";
		User user = new User();
		user.setEmail(email);
		String hashPasswd = new SHAHashing().hashIt(password);
		user.setPassword(hashPasswd);
		user = mentalDummyDAO.login(user);
		if(user != null) {
			httpSession.setAttribute("user", user);
			if(user.getType().equals(environment.getRequiredProperty("admin"))) {
				//for admin
				redirect= "redirect:adminHome";
			}
			else if(user.getType().equals(environment.getRequiredProperty("normal"))) {
				//for normal
				redirect= "redirect:normalHome";
			}
			else if(user.getType().equals(environment.getRequiredProperty("examiner"))) {
				//for examiner
				redirect= "redirect:examinerHome";
			}
		}else {
			model.addAttribute("msg","Login failed. Please provide a valid email and password");
			redirect = "redirect:/login/page";
		}
		
		return redirect;
	}
}
