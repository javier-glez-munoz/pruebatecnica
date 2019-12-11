<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container p-5">
	<div class="row">
		<div class="col-6 offset-3">
			<form id="login-form">
			  <div class="form-group">
			    <label for="username">Username</label>
			    <input type="text" class="form-control" id="username" placeholder="Username">
			  </div>
			  <div class="form-group">
			    <label for="password">Password</label>
			    <input type="password" class="form-control" id="password" placeholder="Password">
			  </div>
			  <button onclick="logIn()" class="btn btn-primary">Submit</button>
			</form>
		</div>
	</div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
function logIn(){
	$.post("/api/v1/user/login",
			{
			username : $("#username").val(),
		    password: $("#password").val(),
		  }).done(
			  function(data){
				  window.location = "/listitems?username="+$("#username").val();
				  	
		  }).fail(
			  function( jqXHR, textStatus, errorThrown ){
				  alert(textStatus);
		  });
	}
</script>
</html>