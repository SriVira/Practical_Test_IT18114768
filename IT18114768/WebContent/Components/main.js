$(document).on("click", ".btnUpdate", function(event) { 
	
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIdUpdate').val());
	$("#Name").val($(this).closest("tr").find('td:eq(1)').text());
	$("#Nic").val($(this).closest("tr").find('td:eq(2)').text());
	$("#Phone").val($(this).closest("tr").find('td:eq(3)').text());
	$("#Hospital").val($(this).closest("tr").find('td:eq(4)').text());
	$("#Doctor").val($(this).closest("tr").find('td:eq(5)').text());
	$("#Date").val($(this).closest("tr").find('td:eq(6)').text());
	$("#Time").val($(this).closest("tr").find('td:eq(7)').text());
	
	$("#alertSuccess").text("Data Retrived");
	$("#alertSuccess").show();  

});

$(document).ready(function () {
	 document.forms['form'].reset();
	 
});

$(document).ready(function() { 
	
	if($("#alertSuccess").text().trim() == ""){
		$("#alertSuccess").hide();
	}
	
	$("#alertError").hide(); 
	
});

$(document).on("click", "#btnSave", function(event) { 
	
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text(""); 
	$("#alertError").hide(); 
	
	var status = validateItemForm(); 
	
	if (status != true)  {  
		$("#alertError").text(status);  
		$("#alertError").show();  
		return; 	
		} 
	
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
	
	$.ajax( { 
		url : "AppointmentApi", 
		type : type,  
		data : $("#form").serialize(),  
		dataType : "text",  
		complete : function(response, status) 
		{   
			onItemSaveComplete(response.responseText, status);  
		
		} 
	}); 
	
	$("#alertSuccess").text("Successfully saved.");
	$("#alertSuccess").show();  
});

function onItemSaveComplete(response, status) {  
	
	var resultSet = JSON.parse(response); 
	 
	if (resultSet.status.trim() == "success") {  
		
		$("#alertSuccess").text("Successfully saved.");  $("#alertSuccess").show(); 
		 
		 $("#divItemsGrid").html(resultSet.data); 
	
	} else if (resultSet.status.trim() == "error") 
	
	{  
		$("#alertError").text(resultSet.data); 
		$("#alertError").show(); 
		
	}
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");  
		$("#alertError").show(); 
	}
	 else 
	 {  
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
	 }
	
	$("#hidItemIDSave").val(""); 
	$("#form")[0].reset(); 
}


$(document).on("click", ".btnRemove", function(event) { 
	
	
	$.ajax( { 
		url : "AppointmentApi",   
		type : "DELETE",   
		data : "Appoid=" + $(this).data("appid"),   
		dataType : "text",   
		complete : function(response, status) 
		{   
			onItemDeleteComplete(response.responseText, status);  
		
		} 
	}); 
	$("#alertSuccess").text("Successfully deleted. ");
	$("#alertSuccess").show();
});

function onItemDeleteComplete(response, status) {  
	
	var resultSet = JSON.parse(response); 
	 
	if (resultSet.status.trim() == "success") {  
		
		$("#alertSuccess").text("Successfully deleted.."); 
		$("#alertSuccess").show(); 
		 
		 $("#divItemsGrid").html(resultSet.data); 
	
	} else if (resultSet.status.trim() == "error") 
	
	{  
		$("#alertError").text(resultSet.data); 
		$("#alertError").show(); 
		
	}
	else if (status == "error") {
		
		$("#alertError").text("Error while deletingdeleting.");  
		$("#alertError").show(); 
	}
	 else 
	 {  
		 $("#alertError").text("Unknown error while deleting.."); 
		 $("#alertError").show(); 
	 }
	
}



	
	

function validateItemForm() {  
	
	if ($("#Name").val().trim() == "") {  
		
		return "Please Enter Patient Name";
	} 
	
	if ($("#Nic").val().trim() == "") {  
		 
		return "Please Enter Nic Id";
	} 
	
	if ($("#Phone").val().trim() == "") {  

	return "Please Enter Mobile Number";
	} 
	
	if ($("#Hospital").val().trim() == "...") {  

		return "Please Select Hospital";
	} 
	
	if ($("#Doctor").val().trim() == "...") {  

		return "Please Select Doctor ";
	}
	
	if ($("#Date").val().trim() == "dd/mm/yyyy") {  

		return "Please Select Preferd Date ";
	}
	
	if ($("#Time").val().trim() == "") {  

		return "Please Enter Preferd Time";
	}
	
	return true; 
	 
	}
