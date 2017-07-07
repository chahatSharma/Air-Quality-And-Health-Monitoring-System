

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
            footer{
                margin-top: 30px;
                
               }
               footer img{
                   border-style: solid;
               }
               footer li{
                   list-style: none;
                   display: inline;
               }
               
    </style>
    </head>
    <body>
        <input type="hidden" name="enterprise" />
        <footer>
            <div class="container">
                <div class="row">
<!--                    <div class="col-sm-3"></div>-->
                    <div class="col-sm-2">
                        <img src="resources/images/airnow.jpg" alt="logo" width="40%" height="30%">
                    </div>
<div class="col-sm-8">
    <ul>
        <li><a href="/copyright/">© 2015 Trustees of Northeastern University</a></li>
        <li><a href="/search/">Search</a></li>
        <li class="last"><a href="/contact/">Contact</a></li>
    </ul>
</div>
<div class="col-sm-2">
    <p id="socialMedia">
        Follow us
        <a href="http://www.facebook.com/northeastern?_rdr=p" id="facebook" title="facebook"><img src="resources/images/facebook.jpeg" alt="Facebook" width="5%" height="5%"></a>
        <a href="https://twitter.com/nuadmissions" id="twitter" title="twitter"><img src="resources/images/footer_twitter-logo.jpg" alt="Twitter" width="5%" height="5%"></a>
        <a href="http://www.northeastern.edu/graduate/programs/information-systems/#masters" id="neu" title="NEU"><img src="resources/images/neu.jpeg" alt="Neu" width="5%" height="5%"></a>
    </p>
</div>
                    
                </div>
            </div>
        </footer>
    </body>
</html>
