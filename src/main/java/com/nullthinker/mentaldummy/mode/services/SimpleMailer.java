package com.nullthinker.mentaldummy.mode.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service("mailer")
public class SimpleMailer {
	
	@Autowired
	private Environment environment;
	
	public boolean sendMessage(String username, String password, String to,String sub, String msg){
		Properties prop = new Properties();
		prop.put("mail.smtp.auth",environment.getRequiredProperty("mail.smtp.auth"));
		prop.put("mail.smtp.starttls.enable",environment.getRequiredProperty("mail.smtp.starttls.enable"));
		prop.put("mail.smtp.host",environment.getRequiredProperty("mail.smtp.host"));
		prop.put("mail.smtp.port",environment.getRequiredProperty("mail.smtp.port"));
		boolean state = false;
		Session session = Session.getInstance(prop,	new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username, password);
			}
		});
		System.out.println(session.getClass());
		
		try{
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(sub);
			message.setText(msg);
			Transport.send(message);
			System.out.println("Message was Sent");
			state = true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return state;
	}

}
