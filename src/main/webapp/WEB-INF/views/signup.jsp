<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mental Dummy</title>
<link rel="stylesheet" type="text/css"
	href="../../MentalDummy/resources/css/singup.css" />
</head>
<body>
	<form action="./submit" method="post">
		<div class="container">
			<label><b>First Name</b></label> 
			<input type="text" placeholder="Enter  First Name" name="firstname" required>
			<label><b>Last Name</b></label> 
			<input type="text" placeholder="Enter Last Name" name="lastname" required>
			<label><b>Email</b></label>
			<input type="text" placeholder="Enter Email" name="email" required><br><br>
			<label><b>Gender</b></label>
			<input type="radio" name="gender" value="male" checked> Male
  			<input type="radio" name="gender" value="female"> Female
  			<input type="radio" name="gender" value="other"> Other <br><br>
			<label><b>Password</b></label>
			<input type="password" placeholder="Enter Password" name="password"
				required>
			<label><b>Repeat Password</b></label>
			<input type="password" placeholder="Repeat Password" name="passwordrepeat"
				required>
			<p>
				By creating an account you agree to our <a href="#">Terms &
					Privacy</a>.
			</p>

			<div class="clearfix">
				<button type="submit" class="signupbtn">Create Account</button>
			</div>
		</div>
	</form>
</body>
</html>