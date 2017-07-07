<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctor Home Page</title>
</head>
<body>
	<jsp:include page="headerDoctor.jsp" />
	<div class="container">
		<form role="form" method="post">
		<div class="row">
				<div class="col-sm-3 col-md-3 col-lg-3">
					<div class="form-group"></div>
				</div>
					<div class="form-group">
							<label for="inputEmail" class="col-md-1 control-label">First Name:</label>
							<div class="col-md-2 col-lg-2">
								<input type="text" class="form-control" id="firstNameSearch"
									placeholder="First Name" name="firstNameSearch">
							</div>
							<label for="inputEmail" class="col-md-1 control-label">Last Name:</label>
							<div class="col-md-2 col-lg-2">
								<input type="text" class="form-control" id="lastNameSearch"
									placeholder="Last Name" name="lastNameSearch">
							</div>

						</div>
				<div class="col-sm-3 col-md-3 col-lg-3">
					<div class="form-group"></div>
				</div>
			</div>
			
			<div class="row">
			<br />
			<br />
			</div>
			<div class="row">
			<div class="col-sm-4 col-md-4 col-lg-4">
					<div class="form-group"></div>
				</div>
				
				<div class="col-sm-2 col-md-2 col-lg-2">
					<input type="submit" class="btn btn-default"
						formaction="searchPatient.htm" value="Search">
				</div>
				<div class="col-sm-2 col-md-2 col-lg-2">
					<input type="submit" class="btn btn-default"
						formaction="reset.htm" value="Reset">
				</div>
				<div class="col-sm-4 col-md-4 col-lg-4">
					<div class="form-group"></div>
				</div>
			</div>
			
			<div class="row">
			<br />
			<br />
			</div>
			
			<div class="row">
				<div class="col-sm-3 col-md-3 col-lg-3"></div>
				<div class="col-sm-6 col-md-6 col-lg-6">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Select</th>
								<th>Firstname</th>
								<th>Lastname</th>
								<th>DOB</th>

							</tr>
						</thead>
						<tbody>
							
								<c:forEach var="patient" items="${patientsList}">
								<tr>
									<td><input type="radio" name="patientId"
										value=${patient.personId }></td>
									<td>${patient.person.firstName}</td>
									<td>${patient.person.lastName }</td>
									<td>${patient.person.dob }</td>
									</tr>
								</c:forEach>
							

						</tbody>
					</table>
				</div>
				<div class="col-sm-3 col-md-3 col-lg-3"></div>
			</div>
			<div class="row">
				<div class="col-sm-3 col-md-3 col-lg-3">
					<div class="form-group"></div>
				</div>
				<div class="col-sm-2 col-md-2 col-lg-2">
					
				</div>
				<div class="col-sm-2 col-md-2 col-lg-2">
					<input type="submit" class="btn btn-default"
						formaction="viewVitalsDoctor.htm" value="View Vital Details">
				</div>
				<div class="col-sm-2 col-md-2 col-lg-2">
					<input type="submit" class="btn btn-default"
						formaction="contact.htm" value="Contact">
				</div>
				<div class="col-sm-1 col-md-1 col-lg-1">
					<div class="form-group"></div>
				</div>
			</div>

		</form>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>