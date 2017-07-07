<%-- 
    Document   : result
    Created on : Dec 14, 2015, 6:06:26 PM
    Author     : chahatSharma
--%>

<!DOCTYPE html >
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="headerLogin.jsp" %>

<html  lang="en" >
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
        <style type="text/css">
            #search{
                margin-left: 100px;
            }
            #map {
                width:100%;
                height:500px;

            }
            #buttons{
                margin-top: 10px;
            }


        </style>
    </head>
    <body onload="ajaxFunction();" >
          <input type="hidden" id="lat" name="lat"   >
          <input type="hidden" id="lng" name="lng"   >
          <input type="hidden" id="color" name="color"   >
          <input type="hidden" id="markerContent" name="markerContent" >
        <div class ="container">
              <div class="col-sm-2"></div>
              <div class="col-sm-6">
                  <div  id="map" class="google-map-canvas"></div>

                  <div id="buttons"><input type="button" class="btn-sm col-sm-2" value="Good" style="background-color: green">

                      <input type="button" class=" btn-sm col-sm-2" value="Moderate" style="background-color: yellow">
                      <input type="button" class="btn-sm col-sm-2" value="USG" style="background-color: orange">
                      <input type="button" class="btn-sm col-sm-2" value="Unhealthy" style="background-color: red">
                      <input type="button" class="btn-sm col-sm-2" value="Very unhealthy" style="background-color: purple">
                      <input type="button" class="btn-sm col-sm-2" value="Hazardous" style="background-color: maroon">

                  </div> 
              </div>
              <div class="col-sm-4"></div>
              <form:form action="viewOutdoor.htm" method="POST" commandName="googleMap">

                  <div class="form-group ">
                      <label for="zip" class="col-sm-1"> Zip </label>
                      <input type="text" class="form-inline " id="zip" name="zip" placeholder="Zip" required="true">
                  </div>
                  <div class="form-group">
                      <label for="date" class="col-sm-1"> Date </label>
                      <input type="text" class="form-inline" id="date" name="date" required="true" value="${serverTime}">
                  </div>
<input type="hidden" name="aqi" />
                  <input type="submit" class="btn btn-default " id="search" value="Search">
                  <span class="glyphicon glyphicon-search"></span>
                
</form:form>
              
          </div>

          <script type="text/javascript">

                      var map;
                      var zip =${zip};
                      var aqi = ${aqi};
                      var c = ${color};
                      var color = '';
                      if(c ==0)
                          color="green";
                      else if(c==1)
                          color="yellow";
                      else if(c==2)
                          color="orange";
                      else if(c==3)
                          color="red";
                      else if(c==4)
                          color="0xCC66FF";
                      else if(c==5)
                          color="0x990000";
                     
                      
                      document.getElementById('color').value=color;
                      function initMap() {

                    	  var lat =  ${lat};
                              var lng =${lng};
                            	document.getElementById('markerContent').value=aqi;
                              var content = document.getElementById('markerContent').value;
                              if (!isNaN(lat) && !isNaN(lng))
                      {

                      myLatLng = new google.maps.LatLng(lat, lng);
                              var myOptions = {center: myLatLng, zoom: 14, mapTypeId: google.maps.MapTypeId.HYBRID};
                              map = new google.maps.Map(document.getElementById('map'), myOptions);
                              var marker = new google.maps.Marker({
                              position: myLatLng,
                              });
                              marker.setMap(map);
                              var infowindow = new google.maps.InfoWindow({
                              content: content
                              });
                              //            google.maps.event.addListener(marker, 'click', function () {
                              infowindow.open(map, marker);
                              //            });
                              //            }
                              map = new google.maps.Circle({
                              strokeColor: document.getElementById('color').value,
                                      strokeOpacity: 0.8,
                                      strokeWeight: 2,
                                      fillColor: document.getElementById('color').value,
                                      fillOpacity: 0.35,
                                      map: map,
                                      center: myLatLng,
                                      radius: Math.sqrt(120) * 100
                              });
                      }
                      else
                      {
                      map = new google.maps.Circle({
                      strokeColor: document.getElementById('color').value,
                              strokeOpacity: 0.8,
                              strokeWeight: 2,
                              fillColor: document.getElementById('color').value,
                              fillOpacity: 0.35,
                              map: map,
                              center: {lat: 42.364758, lng: - 71.10326},
                              radius: Math.sqrt(120) * 100
                      });
                      }
                      
                      }

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
              
              document.getElementById('zip').value =zip;
              }
              }

              xmlHttp.open("GET", "time.jsp", true);
                      xmlHttp.send();
                     //loadAjax('resources/xml/zipCodedata.xml', myFunction);
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


             /* xmlDoc = xhttp.responseXML;
                      txt = "";
                      x = xmlDoc.getElementsByTagName("DATE");
                      y = xmlDoc.getElementsByTagName("ZIP");
                      z = xmlDoc.getElementsByTagName("AQI");
                      a = xmlDoc.getElementsByTagName("COLOR");
                    
                    
                      for (i = 0; i < x.length; i++) {
              txt = x[i].childNodes[0].nodeValue;
                      zip = y[i].childNodes[0].nodeValue;
                      if (document.getElementById('date').value == txt)
              {
              if (document.getElementById('zip').value == zip)
              {
              document.getElementById('color').value = a[i].childNodes[0].nodeValue;
                      document.getElementById('markerContent').value = Number(z[i].childNodes[0].nodeValue);
//                        document.getElementById('respRate').value = y[i].childNodes[0].nodeValue;
//                        document.getElementById('heartRate').value = z[i].childNodes[0].nodeValue;
//                        document.getElementById('bp').value = a[i].childNodes[0].nodeValue;*/
initMap();
              //}

              //break;
              }

             // }
              
             // }
              

          </script>
          <script async defer
                  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBs6JB-I8cDpVStSE_CHLGQpWbk8L2dL1w&callback=initMap">
          </script>
    <jsp:include page="footer.jsp" />
    <script>
   
        ajaxFunction();
        initMap();
        
        </script>
</body>
</html>
