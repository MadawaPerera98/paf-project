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

import model.user;

@Path("/user")
public class userService {
	
	user uObj = new user();
	@GET
	@Path("/user")
	@Produces(MediaType.TEXT_HTML)
	public String readfunds()
	{
		 return uObj.readUser();
	}

	//INSERT DATA - POST METHOD
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(@FormParam("userid") String userid,
	@FormParam("name") String name,
	@FormParam("tp") String tp,
	@FormParam("address") String address,
	@FormParam("accno") String accno)
	{
		String output = uObj.insertUser(userid, name,tp, address,accno);
		return output;
	}
	
	
	//read
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser()
	{
		return uObj.readUser();
	}
	
	//UPDATE - GET METHOD
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String uData)
	{
			
		//Convert the input string to a JSON object
		JsonObject userObject = new JsonParser().parse(uData).getAsJsonObject();
		 
		 
		//Read the values from the JSON object
		 String userid = userObject.get("userid").getAsString();
		 String name = userObject.get("name").getAsString();
		 String tp = userObject.get("tp").getAsString();
		 String address = userObject.get("address").getAsString();
		 String accno = userObject.get("accno").getAsString();
		 String output = uObj.updateUser (userid,name,tp,address,accno);
		 return output;
	}
	
	//DELETE DATA
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String uData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(uData, "", Parser.xmlParser());

	//Read the value from the element <id>
	 String id = doc.select("userid").text();
	 String output = uObj.deleteUser(id);
	return output;
	}

	
}











