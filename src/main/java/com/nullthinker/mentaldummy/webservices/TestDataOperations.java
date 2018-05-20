package com.nullthinker.mentaldummy.webservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.nullthinker.mentaldummy.beans.Subject;
import com.nullthinker.mentaldummy.model.dao.DAO;

@Path("test")
public class TestDataOperations {
	
	@Autowired
	private DAO db;
	
	@Path("subjectsData") 
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Subject> getSubjects(){
		return db.getSubjects();
		
	}
	
}
