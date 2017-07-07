<%-- 
    Document   : signUp
    Created on : Dec 18, 2015, 4:27:04 PM
    Author     : chahatSharma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Signup Page</title>
</head>
<body>
	<c:choose>
		<c:when test="${update eq 'update'}">
			<jsp:include page="headerLogin.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="header.jsp" />
		</c:otherwise>
	</c:choose>
	<div class="container-fluid">
		<div class="text-center">
			<h3>Please fill in the details!!</h3>
		</div>
		<div>
			<br />
		</div>

		<c:if test="${!empty successMessage}">
			<h1 style="color: red">${successMessage }</h1>
		</c:if>
		<form:form class="form-horizontal" role="form" action="addDetails.htm"
			commandName="ua" method="post">
			<input type="hidden" name="update" value="${update} " id="update" />
			<input type="hidden" name="personId" value="${personId}"
				id="personId" />
			<div class="row">
				<div class="col-sm-6 ">
					<div class="form-group">
						<label for="firstName" class="col-md-4 control-label">First
							Name</label>
						<div class="col-md-8 col-lg-4">
							<form:input path="person.firstName" type="text"
								class="form-control" id="firstName" placeholder="First Name"
								required="true"></form:input>
							<font color="red"><form:errors path="person.firstName" /></font>
						</div>
					</div>
				</div>
				<div class="col-sm-6 ">
					<div class="form-group">
						<label for="lastName" class="col-md-4 control-label">Last
							Name</label>
						<div class="col-md-8 col-lg-4">
							<form:input path="person.lastName" type="text"
								class="form-control" id="lastName" placeholder="Last Name"
								name="lastName" required="true"></form:input>
							<font color="red"><form:errors path="person.lastName" /></font>
						</div>
					</div>
				</div>
				</div>
				<div class="row">
				<div class="col-sm-6 ">
					<div class="form-group">
						<label for="dob" class="col-md-4 control-label">Date Of
							Birth</label>
						<div class="col-md-8 col-lg-4">
							<form:input type="date" class="form-control" id="dob"
								placeholder="Date Of Birth" required="true" path="person.dob"></form:input>
							<font color="red"><form:errors path="person.dob" /></font>
						</div>
					</div>
				</div>
				<div class="col-sm-6 ">
					<div class="form-group">
						<label for="inputEmail" class="col-md-4 control-label">Email:</label>
						<div class="col-md-8 col-lg-4">
							<form:input type="email" class="form-control" id="inputEmail"
								placeholder="Email" path="person.emailId"></form:input>
							<font color="red"><form:errors path="person.emailId" /></font>
						</div>
					</div>
				</div>
			</div>
				<div class="row">

				<div class="col-sm-6 col-md-6 col-lg-6">
					<div class="form-group">
						<label for="rrr"
							class="col-sm-12 col-md-12 col-lg-12 control-label">Home
							Address </label>
					</div>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-6">
					<div class="form-group">
						<br /> <br />
					</div>
				</div>
				</div>
				<div class="row">
				<div class="col-sm-6 ">
					<div class="form-group">
						<label for="addressLine1" class="col-md-4 control-label">Address
							Line1:</label>
						<div class="col-md-8 col-lg-4">
							<form:input type="text" class="form-control" id="addressLine1"
								placeholder="Street Addresss"
								path="person.addressByHomeAddressId.addressLine1"
								required="true"></form:input>
							<font color="red"><form:errors
									path="person.addressByHomeAddressId.addressLine1" /></font>
						</div>
					</div>
				</div>
				<div class="col-sm-6 ">
					<div class="form-group">
						<label for="addressLine2" class="col-md-4 control-label">Address
							Line2:</label>
						<div class="col-md-8 col-lg-4">
							<form:input type="text" class="form-control" id="addressLine2"
								placeholder="House/Apt No"
								path="person.addressByHomeAddressId.addressLine2"
								required="true"></form:input>
							<font color="red"><form:errors
									path="person.addressByHomeAddressId.addressLine2" /></font>
						</div>
					</div>
				</div>
				</div>
				<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label for="city" class="col-md-4 control-label">City:</label>
						<div class="col-md-8 col-lg-4">
							<form:input type="text" class="form-control" id="city"
								placeholder="City" path="person.addressByHomeAddressId.city"
								required="true"></form:input>
							<font color="red"><form:errors
									path="person.addressByHomeAddressId.city" /></font>
						</div>
					</div>
				</div>
				<div class="col-sm-6 ">
					<div class="form-group">
						<label for="state" class="col-md-4 control-label">State:</label>
						<div class="col-md-8 col-lg-4">
							<form:input type="text" class="form-control" id="state"
								placeholder="State" path="person.addressByHomeAddressId.state"
								required="true"></form:input>
							<font color="red"><form:errors
									path="person.addressByHomeAddressId.state" /></font>
						</div>
					</div>
				</div>
				</div>
				<div class="row">
				<div class="col-sm-6 ">
					<div class="form-group">
						<label for="zip" class="col-md-4 control-label">Zip:</label>
						<div class="col-md-8 col-lg-4">
							<form:input type="number" class="form-control" id="zip"
								placeholder="Zip Code"
								path="person.addressByHomeAddressId.zipcode" required="true"></form:input>
							<font color="red"><form:errors
									path="person.addressByHomeAddressId.zipcode" /></font>
						</div>
					</div>
				</div>
				<div class="col-sm-6 ">
					<div class="form-group">
						<label for="phoneNo" class="col-md-4 control-label">Phone
							No:</label>
						<div class="col-md-8 col-lg-4">
							<form:input type="number" class="form-control" id="phoneNo"
								placeholder="Phone No"
								path="person.addressByHomeAddressId.phoneNo" required="true"></form:input>
							<font color="red"><form:errors
									path="person.addressByHomeAddressId.phoneNo" /></font>
						</div>
					</div>
				</div>

