<%@page import="model.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
 
 <% 
 /*
 session.setAttribute("statusMsg", "");
 System.out.println("Trying to process........");
 Appointment Aobj = new  Appointment();
 String stsMsh = "";
 //Insert.......................................

 if(request.getParameter("hidItemIDSave") == ""){
	 
	  stsMsh = Aobj.insertData(request.getParameter("Name"), request.getParameter("Nic"), request.getParameter("Phone"), request.getParameter("Hospital"), request.getParameter("Doctor"), request.getParameter("Date"), request.getParameter("Time"));
	  session.setAttribute("statusMsg", "Data Record Inserted");
	 
 }else{
	 
	 stsMsh = Aobj.updateData(request.getParameter("hidItemIDSave"),request.getParameter("Name"), request.getParameter("Nic"), request.getParameter("Phone"), request.getParameter("Hospital"), request.getParameter("Doctor"), request.getParameter("Date"), request.getParameter("Time"));
	 session.setAttribute("statusMsg", "Record Updated");
	 
 }
 //Delete........................................
 	
 if(request.getParameter("hidItemIdDelete")!= null){
	 
	 stsMsh = Aobj.deleteData(request.getParameter("hidItemIdDelete"));
	 session.setAttribute("statusMsg", " Record Deleted");
 }
 */
 %>       
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.5.0.min.js"></script> 
<script src="Components/main.js"></script> 

<meta charset="ISO-8859-1">
<title>Appointment</title>
</head>
<body>
<div class="container w-50 p-3" style="margin-top:50px;">
<div class="page-header">
  <h1 id="p" >Book Your Appointment </h1>
</div>
<br><br>


 <form id="form"  action="Appointment.jsp?" >
 
  <div class="form-group">
    <label for="email">Patient Name :</label>
    <input type="text" class="form-control" id="Name" name="Name">
  </div>
  
  <div class="form-group">
    <label for="pwd">Patient Nic :</label>
    <input type="text" class="form-control" name="Nic" id="Nic">
  </div>
  
  <div class="form-group">
    <label for="pwd">Patient Phone No :</label>
    <input type="text" class="form-control" name="Phone"  id="Phone">
  </div>
  
  <div class="form-group">
  <label for="sel1">Hospital Name :</label>
  <select class="form-control" id="Hospital" name="Hospital">
  	<option value ="..." selected>...</option>
    <option value ="Lady Bug Hospital">Lady Bug Hospital</option>
    <option value ="Richway Hospital">Richway Hospital</option>
    <option value ="CCC Hospital">CCC Hospital</option>
    <option value ="Bagya Hospital">Bagya Hospital</option>
  </select>
</div> 
  
   <div class="form-group">
  <label for="sel1">Doctor Name :</label>
  <select class="form-control" id="Doctor" name="Doctor">
  	<option value="..." selected>...</option>
    <option value ="Dr Naveen">Dr Naveen</option>
    <option value ="Dr Cursal">Dr Cursal</option>
    <option value ="Dr Amal">Dr Amal</option>
    <option value ="Dr Kamal">Dr Kamal</option>
  </select>
</div> 

  <div class="form-group">
    <label for="pwd">Date :</label>
    <input type="date" class="form-control" id="Date" name="Date">
  </div>
  
    <div class="form-group">
    <label for="pwd">Time :</label>
    <input type="text" class="form-control" id="Time" name="Time">
  </div>
  
  <button type="button" id="btnSave" class="btn btn-primary">Book Now</button>
  <input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value=""> 
</form> 
<br>
<div id="alertSuccess" class="alert alert-success"></div>   
<div id="alertError" class="alert alert-danger"></div>

<div id="divItemsGrid" style="margin-top:10px;">
		<% 
		
		Appointment Aobj2 = new  Appointment();
		out.print(Aobj2.readData());
		
		%> 
</div> 

</div>
		
</body>
</html>