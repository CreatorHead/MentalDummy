<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="../../MentalDummy/resources/css/login/login.css" />
<link rel="stylesheet" type="text/css"
	href="../../MentalDummy/resources/css/general/centre.css">
<link rel="stylesheet" type="text/css"
	href="../../MentalDummy/resources/css/general/genericLayout.css">
<title>Mental Dummy</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/Header.jsp" />
	<div class="container container-table">
		<div class='row vertical-center-row'>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<strong>Login</strong>
					</h2>
				</div>
				<div class="panel-body">
					<%
						String msg = request.getParameter("msg");
						if (msg != null) {
					%>
					<p id="redColor"><%=msg%></p>
					<%
						}
					%>
					<form action="./login/submit" method="post">

						<div class="form-group">
							<label for="email">Email:</label>
							<p id=emailPara>
								<input id='txtEmail' type="text" class="form-control"
									placeholder="Enter Email" name="email" required>
							</p>
						</div>
						<div class="form-group">
							<label for="password">Password:</label> <input type="password"
								class="form-control" placeholder="Enter Password"
								name="password" required>
						</div>

						<div class="checkbox form-group" form-group>
							<label><input type="checkbox" name="remember">
								Remember me</label>
						</div>

						<div class="form-group">
							<button id='btnValidate' type="submit"
								class="btn btn-default elementColor">Login</button>
						</div>
						<div class="form-group">
							<span class="psw"><a href="#">Forgot password?</a></span>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- papnle -->
	</div>
	<jsp:include page="/WEB-INF/views/Footer.jsp" />
	<!-- container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="../../MentalDummy/resources/js/general/validateEmail.js"></script>
</body>
</html>