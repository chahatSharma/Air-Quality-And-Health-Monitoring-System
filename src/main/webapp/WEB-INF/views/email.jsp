<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="headerDoctor.jsp" />
<div class="container">
<form:form class="form" role="form" method="POST"  commandName="email" >

				<div class="form-group">
					<p style="color: red" >${ successMessage}</p>
				</div>
			
   <div class="form-group">
      <label for="to">To:</label>
      <form:input type="text" class="form-control" path="to" readonly="true"></form:input>
    </div>
    <div class="form-group">
      <label for="from">From:</label>
      <form:input type="text" class="form-control" path="from"></form:input>
    </div>  
    <div class="form-group">
      <label for="from">Subject:</label>
      <form:input type="text" class="form-control" path="subject"></form:input>
    </div> 
    <div class="form-group">
      <label for="from">Message:</label>
     <form:textarea class="form-control" rows="5" id="comment" path="msg"></form:textarea>
    </div> 
    <div class="text-center">
    <button type="submit" class="btn btn-default" formaction="reset.htm"> Back</button>
		<button type="submit" class="btn btn-default" formaction="sendMail.htm">Submit</button>
	</div>
</form:form>
</div>
</body>
</html>