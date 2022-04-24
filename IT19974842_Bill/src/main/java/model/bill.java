package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class bill {

	//A common method to connect to the DB
			private Connection connect()
			 {
			 Connection con = null;
			 try
			 {
				 Class.forName("com.mysql.jdbc.Driver");

				 //Provide the correct details: DBServer/DBName,username, password
				 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/madawa", "root", "");
			 }
			 catch (Exception e)
			 {
				 e.printStackTrace();
			 }
			 return con;
			 }
			
			
			//insert bill 
			
			public String insertBill(String billid, String accno, String musage, String totbill,String cname)
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
					String query = " insert into bill ('billid','accno','musage','totbill','cname')" + " values (?, ?, ?, ?,?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setString(1, billid);
					preparedStmt.setString(2, accno);
					preparedStmt.setString(3, musage);
					preparedStmt.setString(4, totbill);
					preparedStmt.setString(5, cname);
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "EBill Inserted successfully";
				}
				catch (Exception e)
				{
					output = "Error while inserting the Bill.";
					System.err.println(e.getMessage());
				}
			 return output;
			 }
			
			
			//read
			
			public String readBill()
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
					output = "<table border='1'><tr><th>Bill ID</th><th>Account No</th>" +"<th>Monthly Usage</th>" +"<th>Total Bill</th>"+"<th>Customer Name</th>";
			
					String query = "select * from bill";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
			 
			 
			 
					// iterate through the rows in the result set
					while (rs.next())
					{
			
						String billid = rs.getString("billid");
						String accno = rs.getString("accno");
						String musage = rs.getString("musage");
						String totbill = rs.getString("totbill");
						String cname = rs.getString("cname");
			 
						// Add into the HTML table
						output += "<tr><td>" + billid + "</td>";
						output += "<td>" + accno + "</td>";
						output += "<td>" + musage + "</td>";
						output += "<td>" + totbill + "</td>";
						output += "<td>" + cname + "</td>";
						// buttons
						output += "<td> <input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='bill.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>" + "</form></td></tr>";
					}
					con.close();
					// Complete the HTML table
					output += "</table>";
				}
				catch (Exception e)
				{
					output = "Error while reading the Bill.";
					System.err.println(e.getMessage());
				}
				return output;
			 }
			
			
			//update
			public String updateBill(String billid, String accno, String musage, String totbill,String cname)
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
					String query = "UPDATE bill SET accno=?,musage=?,totbill=?,cname=? WHERE billid=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setString(1, billid);
					preparedStmt.setString(2, accno);
					preparedStmt.setString(3, musage);
					preparedStmt.setString(4, totbill);
					preparedStmt.setString(5,  cname);
					
			 
			 
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Bill Updated successfully";
				}
				catch (Exception e)
				{
					output = "Error while updating the Bill.";
					System.err.println(e.getMessage());
				}
				return output;
			}
			
			public String deleteBill(String billid)
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
					String query = "delete from bill where billid=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setString(1, billid);
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Deleted successfully Bill";
				}
				catch (Exception e)
				{
					output = "Error while deleting the bill.";
					System.err.println(e.getMessage());
				}
				return output;
			}
			
}





























