package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.inq;

@Path("/inq")
public class inqService {

	inq inqObj = new inq();
	@GET
	@Path("/inq")
	@Produces(MediaType.TEXT_HTML)
	public String readfunds()
	{
		 return inqObj.readInq();
	}
	
	//INSERT DATA - POST METHOD
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertInq(@FormParam("inqid") String inqid,
		@FormParam("head") String head,
		@FormParam("desc") String desc,
		@FormParam("date") String date)
		{
		String output = inqObj.insertInq(inqid, head,desc, date);
		return output;
		}
	
		//read
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readfund()
		{
			return inqObj.readInq();
		}
		
		//UPDATE - GET METHOD
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateInq(String inqData)
		{
				
			//Convert the input string to a JSON object
			JsonObject inqObject = new JsonParser().parse(inqData).getAsJsonObject();
			 
			 
			//Read the values from the JSON object
			 String inqid = inqObject.get("inqid").getAsString();
			 String head = inqObject.get("head").getAsString();
			 String desc = inqObject.get("desc").getAsString();
			 String date = inqObject.get("date").getAsString();
			 String output = inqObj.updateInq (inqid , head, desc, date);
			 return output;
		}
		//DELETE DATA
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteInq(String inqData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(inqData, "", Parser.xmlParser());

		//Read the value from the element <id>
		 String id = doc.select("inqid").text();
		 String output = inqObj.deleteInq(id);
		return output;
		}
}

























