<%-- 
    Document   : patientDetails
    Created on : Dec 18, 2015, 5:20:06 PM
    Author     : chahatSharma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sensor Specific Details</title>
    </head>
    <body>
       <jsp:include page="header.jsp" />
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                </div>
                <div class="col-sm-5">
                    <form role="form" action="personalDetails">
                    <div class="form-group">
                        <label for="Name"> First Name</label>
                        <input type="text" class="form-control" id="firstName"  placeholder="First Name" required>
                    </div>
                    <div class="form-group">
                        <label for="Name"> Last Name</label>
                        <input type="text" class="form-control" id="lastName"  placeholder="Last Name" required>
                    </div>
                    
                    <label for="bday">Birthday</label>
                    <div class="form-group">

                        <div class="col-sm-4">
                            <!--                            <input type="month" class="form-control" id="month" placeholder="Month"  required>-->

                            <select class="form-control" id="sel1">
                                <option>Month</option>
                                <option>January</option>
                                <option>February</option>
                                <option>March</option>
                                <option>April</option>
                                <option>May</option>
                                <option>June</option>
                                <option>July</option>
                                <option>August</option>
                                <option>September</option> 
                                <option>October</option>
                                <option>November</option>
                                <option>December</option>

                            </select>
                        </div>
                        <div class="col-sm-4">
                            <input type="number" class="form-control" id="day" placeholder="Day"  required>
                        </div>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="year" placeholder="Year"  required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" >Email</label>
                        <input type="email" class="form-control" id="emailId" placeholder="Email" required>
                    </div>

                    
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
    </body>
</html>
