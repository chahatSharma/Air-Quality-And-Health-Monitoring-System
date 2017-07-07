<%-- 
    Document   : index
    Created on : Dec 18, 2015, 3:08:08 PM
    Author     : chahatSharma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function script()
            {
                document.getElementById('submit').click();
            }
        </script>
    </head>
    <body onload="script();">
      <div style="display:none">
      <form action="home" >
                <input type="submit" name="submit" id ="submit" value="submit"  />
                </form>
      </div>
    </body>
</html>
