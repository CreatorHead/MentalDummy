<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mental Dummy</title>
<link rel="stylesheet" type="text/css"
	href="../../MentalDummy/resources/css/fileUpload.css" />
</head>
<body>
	<form action="./submit" method="post" enctype="multipart/form-data">
		<div class="container">
			<label><b>Section: </b></label> 
			<input type="text" placeholder="Enter Section Name" name="section" required/>
			<label><b>Select a file</b></label>
			<input type="file" name="file" required/>
			<button type="submit">Upload</button>
		</div>
	</form>
</body>
</html>