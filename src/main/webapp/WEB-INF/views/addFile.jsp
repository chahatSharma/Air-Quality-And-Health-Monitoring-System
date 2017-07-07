
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Request Page</title>
</head>
<body>
	<jsp:include page="headerLogin.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-5">
			<c:if test="${!empty successMessage}">
					<p style="color: red">${successMessage }</p>
				</c:if>
			<c:choose>
				<c:when test="${!empty errorMessage}">
					<p style="color: red">${errorMessage }</p>
				</c:when>
				<c:otherwise>
				<form method="POST" action="uploadFile.htm"
					enctype="multipart/form-data">
					<input type="hidden" name="docId" value="${docId}" />
					<input type="hidden" name="fileUpload" id="fileUpload" />
					<div class="form-group">
						<label for="doctor">Doctor Name</label> <input type="text"
							name="doctorName" class="form-control" id="userName"
							placeholder="Doctor Name" value="${docName }">
					</div>
					<div class="form-group">
						<label for="msg">Message</label> <textarea
							class="form-control" name="message" id="msg"
							placeholder="Enter Message" rows="10" cols="200" required></textarea>
					</div>
					<div class="form-group">
					<label for="checkbox">Do you want to upload file:</label>
					<input type="checkbox" id="checkbox" name="checkbox" onclick="displayFileUpload()"/>
					</div>
					<div id="fileId" style="display: none">
					<div class="form-group">
						<label class="control-label">Select File</label> <input id="input"
							type="file" class="file" name="file">
					</div>
					<div class="form-group">
						<label class="control-label">File Name:</label>  
						<input type="text" name="name" class="form-control" > 
					</div>
					</div>



					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				</c:otherwise>
			</c:choose>
			</div>
			<div class="col-sm-3"></div>



		</div>






	</div>

	<jsp:include page="footer.jsp" />
	<script>
	function displayFileUpload(){
		if(document.getElementById('checkbox').checked)
			{
			document.getElementById('fileId').style="display:block";
			document.getElementById('fileUpload').value="yes";
			}
		else
			{
			document.getElementById('fileId').style="display:none";
			document.getElementById('fileUpload').value="no";
			}
	}
	displayFileUpload();
	</script>

</body>
</html>

