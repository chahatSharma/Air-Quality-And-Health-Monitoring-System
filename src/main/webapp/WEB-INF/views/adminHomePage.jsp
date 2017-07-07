
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Admin Page</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-5">
				<c:if test="${!empty errorMessage}">
					<p style="color: red">${errorMessage }</p>
				</c:if>
				<form:errors path="ua1.*" />
				<form role="form" action="admPage.htm" method="post">
					<div class="form-group">
						<label for="email">User Name</label> <input type="text"
							name="userName" class="form-control" id="userName"
							placeholder="username" required>
					</div>
					<div class="form-group">
						<label for="pwd">Password</label> <input type="password"
							class="form-control" name="password" id="pwd"
							placeholder="Enter password" required>
					</div>
					<div class="form-group">
						<label for="role">Role</label> <select class="form-control"
							id="select" required onchange="selectBox()" name="role">
							<option value="Doctor">Doctor</option>
							<option value="Pho">Public Health Officer</option>
							<option value="Mayor">Mayor</option>
						</select>
					</div>
					<div id="doctorDiv" style="display: none">
					<input type="hidden" name="person.doctor.role" value="Doctor"/>
					<div class="form-group">
						<label for="name">First Name</label> <input type="text"
							class="form-control" name="person.firstName" id="firstName"
							placeholder="First Name">


					</div>
					<div class="form-group">
						<label for="name">Last Name</label> <input type="text"
							class="form-control" name="person.lastName" id="lastName"
							placeholder="Last Name">


					</div>
					<div class="form-group">
						<label for="name">Practice No</label> <input type="text"
							class="form-control" name="person.Doctor.practiceNo"
							id="practiceNo" placeholder="Practice No">

					</div>
					
					
					
					<div class="form-group">
						<label for="email">Email Id</label> <input type="text"
							class="form-control" name="person.emailId"
							id="emailId" placeholder="Email Id">

					</div>
					</div>
					
					<div id="mayorDiv" style="display: none">
					<input type="hidden" name="person.mayor.mayor" value="Mayor"/>
					<div class="form-group">
						<label for="name">First Name</label> <input type="text"
							class="form-control" name="person.firstName" id="firstName"
							placeholder="First Name">


					</div>
					<div class="form-group">
						<label for="name">Last Name</label> <input type="text"
							class="form-control" name="person.lastName" id="lastName"
							placeholder="Last Name">


					</div>
					<div class="form-group">
						<label for="name">City</label> <input type="text"
							class="form-control" name="person.mayor.cityOfficial"
							id="city" placeholder="Official City">

					</div>
					</div>
					<div id="phoDiv" style="display: none">
					<input type="hidden" name="person.publichealthofficer.role" value="PublicHealthofficer"/>
					<div class="form-group">
						<label for="name">First Name</label> <input type="text"
							class="form-control" name="person.firstName" id="firstName"
							placeholder="First Name">


					</div>
					<div class="form-group">
						<label for="name">Last Name</label> <input type="text"
							class="form-control" name="person.lastName" id="lastName"
							placeholder="Last Name">


					</div>
					<div class="form-group">
						<label for="zip">Zip Code</label> <input type="text"
							class="form-control" name="person.publichealthofficer.zipCodeOfficial"
							id="zip" placeholder="Official Zip Code">

					</div>
					</div>
					
					
					



					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
			<div class="col-sm-3"></div>



		</div>






	</div>

	<jsp:include page="footer.jsp" />
<script>
function selectBox(){
if(document.getElementById('select').selectedIndex == 0)
	{
		document.getElementById('doctorDiv').style="display:block";
		document.getElementById('phoDiv').style="display:none";
		document.getElementById('mayorDiv').style="display:none";
	}
else if(document.getElementById('select').selectedIndex == 1)
{
	document.getElementById('phoDiv').style="display:block";
	document.getElementById('doctorDiv').style="display:none";
	document.getElementById('mayorDiv').style="display:none";
}
else if(document.getElementById('select').selectedIndex == 2)
{
	document.getElementById('mayorDiv').style="display:block";
	document.getElementById('phoDiv').style="display:none";
	document.getElementById('doctorDiv').style="display:none";
	
}
}
selectBox();
</script>
</body>
</html>

