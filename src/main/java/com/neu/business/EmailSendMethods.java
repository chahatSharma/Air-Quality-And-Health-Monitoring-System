package com.neu.business;

public class EmailSendMethods {
	
	public void sendEmail(String emailId,  String personName, int aqi) {

        String[] args = new String[4];
        args[0] = emailId;
        String specialMsg = "";
        if(aqi == 2 || aqi ==3)
        specialMsg = "Unhealthy";
        else if( aqi ==4)
            specialMsg = "Very Unhealthy";
        else
            specialMsg = "Hazardous";
        
        //StringBuilder text = new StringBuilder();
        String formattedText = "The air quality is" + specialMsg;
        String text= "";
               if(aqi ==2 || aqi ==3 )
        { 
              text=  
                "You may try opening the window or increase ventilation rate \"If the problem still persists, please feel free to contact us" + System.lineSeparator()
                + "Please check your dashboard for more details";
        }
               else if(aqi >= 4)
        {
            text="Please leave the place immediately Our representative is getting in touch with you soon to ensure your safety";
        }
               
        String salutation = "Dear " + personName + System.lineSeparator() + formattedText + text;
                

        
        args[1] = salutation;
        args[3]="admin@mayorOffice.com";
        args[2] = "Alert Message";
        
        SendEmail.main(args);
    }
	
	 public String setEmailId(long mobileNo)
	    {
	        String email = "";
	        
	            email = String.valueOf(mobileNo) + "@cingularme.com";
	        
	           // email=email+","+String.valueOf(mobileNo) + "@tmomail.net";
	            return email;
	        
	    }

}
