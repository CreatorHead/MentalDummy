<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mental Dummy</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="../../MentalDummy/resources/css/general/genericLayout.css">
<link rel="stylesheet" type="text/css"
	href="../../MentalDummy/resources/css/questionUpload/questionUpload.css">
</head>
<body>
	<div class="container container-table">
		<div class='row vertical-center-row'>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<strong>Questions Upload</strong>
					</h2>
				</div>
				<!-- panel-heading -->
				<div class="panel-body">
					<%
						String msg = request.getParameter("msg");
						if (msg != null) {
					%>
					<p class='redColor' id="redColor"><%=msg%></p>
					<%
						}
					%>
					<form id='uploadQuestion' action="./submit/questions" method="post"
						enctype="multipart/form-data">

						<div class="form-group">
							<label for="subject">Select a Subject:</label> 
							<select
								class="form-control" id="subject" name='subject'>
								<option>Java</option>
								<option>SQL</option>
								<option>J2EE</option>
								<option>Manual Testing</option>
							</select>
						</div>

						<div class="form-group">
							<label for="duration">Test Duration in Mins:</label>
							<p id="durationPara">
								<input id="durationInput" type="number" class="form-control"
									placeholder="Enter Time Duration" name="duration" required>
							</p>
						</div>

						<div class="form-group">
							<label for="passMarks">Passing Marks (%):</label>
							<p id="passMarksPara">
								<input id="passMarksInput" type="number" class="form-control"
									placeholder="Enter Percentage" name="passMarks" required>
							</p>
						</div>

						<div id='diffId' class="form-group">
							<label for="diffLvl">Difficulty: </label>
							<p id='diffradioPara'>
								<label class="radio-inline"> <input type="radio"
									name="diffLvl" value="low">Low
								</label> <label class="radio-inline"> <input type="radio"
									name="diffLvl" value="intermediate">Intermediate
								</label> <label class="radio-inline"> <input type="radio"
									name="diffLvl" value="high">High
								</label>
							</p>
						</div>

						<div class="form-group">
							<label for="passkey">Pass Key:</label>
							<p id="passkeyPara">
								<input id="passkeyInput" type="text" class="form-control"
									placeholder="Enter Pass key" name="passkey" required>
							</p>
						</div>

						<div class="form-group">
							<p>
								<strong>Pleas check out the</strong> <a href="../resources/testFormat/test_format.xlsx"><strong>Question
										Upload Format</strong></a>
							</p>
						</div>

						<div class="form-group">
							<label for="file">Upload:</label>
							<p id="fileUploadPara">
								<input id='fileUploadInput' type="file" name="file" required />
							</p>
						</div>

						<div class="form-group">
							<input id='btnValidate' type="submit"
								class="btn btn-default elementColor" value='Submit'/>
						</div>
					</form>
				</div>
				<!-- panel-body -->
			</div>
		</div>
		<!-- panel -->
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
	<script type="text/javascript"
		src="../../MentalDummy/resources/js/questionUpload/questionUpload.js"></script>
</body>
</html>