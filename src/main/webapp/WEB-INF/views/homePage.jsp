

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style> 
            div p{
                text-align: center;
            }
            table{
                border-style: solid
            }
            table img{
               margin-left:30px;
               width:80%
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        
        <div class="container">
        <div class="row">
            <div class="table-responsive col-sm-4">
                <table class="table">
                    <thead>
                        <tr>
                            <th><p> <span class="glyphicon glyphicon-cloud">
                        </span>OutDoor AirQuality Check</p></th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <img src="resources/images/image.jpg" alt="google maps" width="80%" >
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>You can view Outdoor Air Quality anytime</p>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="table-responsive col-sm-4">
                <table class="table">
                    <thead>
                        <tr>
                            <th><p> <span class="glyphicon glyphicon-home">
                        </span>My Air</p></th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <img src="resources/images/indoorAQI.jpg" alt="google maps" width="80%"  >
                            </td>
                        </tr>
                         <tr>
                            <td>
                                <p>You can view Air Quality inside your home</p>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="table-responsive col-sm-4">
                <table class="table">
                    <thead>
                        <tr>

                            <th>   <p> <span class="glyphicon glyphicon-heart">
                        </span>My Health</p></th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <img src="resources/images/vital.jpg" alt="google maps" width="50%"  >
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>You can track your vital signs</p>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            </div>
            <div class="row">
            <div class="table-responsive col-sm-4">
                <table class="table">
                    <thead>
                        <tr>
                            <th>   <p> <span class="glyphicon glyphicon-stats">
                        </span>My Dashboard</p></th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <img src="resources/images/dashboard.jpg" alt="google maps" width="100%" >
                            </td>
                        </tr>
                        <td>
                                <p>Your personalized dashboard  </p>
                            </td>
                    </tbody>
                </table>
            </div>
            <div class="table-responsive col-sm-4">
                <table class="table">
                    <thead>
                        <tr>
                            <th>   <p> <span class="glyphicon glyphicon-download">
                        </span>My Reports</p></th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <img src="resources/images/vital.jpg" alt="google maps" width="100%" >
                            </td>
                        </tr>
                        <td>
                                <p>Your personalized reports  </p>
                            </td>
                    </tbody>
                </table>
            </div>
            <div class="table-responsive col-sm-4">
                <table class="table">
                    <thead>
                        <tr>
                            <th>   <p> <span class="glyphicon glyphicon-envelope">
                        </span>Alerts and Emails</p></th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <img src="resources/images/upload.jpg" alt="google maps" width="100%"  >
                            </td>
                        </tr>
                        <td>
                                <p>You can get alerts</p>
                            </td>
                    </tbody>
                </table>
            </div>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
