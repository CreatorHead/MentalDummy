package com.nullthinker.mentaldummy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nullthinker.mentaldummy.beans.User;
import com.nullthinker.mentaldummy.mode.services.SHAHashing;
import com.nullthinker.mentaldummy.model.dao.DAO;

@Controller
public class LoginController {
	@Autowired
	private DAO mentalDummyDAO;

//	@Autowired
//	private Environment environment;

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage(){
		return "login";
	}
	
	
	@RequestMapping(value="/login/submit",method=RequestMethod.POST)
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
			if(user.getConfirmationDetails().isEnabled()) {
				httpSession.setAttribute("user", user);
				if(user.getRoles().contains("admin")) {
					//for admin
					redirect= "redirect:../admin/home";
				}else if(user.getRoles().contains("examiner")) {
					//for examiner
					redirect= "redirect:../examiner/home";
				}else if(user.getRoles().contains("user")) {
					//for user
					redirect= "redirect:../user/home";
				}
			}else {
				model.addAttribute("msg","Please confirm your email");
				redirect = "redirect:/login";
			}
			
		}else {
			model.addAttribute("msg","Login failed. Please provide a valid email and password");
			redirect = "redirect:/login";
		}
		return redirect;
	}
}
