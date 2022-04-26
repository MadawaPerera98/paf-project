package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class user {

	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName,username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hashi", "root", "");
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
		 return con;
		 }
		
		
		//insert inq
		
		public String insertUser(String userid, String name, String tp, String address,String accno)
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
				String query = " insert into user ('userid','name','tp','address','accno')" + " values (?, ?, ?, ?,?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, userid);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, tp);
				preparedStmt.setString(4, address);
				preparedStmt.setString(5, accno);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "User Inserted successfully";
			}
			catch (Exception e)
			{
				output = "Error while inserting the User.";
				System.err.println(e.getMessage());
			}
		 return output;
		 }
		
		
		//read
		
		public String readUser()
		 {
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}
		 
				// Prepare the HTML table to be displayed
				output = "<table border='1'><tr><th>User ID</th><th>Name</th>" +"<th>Telephone Number</th>" +"<th>Address</th>"+"<th>Account Number</th>";
		
				String query = "select * from user";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
		 
		 
		 
				// iterate through the rows in the result set
				while (rs.next())
				{
		
					String userid = rs.getString("userid");
					String name = rs.getString("name");
					String tp = rs.getString("tp");
					String address = rs.getString("address");
					String accno = rs.getString("accno");
		 
					// Add into the HTML table
					output += "<tr><td>" + userid + "</td>";
					output += "<td>" + name + "</td>";
					output += "<td>" + tp + "</td>";
					output += "<td>" + address + "</td>";
					output += "<td>" + accno + "</td>";
					// buttons
					output += "<td> <input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='user.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>" + "</form></td></tr>";
				}
				con.close();
				// Complete the HTML table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the user.";
				System.err.println(e.getMessage());
			}
			return output;
		 }
		
		
		//update
		public String updateUser(String userid, String name, String tp, String address,String accno)
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
				String query = "UPDATE user SET name=?,tp=?,address=?,accno=? WHERE userid=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, userid);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, tp);
				preparedStmt.setString(4, address);
				preparedStmt.setString(5, accno);
				
		 
		 
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "User Updated successfully";
			}
			catch (Exception e)
			{
				output = "Error while updating the User.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		public String deleteUser(String userid)
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
				String query = "delete from user where userid=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, userid);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully User";
			}
			catch (Exception e)
			{
				output = "Error while deleting the User.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
}





























