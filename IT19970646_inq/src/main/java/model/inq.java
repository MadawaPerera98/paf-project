package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class inq {

	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName,username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/nawod", "root", "");
	 }
	 catch (Exception e)
	 {
		 e.printStackTrace();
	 }
	 return con;
	 }

	//insert inq
	
	public String insertInq(String inqid, String head, String desc, String date)
	 {
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into inquire ('inqid','head','desc','date')" + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, inqid);
			preparedStmt.setString(2, head);
			preparedStmt.setString(3, desc);
			preparedStmt.setString(4, date);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inquire Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the Inquire.";
			System.err.println(e.getMessage());
		}
	 return output;
	 }
	
	//read
	
		public String readInq()
		 {
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}
		 
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Inquire ID</th><th>Inquire Heading</th>" +"<th>Description</th>" +"<th>Date</th>";
		
				String query = "select * from inquire";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
		 
		 
		 
				// iterate through the rows in the result set
				while (rs.next())
				{
		
					String id = rs.getString("inqid");
					String head = rs.getString("head");
					String desc = rs.getString("desc");
					String date = rs.getString("date");
		 
					// Add into the HTML table
					output += "<tr><td>" + id + "</td>";
					output += "<td>" + head + "</td>";
					output += "<td>" + desc + "</td>";
					output += "<td>" + date + "</td>";
					// buttons
					output += "<td> <input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='inq.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>" + "</form></td></tr>";
				}
				con.close();
				// Complete the HTML table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the Inquire.";
				System.err.println(e.getMessage());
			}
			return output;
		 }
		
		//update
		public String updateInq(String inqid, String head, String desc, String date)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for updating."; 
				}
		 
		 
				// create a prepared statement update
				String query = "UPDATE inquire SET head=?,desc=?,date=? WHERE inqid=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, inqid);
				preparedStmt.setString(2, head);
				preparedStmt.setString(3, desc);
				preparedStmt.setString(4, date);
		 
		 
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			}
			catch (Exception e)
			{
				output = "Error while updating the Inquire.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		//delete
		public String deleteInq(String inqid)
		 {
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for deleting."; 
				}
		 
		 
				// create a prepared statement for delete 
				String query = "delete from inquire where inqid=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, inqid);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully Inquire";
			}
			catch (Exception e)
			{
				output = "Error while deleting the Inquire.";
				System.err.println(e.getMessage());
			}
			return output;
		}


	
}
