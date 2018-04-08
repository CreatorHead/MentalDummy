<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mental Dummy</title>
<link rel="stylesheet" type="text/css"
	href="../../MentalDummy/resources/css/login.css" />
</head>
<body>
	<h2>Login Form</h2>
	<%
		String msg = request.getParameter("msg");
		if (msg != null) {
	%>
			<h3 id="redColor"><%=msg%></h3>
	<%} %>
	
	<form action="./login/submit" method="post">
		<div class="imgcontainer">
			<img src="../../MentalDummy/resources/images/img_avatar2.png"
				alt="Avatar" class="avatar">
		</div>

		<div class="container">
			<label><b>Email</b></label> <input type="text"
				placeholder="Enter Email" name="email" required> <label><b>Password</b></label>
			<input type="password" placeholder="Enter Password" name="password"
				required>

			<button type="submit">Login</button>
			<input type="checkbox" checked="checked"> Remember me
		</div>

		<div class="container" style="background-color: #f1f1f1">
			<button type="button" class="cancelbtn">Cancel</button>
			<span class="psw">Forgot <a href="#">password?</a></span>
		</div>
	</form>
</body>
</html>