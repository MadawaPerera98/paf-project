package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class payment {

	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName,username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/umangi", "root", "");
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
		 return con;
		 }
		
		//insert
		public String insertPayment(String pid, String ptype, String crdno, String date,String cvn)
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
				String query = " insert into payment ('pid','ptype','crdno','date','cvn')" + " values (?, ?, ?, ?,?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, pid);
				preparedStmt.setString(2, ptype);
				preparedStmt.setString(3, crdno);
				preparedStmt.setString(4, date);
				preparedStmt.setString(5, cvn);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Payment Inserted successfully";
			}
			catch (Exception e)
			{
				output = "Error while inserting the payment.";
				System.err.println(e.getMessage());
			}
		 return output;
		 }
		
		

		//read
		
			public String readPayment()
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
					output = "<table border='1'><tr><th>Payment ID</th><th>Payment Type</th>" +"<th>Card No</th>" +"<th>Date</th>"+"<th>CVN</th>";
			
					String query = "select * from payment";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
			 
			 
			 
					// iterate through the rows in the result set
					while (rs.next())
					{
			
						String pid = rs.getString("pid");
						String ptype = rs.getString("ptype");
						String crdno = rs.getString("crdno");
						String date = rs.getString("date");
						String cvn = rs.getString("cvn");
			 
						// Add into the HTML table
						output += "<tr><td>" + pid + "</td>";
						output += "<td>" + ptype + "</td>";
						output += "<td>" + crdno + "</td>";
						output += "<td>" + date + "</td>";
						output += "<td>" + cvn + "</td>";
						// buttons
						output += "<td> <input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='payment.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>" + "</form></td></tr>";
					}
					con.close();
					// Complete the HTML table
					output += "</table>";
				}
				catch (Exception e)
				{
					output = "Error while reading the payment.";
					System.err.println(e.getMessage());
				}
				return output;
			 }
		
			//update
			public String updatePayment(String pid, String ptype, String crdno, String date,String cvn)
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
					String query = "UPDATE payment SET ptype=?,crdno=?,date=?,cvn=? WHERE pid=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setString(1, pid);
					preparedStmt.setString(2, ptype);
					preparedStmt.setString(3, crdno);
					preparedStmt.setString(4, date);
					preparedStmt.setString(4, cvn);
					
			 
			 
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Payment Updated successfully";
				}
				catch (Exception e)
				{
					output = "Error while updating the Payment.";
					System.err.println(e.getMessage());
				}
				return output;
			}
		
			//delete
			public String deletePayment(String pid)
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
					String query = "delete from payment where pid=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setString(1, pid);
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Deleted successfully Payment";
				}
				catch (Exception e)
				{
					output = "Error while deleting the payment.";
					System.err.println(e.getMessage());
				}
				return output;
			}
}




























