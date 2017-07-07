

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>JSP Page</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
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
                        <li><a href="#">AQI</a></li>
                        <li><a href="#">Vital Sign</a></li> 
                         <li><a href="#">Effects</a></li> 
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="signUp.htm"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                        <li><a href="login.htm"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    </ul>
                </div>
            </nav>

        </div>
    </body>
</html>