</div>
				<div class="row">
				<div class="col-sm-6 col-md-6 col-lg-6">
					<div class="form-group">
						<label for="rrr"
							class="col-sm-12 col-md-12 col-lg-12 control-label">Other
							Details </label>
					</div>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-6">
					<div class="form-group">
						<br /> <br />
					</div>
				</div>
				</div>
				<div class="row">
				<div class="col-sm-6 ">
					<div class="form-group">
						<label for="addressLine1" class="col-md-4 control-label">Blood
							Group:</label>
						<div class="col-md-8 col-lg-4">
							<form:input type="text" class="form-control" id="bloodGroup"
								placeholder="Street Addresss" path="person.patient.bloodGroup"
								required="true"></form:input>
							<font color="red"><form:errors
									path="person.patient.bloodGroup" /></font>
						</div>
					</div>
				</div>
				<div class="col-sm-6 ">
					<div class="form-group">
						<label for="addressLine2" class="col-md-4 control-label">Zip
							Code:</label>
						<div class="col-md-8 col-lg-4">
							<form:input type="text" class="form-control" id="zipCode1"
								placeholder="Zip Code" path="person.patient.reportingZipCode"
								required="true"></form:input>
							<font color="red"><form:errors
									path="person.patient.reportingZipCode" /></font>
						</div>
					</div>
				</div>
				</div>
				<div class="row">
				<div class="col-sm-6 col-md-6 col-lg-6">
					<div class="form-group">
						<label for="rrr"
							class="col-sm-12 col-md-12 col-lg-12 control-label">User
							Account </label>
					</div>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-6">
					<div class="form-group">
						<br /> <br />
					</div>
				</div>
