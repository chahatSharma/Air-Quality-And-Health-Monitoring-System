
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
						<form method="POST" action="replyToRequest.htm">
							<input type="hidden" name="fileName" value="${fileName}" /> <input
								type="hidden" name="location" id="location" value="${location}" />
							<div class="form-group">
								<label for="doctor">Patient Name</label> <input type="text"
									name="doctorName" class="form-control" id="userName"
									placeholder="Patient Name" value="${senderName }" readonly="readonly">
							</div>
							<div class="form-group">
								<label for="msg">Message</label>
								<textarea class="form-control" name="message" id="msg"
									placeholder="Message" rows="10" cols="200" readonly="readonly">${message }</textarea>
							</div>
							<div class="form-group">
								<label for="msg">Reply</label>
								<textarea class="form-control" name="message" id="msg"
									placeholder="Message" rows="10" cols="200" readonly="readonly">${reply }</textarea>
							</div>
							<div class="form-group">
								<label class="control-label">Request Date:</label> <input
									type="text" name="name" class="form-control"
									value="${requestDate }" readonly="readonly">
							</div>
							<div >
							
							<c:if test="${!empty attachment}">
							
							
								<c:url value="/viewAttachment.htm" var="servletUrl">
									<c:param name="fileName" value="${fileName }" />
									<c:param name="location" value="${location}" />
								</c:url>
								
								<a href="${servletUrl }" target="_blank">View Attachment</a>
								
								<c:url value="/downloadAttachment.htm" var="controllerUrl">
									<c:param name="fileName" value="${fileName }" />
									<c:param name="location" value="${location}" />
								</c:url>
								<a href="${controllerUrl }">Download Attachment</a>
								
							</c:if>
							
							</div>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
			</div>





	</div>

	<jsp:include page="footer.jsp" />


</body>
</html>

