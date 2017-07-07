<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Bootstrap Example</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <style>
            body{
                background-color: whitesmoke;

            }
            .logo{

                font-family:"Helvetica";

                padding-top:2px;
                color:red;
            }
            h6{
                text-align: right;
            }

        </style>
    </head>
    <body>

        <div class="container">
            <header >
                <div class="page-header">
                    <h1 class="logo">Health and Air Quality Monitor</h1>
                    <h6>Your own health care</h6>
                </div>
                
                    
            </header>
            
            <nav class="navbar navbar-inverse">
                <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button></div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="#">Indoor AQ</a></li>
                        <li><a href="#">OutDoor AQ</a></li> 
                        <li><a href="#">Vital Sign</a></li> 
                         <li><a href="#">Dashboard</a></li> 
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    </ul>
                </div>
            </nav>

            <div class="col-sm-4">
            </div>
            <div class="col-sm-5">
<!--                <h2>Registration form</h2>-->
            </div>
            <div class="col-sm-3">
            </div>
            <form role="form">
                <div class="col-sm-4">
                </div>
                <div class="col-sm-5">

                    <div class="form-group">
                        <label for="Name"> First Name</label>
                        <input type="text" class="form-control" id="firstName"  placeholder="First Name" required>
                    </div>
                    <div class="form-group">
                        <label for="Name"> Last Name</label>
                        <input type="text" class="form-control" id="lastName"  placeholder="Last Name" required>
                    </div>
                    <div class="form-group">
                        <label for="email" >User Name</label>
                        <input type="text" class="form-control" id="userName" placeholder="username" required>
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password</label>
                        <input type="password" class="form-control" id="pwd" placeholder="Enter password" required>
                    </div>
                    <div class="form-group">
                        <label for="pwd">Confirm Password</label>
                        <input type="password" class="form-control" id="confPwd" placeholder="Confirm password" required>
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

                    <div class="form-group">
                        <label for="address1" >Address Line1</label>
                        <input type="text" class="form-control" id="addLine1" placeholder="Street Name/Building No" required>
                    </div>

                    <div class="form-group">
                        <label for="address2" >Address Line2</label>
                        <input type="text" class="form-control" id="addLine2" placeholder="Apartment No" required>
                    </div>

                    <div class="form-group">
                        <label for="city" >City</label>
                        <input type="text" class="form-control" id="city" placeholder="City" required>
                    </div>

                    <div class="form-group">
                        <label for="state" >State</label>
                        <input type="text" class="form-control" id="state" placeholder="State" required>
                    </div>

                    <div class="form-group">
                        <label for="zip" >Zip</label>
                        <input type="text" class="form-control" id="zip" placeholder="Zip" required>
                    </div>

                    <div class="form-group">
                        <label for="phoneNo" >Mobile Phone</label>
                        <input type="tel" class="form-control" id="phNo" placeholder="Mobile No" required>
                    </div>

                    <div class="checkbox">
                        <label><input type="checkbox"> Remember me</label>
                    </div>



                    <button type="submit" class="btn btn-default">Submit</button>
                </div>
                <div class="col-sm-3">
                </div>

            </form>
        </div>
<jsp:include page="footer.jsp" />
    </body>
</html>
