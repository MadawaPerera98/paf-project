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

import model.payment;

@Path("/payment")
public class paymentService {
	
	payment pObj = new payment();
	@GET
	@Path("/payment")
	@Produces(MediaType.TEXT_HTML)
	public String readfunds()
	{
		 return pObj.readPayment();
	}
	
	//INSERT DATA - POST METHOD
			@POST
			@Path("/")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces(MediaType.TEXT_PLAIN)
			public String insertInq(@FormParam("pid") String pid,
			@FormParam("ptype") String ptype,
			@FormParam("crdno") String crdno,
			@FormParam("date") String date,
			@FormParam("cvn") String cvn)
			{
				String output = pObj.insertPayment(pid, ptype,crdno, date,cvn);
				return output;
			}
			
			
			//read
			@GET
			@Path("/")
			@Produces(MediaType.TEXT_HTML)
			public String readfund()
			{
				return pObj.readPayment();
			}
			
			
			//UPDATE - GET METHOD
			@PUT
			@Path("/")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String updatePayment(String pData)
			{
					
				//Convert the input string to a JSON object
				JsonObject paymentObject = new JsonParser().parse(pData).getAsJsonObject();
				 
				 
				//Read the values from the JSON object
				 String pid = paymentObject.get("pid").getAsString();
				 String ptype = paymentObject.get("ptype").getAsString();
				 String crdno = paymentObject.get("crdno").getAsString();
				 String date = paymentObject.get("date").getAsString();
				 String cvn = paymentObject.get("cvn").getAsString();
				 String output = pObj.updatePayment (pid,ptype,crdno,date,cvn);
				 return output;
			}
			
			//DELETE DATA
			@DELETE
			@Path("/")
			@Consumes(MediaType.APPLICATION_XML)
			@Produces(MediaType.TEXT_PLAIN)
			public String deletePayment(String pData)
			{
			//Convert the input string to an XML document
			 Document doc = Jsoup.parse(pData, "", Parser.xmlParser());

			//Read the value from the element <id>
			 String id = doc.select("pid").text();
			 String output = pObj.deletePayment(id);
			return output;
			}

}
























