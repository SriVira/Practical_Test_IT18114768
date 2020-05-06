package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Appointment {
	
	//A common method to connect to the DB
		public Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password 
		 
		
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/onlineappointment", "root", "");
		 
		//For testing
		 System.out.print("Successfully connected"); 
		 
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		
		public String insertData(String aname, String anic, String aphone, String adoctorname, String ahosname, String adate, String atime)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " insert into appointments(`PatientName`,`PatientNIC`,`PatientPhoneNo`,`DoctorName`,`HospitalName`,`Date`,`Time`)"
		 + " values (?, ?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		
		 preparedStmt.setString(1, aname);
		 preparedStmt.setString(2, anic);
		 preparedStmt.setString(3, aphone);
		 preparedStmt.setString(4, adoctorname);
		 preparedStmt.setString(5, ahosname);
		 preparedStmt.setString(6, adate);
		 preparedStmt.setString(7, atime);
		 
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 
		 String newRead = readData();
		 output = "{\"status\":,\"success\",\"data\":\""+newRead+"\"}";
		 
		 }
		 catch (Exception e)
		 {
		output = "{\"status\":\"error\", \"data\": \"Error while inserting the Data.\"}"; 
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		public String readData()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table  class=\"table table-dark\" border=\"1\"><tr><th>Appointment ID</th><th>Patient Name</th><th>Patient NIC</th><th>Patient PhoneNumber</th><th>Doctor Name</th><th>Hospital Name</th><th>Date</th><th>Time</th><th>Update</th><th>Remove</th></tr>";
		 String query = "select * from appointments";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String A_AppointmentID = Integer.toString(rs.getInt("AppointmentID"));
			 String A_PatientName = rs.getString("PatientName");
			 String A_PatientNIC = rs.getString("PatientNIC");
			 String A_PatientPhoneNo = rs.getString("PatientPhoneNo");		
			 String A_DoctorName = rs.getString("DoctorName");
			 String A_HospitalName = rs.getString("HospitalName");
			 String A_Date = rs.getString("Date");
			 String A_Time = rs.getString("Time");
		 // Add into the html table
			 output += "<tr><td><input id=\"hidItemIdUpdate\" value=\"" + A_AppointmentID + "\" name=\"hidItemIdUpdate\" type=\"hidden\"> "+ A_AppointmentID +" </td>";
			 output += "<td>" + A_PatientName + "</td>";
			 output += "<td>" + A_PatientNIC + "</td>";
			 output += "<td>" + A_PatientPhoneNo + "</td>";
			 output += "<td>" + A_DoctorName + "</td>";
			 output += "<td>" + A_HospitalName + "</td>";
			 output += "<td>" + A_Date + "</td>";
			 output += "<td>" + A_Time + "</td>";
		 // buttons
		 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btnUpdate btn btn-secondary\"></td>"
				+ "<td><input name=\"btnRemove\" data-appid='"+A_AppointmentID+"'type=\"submit\" value=\"Remove\"class=\"btnRemove btn btn-danger\"></td></tr>";
		 
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the Data.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		
		public String updateData(String aID, String aname, String anic, String aphone, String adoctorname, String ahosname, String adate, String atime)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE appointments SET PatientName=?,PatientNIC=?,PatientPhoneNo=?,DoctorName=?,HospitalName=?,Date=?,Time=? WHERE AppointmentID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, aname);
		 preparedStmt.setString(2, anic);
		 preparedStmt.setString(3, aphone);
		 preparedStmt.setString(4, adoctorname);
		 preparedStmt.setString(5, ahosname);
		 preparedStmt.setString(6, adate);
		 preparedStmt.setString(7, atime);
		 preparedStmt.setInt(8, Integer.parseInt(aID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newRead = readData();
		 output = "{\"status\":,\"success\",\"data\":\""+newRead+"\"}";
		 }
		 catch (Exception e)
		 {
			 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the Data.\"}"; 
		 
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		public String deleteData(String A_AppointmentID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from appointments where AppointmentID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(A_AppointmentID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newRead = readData();
		 output = "{\"status\":,\"success\",\"data\":\""+newRead+"\"}";
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the Data.\"}"; 

		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 


}
