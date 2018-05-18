package com.nullthinker.mentaldummy.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
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

@Controller @PropertySource(value = { "classpath:fileUpload.properties" })
public class QuestionUploadController {
	
	@Autowired private Environment environment;
	@Autowired private ApachePOIExcelRead apachePOIExcelRead;
	
//	@RequestMapping(value="upload/questions",method=RequestMethod.GET)
//	public String uploadFile(){
//		return "fileUpload";
//	}


	@RequestMapping(value="upload/submit/questions",method=RequestMethod.POST)
	public String processFile(@RequestPart("file") Part file, Model model,
			@RequestParam("subject")String subject,@RequestParam("duration") Integer duration,
			@RequestParam("passMarks") Double passMarks,@RequestParam("diffLvl")String diffLevel,
			@RequestParam("passkey") String passKey
			){
		String state = "";
		try {
			String filePath = environment.getRequiredProperty("uploadLocation")+"/"+file.getSubmittedFileName();
			
			//to write a file in a defaut mutipart location
			file.write(file.getSubmittedFileName());
			
			
			try {
				List<StringBuilder> csvFormatList = apachePOIExcelRead.reader(filePath);
			} catch (Exception e) {
				e.printStackTrace();
				state = e.getMessage();
				model.addAttribute("msg",state);
				return "redirect:../questions";
			}
			
			//converting input stream to bytes
//			byte[] fileBytes = FileUtils.readFileToByteArray(new File(filePath));
			
			//Get the content type
//			String mimeType = Files.probeContentType(Paths.get(filePath));
//			System.out.println(mimeType);
			
			//checking the Mime Type
//			String mimeType = mimeValidation.getMimeType(fileBytes);
//			System.out.println(mimeType);

			//to delete file
		    File fileToDelete = FileUtils.getFile(filePath);
		    FileUtils.deleteQuietly(fileToDelete);
			
			
			ArrayList<String> mimeList = new ArrayList<>();
			mimeList.add("application/vnd.ms-excel");
			mimeList.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			
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
