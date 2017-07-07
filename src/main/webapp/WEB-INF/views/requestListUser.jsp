<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Replied List</title>
</head>
<body>
	<jsp:include page="headerLogin.jsp" />
	<div class="container">
	<div class="row">
	<div class="col-sm-4"></div>
			<div class="col-sm-5">
			<h1> Replied Request </h1>
			</div>
			<div class="col-sm-3"></div>
			</div>
			<div class="row">
		<form role="form" method="post">
		<c:if test="${!empty errorMessage }">
		<p style="color:red">${errorMessage }</p>
		</c:if>
		<c:if test="${!empty successMessage }">
		<p style="color:red">${successMessage }</p>
		</c:if>
		
		<!-- 
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
			-->
			
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
								<th>RequestId</th>
								<th>RequestDate</th>
								<th>Replied</th>
								<th>Replied Date</th>

							</tr>
						</thead>
						<tbody>
							
								<c:forEach var="request" items="${requestList}">
								<tr>
									<td><input type="radio" name="requestId"
										value=${request.requestId }></td>
									<td>${request.requestId}</td>
									<td>${request.requestDate }</td>
									<td>${request.replied }</td>
									<td>${request.repliedDate}</td>
									
									</tr>
								</c:forEach>
							

						</tbody>
					</table>
				</div>
				<div class="col-sm-3 col-md-3 col-lg-3"></div>
			</div>
			<div class="row">
				<div class="col-sm-4 col-md-4 col-lg-4">
					<div class="form-group"></div>
				</div>
				
				<div class="col-sm-4 col-md-4 col-lg-4">
					<input type="submit" class="btn btn-default"
						formaction="viewRequestDetailsUser.htm" value="View Request Details">
				</div>
				
				<div class="col-sm-4 col-md-4 col-lg-4">
					<div class="form-group"></div>
				</div>
			</div>

		</form>
	</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>