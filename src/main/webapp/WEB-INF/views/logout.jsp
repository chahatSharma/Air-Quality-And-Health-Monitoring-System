
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Logout Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                </div>
                <div class="col-sm-5">
                <c:if test="${!empty successMessage}">
                <h1 style="color:red">${successMessage }</h1>
            	</c:if>
            	
            	</div>
            	</div>
            	</div>
<jsp:include page="footer.jsp" />
       
</body>
</html>