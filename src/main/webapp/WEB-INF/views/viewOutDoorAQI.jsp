<%-- 
    Document   : viewOutDoorAQI
    Created on : Dec 18, 2015, 6:23:21 PM
    Author     : chahatSharma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Redirecting Page</title>
        <script>
            function script()
            {
                document.getElementById('submit').click();
            }
            
        </script>
    </head>
    <body onload="script();">
      <div style="display:none">
      <form action="viewOutdoor.htm" >
      <input type="hidden" name="initialize" value="initialize" />
                <input type="submit" name="submit" id ="submit" value="submit"  />
                </form>
      </div>
    </body>
</html>
