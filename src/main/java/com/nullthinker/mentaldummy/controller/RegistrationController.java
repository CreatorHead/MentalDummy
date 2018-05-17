package com.nullthinker.mentaldummy.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import com.nullthinker.mentaldummy.beans.User;
import com.nullthinker.mentaldummy.beans.UserConfirmationDetails;
import com.nullthinker.mentaldummy.mode.services.SHAHashing;
import com.nullthinker.mentaldummy.mode.services.SimpleMailer;
import com.nullthinker.mentaldummy.model.dao.DAO;

/**
 * This Controller is Responsible for profile creation and profile page
 * @author Userr
 *
 */
@Controller
@PropertySource(value = { "classpath:mailer.properties" })
public class RegistrationController {

	@Autowired
	private DAO mentalDummyDAO;
	@Autowired
	private Environment env;
	@Autowired
	private SimpleMailer mailer;

	/**
	 * To show the Registration Page
	 * @return
	 */
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String registerPage(){
		return "signup";
	}

	/**;
	 * For Registration of User data after Submission 
	 * @param user
	 * @param pswrepeat
	 * @param model
	 * @param request
	 * @return 
	 */
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

		List<String> roles = new ArrayList<>();
		roles.add("user");
		user.setRoles(roles);
		user.setCreationDate(new Date());

		if(user.getPassword().equals(pswrepeat)) {
			String hashPasswd = "";
			Zxcvbn passwdCheck = new Zxcvbn();
			Strength strength = passwdCheck.measure(user.getPassword());

			if(! (strength.getScore() >= 1) ) {
				model.addAttribute("msg", "Your password is too weak. Choose a stronger one.");
				return "redirect:/signup";
			}
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
			String text = env.getRequiredProperty("mail.text")+ appUrl + "/MentalDummy/confirm?token="+user.getConfirmationDetails().getConfirmationToken();
			String subject = env.getRequiredProperty("mail.subject");
			String from = env.getRequiredProperty("mail.from");
			String password = env.getRequiredProperty("mail.password");

			//Send the mail to the user
			try {
				mailer.sendMessage(from, password, mailTo, subject, text);
			} catch (MessagingException e) {
				e.printStackTrace();
				model.addAttribute("msg", "Something went wrong, please sign-up again");
				return "redirect:/signup";
			}

			//Inserting User data in database
			user = mentalDummyDAO.createProfile(user);

			if(user != null) {
				model.addAttribute("msg", "Profile creation succesfull. Please verify your account from your email");
			}else {
				model.addAttribute("msg", "Something went wrong, please sign-up again");
				return "redirect:/signup";
			}
		}else {
			model.addAttribute("msg", "Password mismatch");
			return "redirect:/signup";
		}
		return "redirect:/login"; 
	}

	@RequestMapping(value="/confirm", method=RequestMethod.GET)
	public String confirm(@RequestParam("token") String token,
			Model model) {
		User user = mentalDummyDAO.findByConfirmationToken(token);
		System.out.println(user);
		if(user != null) {
			//enabling the user
			user.getConfirmationDetails().setEnabled(true);

			//marking the user as active
			user.setIsActive(true);
			//updating the confirmation_token by adding _confirmed
			user.getConfirmationDetails().setConfirmationToken(user.getConfirmationDetails().getConfirmationToken()+"_confirmed");
			user = mentalDummyDAO.updateUser(user);
			if(user != null) {
				model.addAttribute("msg","Your profile is now active, Please login to your profile");
				return "redirect:/login";
			}else {
				model.addAttribute("msg","Unable to activate your profile");
				return "redirect:/login";
			}
		}model.addAttribute("msg","Oops!  This is an invalid confirmation link. Please Create a Profile");
		return "redirect:/signup";
	}
}
