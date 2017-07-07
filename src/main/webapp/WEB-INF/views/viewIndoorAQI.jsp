

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Indoor AQI</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/highcharts-more.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>
        
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
                        document.getElementById('date').value = xmlHttp.responseText.trim();
                    }
                }

                xmlHttp.open("GET", "time.jsp", true);
                xmlHttp.send();
                loadAjax('viewIndoorAqi.htm', myFunction);
            }
            function loadAjax(url, cfunc) {
                var xhttp, xmlDoc;
                xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        cfunc(xhttp);
                    }
                };
                xhttp.open("GET", url, true);
                xhttp.send();
            }

            function myFunction(xhttp) {

            	var json = JSON.parse(xHttp.responseText);
            	document.getElementById('aqi').value=json.aqi;
            	document.getElementById('respPoll').value=json.respPoll;
            	document.getElementById('meaning').value=json.meaning;

                                
            }
            </script>
        
    </head>
    <body onload="ajaxFunction();">
        <jsp:include page="headerLogin.jsp" />

        <div class="container">
            <div class="row">
            <form:form class="form-horizontal" role="form" action="viewIndoorAQI.htm" commandName="indoorAQIView" 
			method="post" >
                <div class="col-sm-4">
                    
                </div>
                <div class="col-sm-4"> 
                    <div id="chartcontainer" style="min-width: 310px; max-width: 400px; height: 300px; margin: 0 auto"></div>
                    
                </div>
                <div class="col-sm-4">
                     <div class="form-group B">
                        <label>Date</label>    
                         <form:input type="date" path ="date" class="form-control" id="date" placeholder="Date" required="true" value="${serverTime}"></form:input>
                            
                        
                    </div>
                    <div class="form-group B">
                        
                         <input type="submit" name ="search" class="form-control" id="submit" >
                            
                        
                    </div>
                    <div class="form-group B">
                        <label>AQI</label>    
                        <form:input type="text" path ="aqi" class="form-control" id="aqi"   ></form:input>
                        
                    </div>
                        <div class="form-group B">
                        <label>Responsible Pollutant</label>    
                        <form:input type="text" path ="respPoll" class="form-control" id="respPoll" ></form:input>
                        
                    </div>
                        <div class="form-group B">
                        <label>AQI Meaning</label>    
                        <form:input type="text" path ="meaning" class="form-control" id="meaning"  ></form:input>
                        
                    </div>
                </div>
                </form:form>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
         <script>
        //ajaxFunction();
        //var aqi = ${aqi};
        //document.getElementById('aqi').value = aqi;
        var chart = new Highcharts.Chart({
            chart: {
                type: 'gauge',
                plotBackgroundColor: null,
                plotBackgroundImage: null,
                plotBorderWidth: 0,
                plotShadow: false,
                renderTo : 'chartcontainer'
            },
            title: {
                text: 'Indoor AQI '
            },
            pane: {
                startAngle: -150,
                endAngle: 150,
                background: [{
                        backgroundColor: {
                            linearGradient: {x1: 0, y1: 0, x2: 0, y2: 1},
                            stops: [
                                [0, '#FFF'],
                                [1, '#333']
                            ]
                        },
                        borderWidth: 0,
                        outerRadius: '109%'
                    }, {
                        backgroundColor: {
                            linearGradient: {x1: 0, y1: 0, x2: 0, y2: 1},
                            stops: [
                                [0, '#333'],
                                [1, '#FFF']
                            ]
                        },
                        borderWidth: 1,
                        outerRadius: '107%'
                    }, {
                        // default background
                    }, {
                        backgroundColor: '#DDD',
                        borderWidth: 0,
                        outerRadius: '105%',
                        innerRadius: '103%'
                    }]
            },
            // the value axis
            yAxis: {
                min: 0,
                max: 500,
                minorTickInterval: 'auto',
                minorTickWidth: 1,
                minorTickLength: 10,
                minorTickPosition: 'inside',
                minorTickColor: '#666',
                tickPixelInterval: 30,
                tickWidth: 2,
                tickPosition: 'inside',
                tickLength: 10,
                tickColor: '#666',
                labels: {
                    step: 2,
                    rotation: 'auto'
                },
                title: {
                    text: 'km/h'
                },
                plotBands: [{
                        from: 0,
                        to: 50,
                        color: '#55BF3B' // green
                    }, {
                        from: 50,
                        to: 100,
                        color: '#DDDF0D' // yellow
                    }, {
                        from: 100,
                        to: 150,
                        color: '#ffa500' // orange
                    }, {
                        from: 150,
                        to: 200,
                        color: '#DF5353' // red
                    }, {
                        from: 200,
                        to: 300,
                        color: '#551a8b' // purple
                    }
                    , {
                        from: 300,
                        to: 500,
                        color: '#b03060' // maroon
                    }
                ]
            },
            series: [{
                    name: 'AQI',
                    data: [Number(document.getElementById('aqi').value)],
                    tooltip: {
                        valueSuffix: ''
                    }
                }]

        },
        // Add some life
        function (chart) {
//            if (!chart.renderer.forExport) {
//                setInterval(function () {
//                    var point = chart.series[0].points[0],
//                            newVal,inc=0;
////                            inc = Math.round((Math.random() - 0.5) * 20);
//
//                    newVal = point.y + inc;
//                    if (newVal < 0 || newVal > 200) {
//                        newVal = point.y - inc;
//                    }
//
//                    point.update(68);
//
//                }, 3000);
//            }
        });

        </script>
    </body>
</html>
