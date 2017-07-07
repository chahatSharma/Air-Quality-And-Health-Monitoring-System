<%-- 
    Document   : viewVitalSign
    Created on : Dec 18, 2015, 7:51:50 PM
    Author     : chahatSharma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Vital Sign</title>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-more.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

</head>
<style>
table {
    table-layout: fixed;
    word-wrap: break-word;
}
</style>
<body>

	<jsp:include page="headerDoctor.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-2 col-md-2 col-lg-2">
				<div class="form-group"></div>
			</div>
			<div class="col-sm-8 col-md-8 col-lg-8">
				<div class="form-group">
					<p style="color: red" id="errorMessage"></p>
				</div>
			</div>
			<div class="col-sm-2 col-md-2 col-lg-2">
				<div class="form-group"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2 col-md-2 col-lg-2">
			</div>
			<div class="col-sm-6 col-md-6 col-lg-6">
				<form class="form-horizontal" role="form" action="report.pdf"
					method="GET" target="_blank">
					<div class="row">
						<div class="form-group">
							<label for="inputEmail" class="col-md-1 control-label">Start:</label>
							<div class="col-md-5 col-lg-5">
								<input type="date" class="form-control" id="startDate"
									placeholder="Start Date" name="startDate" value="${prevDate}">
							</div>
							<label for="inputEmail" class="col-md-1 control-label">End:</label>
							<div class="col-md-5 col-lg-5">
								<input type="date" class="form-control" id="endDate"
									placeholder="End Date" name="endDate" value="${serverTime}">
							</div>

						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<div class="col-md-3 col-lg-3"></div>
							<div class="col-md-6 col-lg-6">
								<input type="button" class="form-control" id="button"
									value="Submit" onclick="checkValidUser()">
							</div>

							<div class="col-md-3 col-lg-3"></div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<div class="col-md-2 col-lg-2">
							</div>
							<div class="col-md-8 col-lg-8">
								<table class="table table-bordered" id="displayTable">
									<!-- <thead>
										<tr>
											<th>Date</th>
											<th>Respiration Rate</th>
											<th>Heart Rate</th>
											<th>Weight</th>
											<th>BP</th>
											<th>VitalSignReport</th>

										</tr>
									</thead> -->
									<tbody>
									</tbody>

								</table>
							</div>
							<div class="col-md-2 col-lg-2">
							</div>

						</div>
					</div>
					<div class="row">
					<div class="col-md-4 col-lg-4">
							</div>
							<div class="col-md-4 col-lg-4">
							
					<input type="submit" class="form-control" id="button"
								 value="Generate Report" >
								 </div>
								 <div class="col-md-4 col-lg-4">
							</div>
					</div>
				</form>
			</div>
			<div class="col-sm-2 col-md-2 col-lg-2">
				<div id="chartcontainer"
					style="min-width: 310px; max-width: 800px; height: 400px;"></div>


			</div>

<div class="col-sm-2 col-md-2 col-lg-2">
				

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

					if (null == json.errorMessage) {
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

							} ]
						};
						var chart = new Highcharts.Chart(options);

						document.getElementById("errorMessage").innerHTML = '';

						/*******************************/

						var table = document.getElementById("displayTable");
						var length = document.getElementById("displayTable").rows.length;
						
						if(length == (Number(json.newList.length + 1)))
								{
							}
						else
							{
						if(json.newList.length > 0)
							{
							
							var row = document.createElement("tr");

							var col1 = document.createElement("th");
							var colText = document.createTextNode("Date");
							col1.appendChild(colText);
							row.appendChild(col1);
							
							var col2 = document.createElement("th");
							var col2Text = document.createTextNode("Respiration Rate");
							col2.appendChild(col2Text);
							
							row.appendChild(col2);

							var col3 = document.createElement("th");
							var col3Text = document.createTextNode("Heart Rate");
							col3.appendChild(col3Text);
							
							row.appendChild(col3);


							var col4 = document.createElement("th");
							var col4Text = document.createTextNode( "Weight");
							col4.appendChild(col4Text);
							
							row.appendChild(col4);


							var col5 = document.createElement("th");
							var col5Text = document.createTextNode( "BP");
							col5.appendChild(col5Text);
							
							row.appendChild(col5);

							var col6 = document.createElement("th");
							var col6Text = document.createTextNode("Conclusion");
							col6.appendChild(col6Text);
							
							row.appendChild(col6);
							
							table.appendChild(row);
							table.setAttribute("border", "2");
							
							}

						for (var k = 0; k < json.newList.length; k++) {

							var row = document.createElement("tr");

							var col1 = document.createElement("td");
							var colText = document.createTextNode(json.newList[k].captureDate)
							col1.appendChild(colText);
							row.appendChild(col1);
							
							var col2 = document.createElement("td");
							var col2Text = document.createTextNode(json.newList[k].respRate)
							col2.appendChild(col2Text);
							
							row.appendChild(col2);

							var col3 = document.createElement("td");
							var col3Text = document.createTextNode(json.newList[k].heartRate)
							col3.appendChild(col3Text);
							
							row.appendChild(col3);


							var col4 = document.createElement("td");
							var col4Text = document.createTextNode( json.newList[k].weight)
							col4.appendChild(col4Text);
							
							row.appendChild(col4);


							var col5 = document.createElement("td");
							var col5Text = document.createTextNode( json.newList[k].sysBp)
							col5.appendChild(col5Text);
							
							row.appendChild(col5);

							var col6 = document.createElement("td");
							var col6Text = document.createTextNode(json.newList[k].conclusion)
							col6.appendChild(col6Text);
							
							row.appendChild(col6);

							table.appendChild(row);
							table.setAttribute("border", "2");
						}
							}/**********************************************/
					} else {
						document.getElementById("errorMessage").innerHTML = json.errorMessage;
					}
				}

			};

			var startDate = document.getElementById("startDate").value;
			var endDate = document.getElementById("endDate").value;
			var query = "viewPatient.htm" + "&startDate=" + startDate
					+ "&endDate=" + endDate;
			xmlHttp.open("POST", "viewPatient.htm", true);
			xmlHttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xmlHttp.send(query);

		}

		checkValidUser();
	</script>
</body>
</html>
