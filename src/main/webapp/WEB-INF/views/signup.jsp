<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Mental Dummy</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="../../MentalDummy/resources/css/signup/signup.css" />
<link rel="stylesheet" type="text/css"
	href="../../MentalDummy/resources/css/general/centre.css">
<link rel="stylesheet" type="text/css"
	href="../../MentalDummy/resources/css/general/genericLayout.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/Header.jsp" />
	<div class="container container-table">
		<div class='row vertical-center-row'>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<strong>Sign Up</strong>
					</h2>
				</div>
				<!-- panel-heading -->
				<div class="panel-body">
					<%
						String msg = request.getParameter("msg");
						if (msg != null) {
					%>
					<p id="redColor"><%=msg%></p>
					<%
						}
					%>

					<form id='signUpId' action="./signup/submit" method="post">

						<div class="form-group">
							<label for="firstName">First Name:</label>
							<p id="firstNamePara">
								<input id='firstNameInput' type="text" class="form-control"
									placeholder="Enter First Name" name="firstname" required>
							</p>
						</div>

						<div class="form-group">
							<label for="lastName">Last Name:</label>
							<p id="lastNamePara">
								<input id="lastNameInput" type="text" class="form-control"
									placeholder="Enter Last Name" name="lastname" required>
							</p>
						</div>

						<div class="form-group">
							<label for="email">Email:</label>
							<p id="emailPara">
								<input id="txtEmail" type="text" class="form-control"
									placeholder="Enter Email" name="email" required>
							</p>
						</div>

						<div id='genderId' class="form-group">
							<label for="gender">Gender: </label>
							<p id='radioPara'>
								<label class="radio-inline"> <input type="radio"
									name="gender" value="male">Male
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" value="female">Female
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" value="other">Other
								</label>
							</p>
						</div>

						<div class="form-group">
							<label for="password">Password:</label>
							<p id="pwdPara">
								<input id="pwdInput" type="password" class="form-control"
									placeholder="Enter Password" name="password" required>
							</p>
						</div>
						<div class="form-group">
							<label for="repPassword">Repeat Password:</label>
							<p id="repPwdPara">
								<input id="repPwdInput" type="password" class="form-control"
									placeholder="Re-enter Password" name="passwordrepeat" required>
							</p>
						</div>
						<div class="form-group">
							<p>
								By creating an account you agree to our <a href="#">Terms &
									Privacy</a>
							</p>
						</div>

						<div class="form-group">
							<button id='btnValidate' type="submit"
								class="btn btn-default elementColor">Sign Up</button>
						</div>

					</form>
				</div>
				<!-- panel-body -->
			</div>
		</div>
		<!-- panel -->
	</div>
	<jsp:include page="/WEB-INF/views/Footer.jsp" />
	<!-- container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="../../MentalDummy/resources/js/general/validateEmail.js"></script>
	<script type="text/javascript"
		src="../../MentalDummy/resources/js/signup/signup.js"></script>
</body>
</html>