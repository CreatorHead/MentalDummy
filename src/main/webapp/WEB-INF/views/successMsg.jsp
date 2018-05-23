<%@page import="com.nullthinker.mentaldummy.beans.User"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
@import url('http://getbootstrap.com/dist/css/bootstrap.css');

html, body, .container-table {
	height: 94.8%;
}

.container-table {
	display: table;
}

.vertical-center-row {
	display: table-cell;
	vertical-align: middle;
}
</style>
<style>
.panel-default>.panel-heading {
	background-color: #4caf50;
	color: #ffffd3
}
</style>
<title>Mental Dummy</title>
</head>
<body>
	<%
		session = request.getSession(false);
		if(session != null){
			User user = (User)session.getAttribute("user");
			if(user.getRoles().contains("admin")){ %>
				<jsp:include page="/WEB-INF/views/HeaderAdmin.jsp" />
				
			<%}else if(user.getRoles().contains("examiner")){%>
				<jsp:include page="/WEB-INF/views/HeaderExaminer.jsp" />
			<%}else if(user.getRoles().contains("user")){%>
				<jsp:include page="/WEB-INF/views/HeaderUser.jsp" />
			<% }
		}else{%>
			<jsp:include page="/WEB-INF/views/Header.jsp" />
		<% }%>
	<div class="container container-table">
		<div class='row vertical-center-row'>
			<div class="panel panel-default">
				<div class='panel-heading'>
					<h4>
						<strong>Message</strong>
					</h4>
				</div>
				<div class='panel-body'>
					<p id="redColor">${msg}</p>
				</div>
			</div>
		</div>
		<!-- row -->
	</div>
	<jsp:include page="/WEB-INF/views/Footer.jsp" />
	<!-- Container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>