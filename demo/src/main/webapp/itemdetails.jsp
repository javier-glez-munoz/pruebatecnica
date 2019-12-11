<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<%
	String itemCode = request.getParameter("itemCode");
%>
<body>
<input id="item-code-input" type="hidden" value="<%=itemCode%>">
<div class="container p-5">
	<div class="row">
		<div class="col-6 offset-3">
			<form id="login-form" onsubmit="submitForm(); return false;">
			  <div class="form-group">
			    <label for="item-code">Item Code</label>
			    <input type="text" class="form-control" id="item-code" placeholder="Item Code">
			  </div>
			  <div class="form-group">
			    <label for="description">Description</label>
			    <input type="text" class="form-control" id="description" placeholder="Description">
			  </div>
			  <div class="form-group">
			    <label for="price">Price</label>
			    <input type="number" class="form-control" id="price" placeholder="Price">
			  </div>
			  
			</form>
			<button onclick="editItem('<%=itemCode%>')" class="btn btn-primary">Edit</button>
		</div>
	</div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$( document ).ready(function() {
	$.get("/api/v1/items/"+$("#item-code-input").val()).done(
			  function(data){
					  $("#item-code").val(data.itemCode);
					  $("#description").val(data.description);
					  $("#price").val(data.price);
					
				  	
		  }).fail(
			  function( jqXHR, textStatus, errorThrown ){
				  alert(textStatus);
		  });
});

function editItem(itemCode){
	var urlTo = "/api/v1/items/<%=itemCode%>";
	alert(urlTo);
	
	
	$.ajax({
	    url: urlTo,
	    type: 'PUT',
	    contentType: 'application/json',
	    data: JSON.stringify({
			itemCode : $("#item-code").val(),
		    description: "\"" + $("#description").val()+"\"",
		    price: $("#price").val()
		  }),
	    success: function(result) {
	        alert("Ok"");
	    }
	});
	
	}
</script>
</html>