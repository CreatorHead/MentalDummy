package com.nullthinker.mentaldummy.controller;

import java.io.IOException;

import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

@Controller
@RequestMapping("/upload")
public class FileUpload {
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public String uploadFile(){
		return "fileUpload";
	}
	
	
	@RequestMapping(value="/submit",method=RequestMethod.POST)
	public String processFile(@RequestPart("file") Part file, Model model){
		String state = "";
		try {
			String type = file.getContentType();
			System.out.println(type);
			file.write(file.getSubmittedFileName());
			
			state = "success";
		} catch (IOException e) {
			e.printStackTrace();
			state = "failed";
		}
		model.addAttribute("msg",state);
		return "msg";
	}
}