</div>
				<div class="row">
				<div class="col-sm-6 ">
					<div class="form-group">
						<div style="display: none" id="hiddenDiv">User already
							exists!</div>
						<label for="userName" class="col-md-4 control-label">User
							Name:</label>
						<div class="col-md-8 col-lg-4">
							<form:input type="text" class="form-control" id="userName"
								path="userName" placeholder="User Name"
								onblur="checkValidUser()"></form:input>
							<font color="red"><form:errors path="userName" /></font>
						</div>
					</div>

				</div>


				<div class="col-sm-6 ">
					<div class="form-group">
						<label for="inputPassword" class="col-md-4 control-label">Password:</label>
						<div class="col-md-8 col-lg-4">
							<form:input type="password" class="form-control"
								id="inputPassword" path="password" placeholder="Password"></form:input>
							<font color="red"><form:errors path="password" /></font>
						</div>
					</div>
				</div>
				</div>
				<div class="row">
				<div class="col-sm-6 ">
					<div class="form-group">
						<label for="phoneNo" class="col-md-6 control-label">Preferred
							mode of communication: </label>
						<div class="col-md-8 col-lg-4">
							<form:select path="person.patient.prefModeOfComm"
								onchange="changePrefMode()" id="select">

								<option value="email">Email</option>
								<option value="sms">SMS</option>
							</form:select>
						</div>
					</div>
				</div>
				<div id="modeOfComEmail" style="display: block">
					<div class="col-sm-6 ">
						<div class="form-group">
							<label for="phoneNo" class="col-md-4 control-label">Email
								Id:</label>
							<div class="col-md-8 col-lg-4">
								<form:input type="email" class="form-control" id="emailId"
									placeholder="Email Id" path="person.patient.emailId"></form:input>
								<font color="red"><form:errors
										path="person.patient.emailId" /></font>
							</div>
						</div>
					</div>
				</div>
				
				<div id="modeOfComSms" style="display: none">
					<div class="col-sm-6 ">
						<div class="form-group">
							<label for="phoneNo" class="col-md-4 control-label">Phone
								No:</label>
							<div class="col-md-8 col-lg-4">
								<form:input type="number" class="form-control" id="phoneNo"
									placeholder="Phone No" path="person.patient.phoneNo"></form:input>
								<font color="red"><form:errors
										path="person.patient.phoneNo" /></font>
							</div>
						</div>
					</div>
				</div>
				</div>
				<div class="row">

				<div class="col-sm-6 col-md-6 col-lg-6">
					<div class="form-group">
						<label class="col-sm-12 col-md-12 col-lg-12 control-label">
							<input type="checkbox" onclick="showDiv()" id="checkbox">
							Do you want to share details with your Doctor?
						</label>
					</div>
				</div>
				<div class="col-sm-4 col-md-4 col-lg-4">
					<div class="form-group">
						<br /> <br />
					</div>
				</div>
				</div>
				
				<div id="doctor" style="display: none">
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label for="city" class="col-md-4 control-label">Doctor
								First Name:</label>
							<div class="col-md-8 col-lg-4">
								<form:input type="text" class="form-control" id="docFirstName"
									placeholder="Doctor's First Name"
									path="person.patient.doctorFirstName"></form:input>
								<font color="red"><form:errors
										path="person.patient.doctorFirstName" /></font>
							</div>
						</div>
					</div>
					<div class="col-sm-6 ">
						<div class="form-group">
							<label for="state" class="col-md-4 control-label">Doctor
								Last Name:</label>
							<div class="col-md-8 col-lg-4">
								<form:input type="text" class="form-control" id="state"
									placeholder="Doctor's Last Name"
									path="person.patient.doctorLastName"></form:input>
								<font color="red"><form:errors
										path="person.patient.doctorLastName" /></font>
							</div>
						</div>
					</div>
					</div>
					<div class="row">
					<div class="col-sm-6 ">
						<div class="form-group">
							<label for="zip" class="col-md-4 control-label">Doctor
								Practice No:</label>
							<div class="col-md-8 col-lg-4">
								<form:input type="number" class="form-control" id="zip"
									placeholder="Practice No"
									path="person.patient.doctorPracticeNo"></form:input>
								<font color="red"><form:errors
										path="person.patient.doctorPracticeNo" /></font>
							</div>
						</div>
					</div>
				</div>



			</div>
			<div class="text-center">
				<button type="submit" class="btn btn-default">Submit</button>
			</div>

		</form:form>

		<jsp:include page="footer.jsp" />
	</div>
	<!-- /.container -->
	<script>
		function checkValidUser() {

			var userName = document.getElementById("userName").value;
			var xmlHttp;

			try // Firefox, Opera 8.0+, Safari
			{

				xmlHttp = new XMLHttpRequest();
			} catch (e) {
				try // Internet Explorer
				{
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					try {
						xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e) {
						alert("Your browser does not support AJAX!");
						return false;
					}
				}
			}

			xmlHttp.onreadystatechange = function stateChanged() {

				if (xmlHttp.readyState == 4) {

					var json = JSON.parse(xmlHttp.responseText);

					if (json.exists == "yes") {
						alert(xmlHttp.responseText);
						document.getElementById("hiddenDiv").style = "display:block";
						document.getElementById("userName").style = "background-color:red";

					} else {
						document.getElementById("userName").style = "background-color:green";
						document.getElementById("hiddenDiv").style = "display:none";
					}
					//document.getElementById("success").innerHTML = json.successmsg;
				}
			};
			var update = document.getElementById('update').value;
			var query = "validate.htm" + "&userName=" + userName + "&update="
					+ update;

			xmlHttp.open("POST", "validate.htm", true);
			xmlHttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xmlHttp.send(query);

		}
		function showDiv() {
			if (document.getElementById('checkbox').checked) {
				document.getElementById('doctor').style = "display : block";
			} else {
				document.getElementById('doctor').style = "display : none";
			}

		}
		function changePrefMode() {
			if (document.getElementById('select').selectedIndex == 0) {
				document.getElementById('modeOfComEmail').style = "display: block";
				document.getElementById('modeOfComSms').style = "display: none";
			} else if (document.getElementById('select').selectedIndex == 1) {
				document.getElementById('modeOfComSms').style = "display: block";
				document.getElementById('modeOfComEmail').style = "display: none";
			}

		}
	</script>
</body>
</html>