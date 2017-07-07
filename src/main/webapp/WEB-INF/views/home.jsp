
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
                            //alert(xmlHttp.responseText);
                            document.getElementById('date').value = xmlHttp.responseText.trim();
                        }
                    }

                    xmlHttp.open("GET", "time.jsp", true);
                    xmlHttp.send();
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
    <body onload="ajaxFunction();">
        <jsp:include page="headerLogin.jsp" />
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                </div>
                <div class="col-sm-5">
                   <form role="form" action="captureVital">
                    <div class="form-group">
                        <label for="date" >Date</label>
                        <input type="date" name ="date" class="form-control" id="date" placeholder="Date" required>
                    </div>
                    <div class="form-group">
                        <label for="RespRate">Respiration Rate</label>
                        <input type="number" name="respRate" class="form-control" id="respRate" placeholder="Respiration Rate" required>
                    </div>
                    <div class="form-group">
                        <label for="heartRate">Heart Rate</label>
                        <input type="number" name="heartRate" class="form-control" id="heartRate" placeholder="Heart Rate" required>
                    </div>
                       <div class="form-group">
                        <label for="Blood Pressure">Blood Pressure</label>
                        <input type="number" name="bp" class="form-control" id="bp" placeholder="Blood Pressure" required>
                    </div>
                       <div class="form-group">
                        <label for="weight">Weight</label>
                        <input type="number" name="weight" class="form-control" id="Weight" placeholder="Weight" required>
                    </div>
                       <div class="form-group">
                        <label for="Symptoms">Symptoms</label>
                        <select class="form-control" id="symptoms" name="symptomsList" onchange="showOther();">
                            <option>None</option>
                            <option> Suffocation</option>
                            <option>Head Ache</option>
                            <option>Nausea</option>
                            <option>Dizziness</option>
                            <option>Dermatitis</option>
                            <option>Eye/Nose/Throat/Respiratory Irritation</option>
                            <option>Coughing</option>
                            <option>difficulty Concentrating</option>
                            <option>Sensitivity to Odors</option>
                            <option>Muscle pain</option>
                            <option>Infection</option>
                            <option>Fatigue</option>
                            <option>Allergy</option>
                            <option>Other</option>

                            </select>
                       
                       </div>
                       <div class="form-group" style="display:none" id="otherDiv">
                        <label for="otherSymptom">If Other</label>
                        <input type="text" name="other" class="form-control" id="other" placeholder="Other Symptoms" >
                    </div>
                   
                    <button type="submit" class="btn btn-default">Submit</button>
                   </form>
                </div>
                <div class="col-sm-3">
                </div>
                
                
                
            </div>
            
            
            
            
            
            
        </div>
        
        <jsp:include page="footer.jsp" />
    </body>
</html>
