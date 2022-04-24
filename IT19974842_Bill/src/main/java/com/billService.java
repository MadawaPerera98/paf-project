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

import model.bill;



@Path("/bill")
public class billService {

	bill bObj = new bill();
	@GET
	@Path("/bill")
	@Produces(MediaType.TEXT_HTML)
	public String readBill()
	{
		 return bObj.readBill();
	}
	
	
	//INSERT DATA - POST METHOD
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertBill(@FormParam("billid") String billid,
		@FormParam("accno") String accno,
		@FormParam("musage") String musage,
		@FormParam("totbill") String totbill,
		@FormParam("cname") String cname)
		{
			String output = bObj.insertBill(billid, accno,musage, totbill,cname);
			return output;
		}
		
		//read
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readBill1()
		{
			return bObj.readBill();
		}
		
		//UPDATE - GET METHOD
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateBill(String bData)
		{
				
			//Convert the input string to a JSON object
			JsonObject billObject = new JsonParser().parse(bData).getAsJsonObject();
			 
			 
			//Read the values from the JSON object
			 String billid = billObject.get("billid").getAsString();
			 String accno = billObject.get("accno").getAsString();
			 String musage = billObject.get("musage").getAsString();
			 String totbill = billObject.get("totbill").getAsString();
			 String cname = billObject.get("cname").getAsString();
			 String output = bObj.updateBill (billid,accno,musage,totbill,cname);
			 return output;
		}
		

		//DELETE DATA
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteBill(String bData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(bData, "", Parser.xmlParser());

		//Read the value from the element <id>
		 String id = doc.select("billid").text();
		 String output = bObj.deleteBill(id);
		return output;
		}
		
		
}























