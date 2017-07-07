package com.neu.business;

	

	import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
	import org.springframework.validation.Errors;
	import org.springframework.validation.Validator;

	import com.neu.pojo.Person;
	import com.neu.pojo.Useraccount;
import com.neu.pojo.Vitalsign;

import org.springframework.validation.ValidationUtils;

	@Component
	public class VitalSignValidator implements Validator{
		

		
		private Pattern pattern;
		private Matcher matcher;
		

		
		

		    public boolean supports(Class aClass)
		    {
		        return aClass.equals(Vitalsign.class);
		    }

		    public void validate(Object obj, Errors errors)
		    {
		    	Vitalsign vs = (Vitalsign) obj;
		    	//captureDate
		        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "respirationRate", "error.invalid.respRate", "Required field");
		        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "heartRate", "error.invalid.respRate", "Required field");
		        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "error.invalid.respRate", "Required field");
		        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sysBp", "error.invalid.respRate", "Required field");
		        
		        if(null != vs.getCaptureDate())
				{
					Date currDate = new Date();
					if(vs.getCaptureDate().after(currDate))
					{
						errors.rejectValue("captureDate", "dob.incorrect", "Date cannot be later than todays' Date");
						
					}
				}
		        if (null != vs.getRespirationRate()) {
					pattern = Pattern.compile(Constants.ID_PATTERN);
					matcher = pattern.matcher(String.valueOf(vs.getRespirationRate()));
					if (!matcher.matches()) {
						errors.rejectValue("respirationRate", "respirationRate.incorrect", "Enter a valid number");
					}
				}
		        if (null != vs.getHeartRate()) {
					pattern = Pattern.compile(Constants.ID_PATTERN);
					matcher = pattern.matcher(String.valueOf(vs.getHeartRate()));
					if (!matcher.matches()) {
						errors.rejectValue("heartRate", "respirationRate.incorrect", "Enter a valid number");
					}
				}
		        if (null != vs.getWeight()) {
					pattern = Pattern.compile(Constants.ID_PATTERN);
					matcher = pattern.matcher(String.valueOf(vs.getWeight()));
					if (!matcher.matches()) {
						errors.rejectValue("weight", "respirationRate.incorrect", "Enter a valid number");
					}
				}
		        if (null != vs.getSysBp()) {
					pattern = Pattern.compile(Constants.ID_PATTERN);
					matcher = pattern.matcher(String.valueOf(vs.getSysBp()));
					if (!matcher.matches()) {
						errors.rejectValue("sysBp", "respirationRate.incorrect", "Enter a valid number");
					}
				}
		      
		    }
		

	}

