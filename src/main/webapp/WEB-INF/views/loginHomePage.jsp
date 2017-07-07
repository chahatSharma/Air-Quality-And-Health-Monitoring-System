<%-- 
    Document   : loginHomePage
    Created on : Dec 18, 2015, 3:41:50 PM
    Author     : chahatSharma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="resources/js/barChart.js"></script>
        <!--        <script>
                   
                    
                    
                    
                </script>-->
    </head>
    <body>
   
        <jsp:include page="headerLogin.jsp" />
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/highcharts-more.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>
        <div id="chartcontainer" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>
        <input type="hidden" id ="dataset1"    ">


        <input type="hidden" id ="dataset2" ">
        <jsp:include page="footer.jsp" />
        
        <script>
        function ajaxFunction()
        {
            var xmlHttp;

            try     // Firefox, Opera 8.0+, Safari
            {

                xmlHttp = new XMLHttpRequest();
            }
            catch (e)
            {
                try  // Internet Explorer
                {
                    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                }
                catch (e)
                {
                    try
                    {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                    catch (e)
                    {
                        alert("Your browser does not support AJAX!");
                        return false;
                    }
                }
            }

            xmlHttp.onreadystatechange = function ()
            {
                if (xmlHttp.readyState == 4)
                {
                    //alert(xmlHttp.responseText);
                    //document.getElementById('date').value = xmlHttp.responseText.trim();
                }
            }

            xmlHttp.open("GET", "time.jsp", true);
            xmlHttp.send();
            loadAjax('loadAvg.htm', myFunction);
        }
        function loadAjax(url, cfunc) {
            var xhttp, xmlDoc;
            xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    cfunc(xhttp);
                    var json = JSON.parse(xhttp.responseText);
                	document.getElementById('dataset1').value=json.dataset1;
                	document.getElementById('dataset2').value=json.dataset2;
                	
                }
            };
            xhttp.open("GET", url, true);
            xhttp.send();
        }

        function myFunction(xhttp) {

        	/*var json = JSON.parse(xHttp.responseText);
        	document.getElementById('dataset1').value=json.dataset1;
        	document.getElementById('dataset2').value=json.dataset2;
        	alert(json.dataset2);*/
        	//document.getElementById('meaning').value=json.meaning;

                            
        }
        ajaxFunction();
        </script>

    </body>
</html>
