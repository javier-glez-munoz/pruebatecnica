<%@page import="com.example.demo.model.Item"%>
<%@page import="java.util.List"%>
<%@page import="com.example.demo.repository.ItemRepository"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<%
	String username = request.getParameter("username");
%>
<body>
<input id="username-input" type="hidden" value="<%=username%>">
<div class="container p-5">
	<div class="row text-center">
		<div id="items-list" class="col-10 offset-1">
			
		</div>
	</div>
	<div class="row mt-5">
	<div class="col-2 offset-5">
		<button onclick="goToAdd()" class="btn btn-primary">Add Item</button>
	</div>
	</div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$( document ).ready(function() {
	$.get("/api/v1/items").done(
			  function(data){
				  for (let key in data) {
					  let value = data[key];
					  var values = Object.values(value);
					  var claves = Object.keys(value);
					  $("#items-list").append("<div onclick=\"goToItem('"+values[0]+"')\" class=\"row justify-content-center\">" +
							  	values[0] + " ; " +
							  	values[1] + " ; " +
							  	values[3] + " ; " +
			                    "</div>");
					  $("#items-list").append("<div class=\"p-3\" style=\"border-bottom: 1px solid black;\"><button onclick=\"changeState('"+values[0]+"')\" class=\"btn btn-primary\">Deactivate</button></div>");
					}
				  	
		  }).fail(
			  function( jqXHR, textStatus, errorThrown ){
				  alert(textStatus);
		  });
});
function goToAdd(){
	window.location = "/additem?username="+$("#username-input").val();
}
function goToItem(itemCode){
	window.location = "/itemdetails?itemCode="+itemCode;
}

function changeState(itemCode){
	var urlTo = "/api/v1/items/"+itemCode;
	
	$.ajax({
	    url: urlTo,
	    type: 'PUT',
	    contentType: 'application/json',
	    data: JSON.stringify({
			state : "discontinued"
		  }),
	    success: function(result) {
	        alert("Ok");
	    }
	});
	
	}
</script>
</html>