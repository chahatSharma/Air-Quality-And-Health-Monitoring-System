<%-- 
    Document   : viewVitalSign
    Created on : Dec 18, 2015, 7:51:50 PM
    Author     : chahatSharma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Vital Sign</title>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-more.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

</head>
<body>
	<jsp:include page="headerLogin.jsp" />
	<div class="container-fluid">
	<div class="row">
	<div class="col-sm-2 col-md-2 col-lg-2">
					<div class="form-group">
						
					</div>
				</div>
		<div class="col-sm-8 col-md-8 col-lg-8">
					<div class="form-group">
						<p style="color:red" id="errorMessage" ></p>
					</div>
				</div>
				<div class="col-sm-2 col-md-2 col-lg-2">
					<div class="form-group">
						
					</div>
				</div>
				</div>
		<div class="row">
		<div class="col-sm-1 col-md-1 col-lg-1">
					<div class="form-group">
						<br /> <br />
					</div>
				</div>
			<div class="col-sm-4 col-md-4 col-lg-4">
				<form class="form-horizontal" role="form" action="report.pdf" method="GET" target="_blank">
				<div class="row">	
					<div class="form-group">
						<label for="inputEmail" class="col-md-2 control-label">Start:</label>
						<div class="col-md-4 col-lg-4">
							<input type="date" class="form-control" id="startDate"
								placeholder="Start Date" name="startDate" value="${ prevDate}">
						</div>
						<label for="inputEmail" class="col-md-2 control-label">End:</label>
						<div class="col-md-4 col-lg-4">
							<input type="date" class="form-control" id="endDate"
								placeholder="End Date" name="endDate" value="${serverTime }">
						</div>
					
				</div>
				</div>
				<div class="row">
				<div class="form-group">
				<div class="col-md-2 col-lg-2">
							
				</div>
						<div class="col-md-4 col-lg-4">
							<input type="button" class="form-control" id="button"
								 value="Submit" onclick="checkValidUser()">
				</div>
				<div class="col-md-4 col-lg-4">
							<input type="submit" class="form-control" id="button"
								 value="Generate Report" >
				</div>
				<div class="col-md-2 col-lg-2">
							
				</div>
				</div>
				</div>
				</form>
				</div>
			<div class="col-sm-6 col-md-6 col-lg-6">
				<div id="chartcontainer"
					style="min-width: 310px; max-width: 800px; height: 400px;"></div>


			</div>
			<div class="col-sm-1 col-md-1 col-lg-1">
					<div class="form-group">
						<br /> <br />
					</div>
				</div>
				
		</div>
	</div>
	<jsp:include page="footer.jsp" />
	
			<script>
		function checkValidUser() {
			
			
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

					if(null == json.errorMessage)
						{
					var jsonReps = json.datasetResp;
					var jsonHeart = json.datasetHear;
					var jsonWeight = json.arrayWeight;
					var jsonBp = json.datasetBp;

					var options = {
						chart : {
							renderTo : 'chartcontainer',
							type : 'column'
						},
						series : [ {
							name : 'Respiration Rate',
							data : jsonReps
						}, {
							name : 'Heart Rate',
							data : jsonHeart
						}, {
							name : 'Weight',
							data : jsonReps
						}, {
							name : 'BP',
							data : jsonBp

						} ],title: {
						    text: 'Vital Sign Report'
						}
					};
					var chart = new Highcharts.Chart(options);

					document.getElementById("errorMessage").innerHTML = '';
				}
					else 
					{
						document.getElementById("errorMessage").innerHTML = json.errorMessage;
					}
				}
				
			};
			var startDate = document.getElementById("startDate").value;
			var endDate = document.getElementById("endDate").value;
			var query = "viewVitals.htm" + "&startDate=" + startDate + "&endDate=" + endDate;
			xmlHttp.open("POST", "viewVitals.htm", true);
			xmlHttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xmlHttp.send(query);

		}

		checkValidUser();
	</script>
</body>
</html>
