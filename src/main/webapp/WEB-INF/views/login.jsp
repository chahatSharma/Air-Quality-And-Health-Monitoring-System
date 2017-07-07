
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Signup Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                </div>
                <div class="col-sm-5">
                <c:if test="${!empty errorMessage}">
                <p style="color:red">${errorMessage }</p>
            	</c:if>
                <form:errors path="ua1.*" />
                   <form role="form" action="userAccount.htm" method="post" >
                    <div class="form-group">
                        <label for="email" >User Name</label>
                         <input type="text" name ="userName" class="form-control" id="userName" placeholder="username" required>
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password</label>
                        <input type="password" class="form-control" name = "password" id="pwd" placeholder="Enter password" required>
                    </div>
                    <!-- <div class="form-group">
                        <label for="pwd">Confirm Password</label>
                        <input type="password" class="form-control" id="confPwd" placeholder="Confirm password" required>
                    </div> -->

                    <div class="checkbox">
                        <label><input type="checkbox"> Remember me</label>
                    </div>



                    <button type="submit" class="btn btn-default">Submit</button>
                   </form>
                </div>
                <div class="col-sm-3">
                </div>
                
                
                
            </div>
            
            
            
            
            
            
        </div>
        
         <jsp:include page="footer.jsp" />
       
    </body>
</html>

