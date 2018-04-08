package com.nullthinker.metaldummy.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import com.nullthinker.mentaldummy.mode.services.SimpleMailer;

@Configuration
@PropertySource("classpath:mailer.properties")
public class MailSenderConfiguration {
	
	
	
//	@Bean(name="mailer")
//	@Scope(scopeName="prototype")
//	public SimpleMailer getMailer() {
//		
//		
////		
////		SimpleMailer mailer = new SimpleMailer();
////		mailer.setProps(prop);
////		
////		return mailer;
//	}
}
