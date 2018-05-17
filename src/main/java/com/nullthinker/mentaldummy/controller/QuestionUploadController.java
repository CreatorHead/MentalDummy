package com.nullthinker.mentaldummy.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import com.nullthinker.mentaldummy.mode.services.ApachePOIExcelRead;
import com.nullthinker.mentaldummy.mode.services.MimeValidation;

@Controller @PropertySource(value = { "classpath:fileUpload.properties" })
public class QuestionUploadController {
	
	@Autowired private Environment environment;
//	@Autowired private MimeValidation mimeValidation;
	@Autowired private ApachePOIExcelRead apachePOIExcelRead;
	
	@RequestMapping(value="upload/questions",method=RequestMethod.GET)
	public String uploadFile(){
		return "fileUpload";
	}


	@RequestMapping(value="upload/submit/questions",method=RequestMethod.POST)
	public String processFile(@RequestPart("file") Part file, Model model,
			@RequestParam("subject")String subject,@RequestParam("duration") Integer duration,
			@RequestParam("passMarks") Double passMarks,@RequestParam("diffLvl")String diffLevel,
			@RequestParam("passkey") String passKey
			) throws Exception{
		String state = "";
		try {
			String filePath = environment.getRequiredProperty("uploadLocation")+"/"+file.getSubmittedFileName();
//			System.out.println(filePath);
			
			//to write a file in a defaut mutipart location
			file.write(file.getSubmittedFileName());
			
			StringBuilder csvFormat = apachePOIExcelRead.reader(filePath);
			System.out.println(csvFormat);
			//converting input stream to bytes
//			byte[] fileBytes = FileUtils.readFileToByteArray(new File(filePath));
			
			//to delete file
		    File fileToDelete = FileUtils.getFile(filePath);
		    boolean success = FileUtils.deleteQuietly(fileToDelete);
			
			//checking the Mime Type
//			String mimeType = mimeValidation.getMimeType(fileBytes);
//			System.out.println(mimeType);
			
			ArrayList<String> mimeList = new ArrayList<>();
			mimeList.add("application/vnd.ms-excel");
			mimeList.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			
			//TODO File Validation
//			if(!(mimeList.contains(mimeType))) {
//				state = "Only Spreadsheets are supported";
//				model.addAttribute("msg",state);
//				return "redirect:../questions";
//			}
			
			state = "success";
		} catch (IOException e) {
			e.printStackTrace();
			state = "failed";
		}
		model.addAttribute("msg",state);
		return "errMsg";
	}
}
