package com.nullthinker.mentaldummy.controller;


import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nullthinker.mentaldummy.beans.User;
import com.nullthinker.mentaldummy.beans.UserConfirmationDetails;
import com.nullthinker.mentaldummy.mode.services.SHAHashing;
import com.nullthinker.mentaldummy.mode.services.SimpleMailer;
import com.nullthinker.mentaldummy.model.dao.DAO;

@Controller
@PropertySource(value = { "classpath:user_types.properties","classpath:mailer.properties" })
public class RegistrationController {

	@Autowired
	private DAO mentalDummyDAO;
	@Autowired
	private Environment env;
	@Autowired
	private SimpleMailer mailer;

	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String registerPage(){
		return "signup";
	}

	@RequestMapping(value="/signup/submit",method=RequestMethod.POST)
	public String register( User user,
							@RequestParam("passwordrepeat")String pswrepeat,
							Model model,
							HttpServletRequest request
							)
	{	//Checking in db for the existing user with the same email
		User userExists = mentalDummyDAO.findByEmail(user);
		if(userExists != null) {
			//email id is present, send an error message
			model.addAttribute("msg","There is already a user registered with the email provided");
			return "redirect:/signup";
		}
		
		user.setType("normal");
		user.setCreationDate(new Date());
		
		if(user.getPassword().equals(pswrepeat)) {
			String hashPasswd = "";
			try {
				hashPasswd = new SHAHashing().hashIt(pswrepeat);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//setting encrypted password
			user.setPassword(hashPasswd);
			
			UserConfirmationDetails confirmationDetails = new UserConfirmationDetails();
			// Generate random 36-character string token for confirmation link
			String uuid = UUID.randomUUID().toString();
			confirmationDetails.setConfirmationToken(uuid);
			
			//User will be enable if mail is confirmed
			confirmationDetails.setEnabled(false);
			
			//To set the user as inactive
			user.setIsActive(false);
			
			user.setConfirmationDetails(confirmationDetails);
			
			String appUrl = request.getScheme() + "://" + request.getServerName();
			
			String mailTo = user.getEmail();
			String text = env.getRequiredProperty("mail.text")+ appUrl + "/confirm?token="+user.getConfirmationDetails().getConfirmationToken();
			String subject = env.getRequiredProperty("mail.subject");
			String from = env.getRequiredProperty("mail.from");
			String password = env.getRequiredProperty("mail.password");
			
			mailer.sendMessage(from, password, mailTo, subject, text);
			user = mentalDummyDAO.createProfile(user);
			if(user != null) {
				model.addAttribute("msg", "Profile Creation Succesfull. Please Verify Your Account From Your Email");
			}else {
				model.addAttribute("msg", "Something Went Wrong, Please Sign-up Again");
				return "redirect:/signup";
			}
		}else {
			model.addAttribute("msg", "Password Mismatch");
			return "redirect:/signup";
		}
		return "redirect:/login"; 
	}
}
