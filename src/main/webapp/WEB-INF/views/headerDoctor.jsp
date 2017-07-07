

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>header after Login</title>
        <!-- Latest compiled and minified CSS -->

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
        <script>
            $('li').on('click', function () {
                $('li').removeClass('active');
                $(this).addClass('active');
            });
            
        $(document).ready(function () {
            $('.dropdown-toggle').dropdown();
        });
        $(document).ready(function() {
            function disableBack() { window.history.forward() }

            window.onload = disableBack();
            window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
        });
        
   </script>
   
        
    </head>
    <body >
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
                        <li ><a href="reset.htm">Home</a></li>
                        <li><a href="requestList.htm">Request List</a></li>
                         <li><a href="repliedList.htm">Replied List</a></li>
                        
                        
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                   <li><a href="myAccount.htm"> Hello ${sessionScope.name}</a>
                        <li><a href="myAccount.htm"><span class="glyphicon glyphicon-user"></span> My Account</a></li>
                        <li><a href="logout.htm"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                    </ul>
                </div>
            </nav>

        </div>
    </body>
</html>

