<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.neu.dao.AddUserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Capture Vital Signs</title>
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
                            var json = JSON.parse(xmlHttp.responseText);
                            if(null == json.result )
                                {
                            	document.getElementById('submitButton').disabled=true;
                            	document.getElementById('respRate').value=json.respRate;
                            	document.getElementById('heartRate').value=json.heartRate;
                            	document.getElementById('bp').value=json.bp;
                            	document.getElementById('Weight').value=json.weight;
                            	document.getElementById('result').innerHTML="Entry already done for this date.";
                            	
                                }
                            else
                                {
                                	document.getElementById('submitButton').disabled=false;
                                	document.getElementById('result').innerHTML="";
                                	document.getElementById('respRate').value=0;
                                	document.getElementById('heartRate').value=0;
                                	document.getElementById('bp').value=0;
                                	document.getElementById('Weight').value=0;
                                }
                        }
                    }
                    var captureDate= document.getElementById('date').value;
                    
					var query = "checkEntry.htm" + "&captureDate=" + captureDate;
					//alert(query);
                    xmlHttp.open("POST", "checkEntry.htm", true);
                    xmlHttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
                    xmlHttp.send(query);
                }
                function showOther(){
                
               if( document.getElementById('symptoms').selectedIndex == 14)
               {
                   document.getElementById('otherDiv').style.display="block";
               }
               else
               {
                   document.getElementById('otherDiv').style.display="none";
               }
        
        
            }
            </script>
    </head>
    <body >
        <jsp:include page="headerLogin.jsp" />
        <div class="container">
        <div class="row">
         <div class="col-sm-4">
         </div>
         <div class="col-sm-5">
         <c:choose>
          <c:when test="${!empty successMessage}">
                <p style="color:red">${successMessage }</p>
            	</c:when>
            	<c:otherwise>
         <p id="result" style="color:red"></p>
         </c:otherwise>
         </c:choose>
         </div>
        </div>
                    <div class="row">
                <div class="col-sm-4">
                
                </div>
                <div class="col-sm-5">
                   <form:form role="form" action="captureVital.htm" method="post" commandName="vitals">
                    <div class="form-group">
                        <label for="date" >Date</label>
                        <form:input type="date" path ="captureDate" class="form-control" id="date" placeholder="Enter Date" value ="${serverTime }" required="true" onblur="ajaxFunction()"/>
                        <font color="red"><form:errors path="captureDate" /></font>
                    </div>
                    <div class="form-group">
                        <label for="RespRate">Respiration Rate</label>
                        <form:input type="number" path="respirationRate" class="form-control" id="respRate" placeholder="Respiration Rate" required="true"/>
                    <font color="red"><form:errors path="respirationRate" /></font>
                    </div>
                    <div class="form-group">
                        <label for="heartRate">Heart Rate</label>
                        <form:input type="number" path="heartRate" class="form-control" id="heartRate" placeholder="Heart Rate" required="true"/>
                   <font color="red"><form:errors path="heartRate" /></font>
                    </div>
                       <div class="form-group">
                        <label for="Blood Pressure">Blood Pressure</label>
                        <form:input type="number" path="sysBp" class="form-control" id="bp" placeholder="Blood Pressure" required="true"/>
                    <font color="red"><form:errors path="sysBp" /></font>
                    </div>
                       <div class="form-group">
                        <label for="weight">Weight</label>
                        <form:input type="number" path="weight" class="form-control" id="Weight" placeholder="Weight" required="true"/>
                   <font color="red"><form:errors path="weight" /></font>
                    </div>
                  
                       <div class="form-group">
                        <label for="Symptoms">Symptoms</label>
                         <form:select path="symptomsSelected" id="symptoms" onchange="showOther();" class="form-control"  multiple="multiple">
                            <c:forEach var="symptoms" items="${symptomsList}" >
                                <option value="${symptoms[0]}">${symptoms[1]}</option>
                            </c:forEach>
                        </form:select>
                      
                       
                       </div>
                       <div class="form-group" style="display:none" id="otherDiv">
                        <label for="otherSymptom">If Other</label>
                        <input type="text" name="other" class="form-control" id="other" placeholder="Other Symptoms" >
                    </div>
                   
                    <button type="submit" class="btn btn-default" id="submitButton">Submit</button>
                   </form:form>
                </div>
                <div class="col-sm-3">
                </div>
                
                
                
            </div>
            
            
            
            
            
            
        </div>
        
        <jsp:include page="footer.jsp" />
    </body>
    <script>
    document.getElementById('symptoms').selectedIndex=0;
    ajaxFunction();
    </script>
</html>
