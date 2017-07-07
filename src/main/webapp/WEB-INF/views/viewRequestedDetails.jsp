
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
	<jsp:include page="headerDoctor.jsp" />
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
<c:choose>
<c:when test = "${status eq 'Initiated' }">

			<div class="col-sm-4 col-md-4 col-lg-4"></div>

			<div class="col-sm-4 col-md-4 col-lg-4">

				<button class="btn btn-default" data-toggle="modal"
					data-target="#myModalNorm">Reply</button>
			</div>
			<div class="col-sm-4 col-md-4 col-lg-4"></div>

			<div class="col-sm-3"></div>



		

<form method="POST" action="replyToRequest.htm">
		<!-- Modal -->
		<div class="modal fade" id="myModalNorm" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<!-- Modal Header -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Reply</h4>
					</div>

					<!-- Modal Body -->
					<div class="modal-body">

						
							<div class="form-group">
								<label for="exampleInputEmail1">Email address</label> <textarea
									 class="form-control" id="exampleInputEmail1"
									placeholder="Enter email" name="reply"/></textarea>
							</div>
							
							<input type="hidden" name="requestId" value="${requestId }"/>
							
						


					</div>

					<!-- Modal Footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							Close</button>
						<button type="submit" class="btn btn-default">Submit</button>
					</div>
				</div>
			</div>
		</div>
		</form>

</c:when>
<c:otherwise>

<div class="form-group">
								<label for="msg">Reply</label>
								<textarea class="form-control" name="reply" id="msg"
									placeholder="Message" rows="10" cols="200" readonly="readonly">${reply }</textarea>
							</div>
</c:otherwise>
</c:choose>




	</div>

	<jsp:include page="footer.jsp" />


</body>
</html>

