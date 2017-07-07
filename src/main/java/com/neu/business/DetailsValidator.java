package com.neu.business;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.pojo.Useraccount;
@Component
public class DetailsValidator implements Validator {

	private Pattern pattern;
	private Matcher matcher;

	public boolean supports(Class aClass) {
		return aClass.equals(Useraccount.class);
	}

	public void validate(Object obj, Errors errors) {
		// person.dob
System.out.println("inside detail Validator");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.firstName", "error.invalid.firstname",
				"First Name is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.lastName", "error.invalid.lastname",
				"Last Name is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.emailId", "error.invalid.emailID",
				"Email Id is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.addressByHomeAddressId.addressLine1",
				"error.invalid.addressLine1", "Address Line is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.addressByHomeAddressId.addressLine2",
				"error.invalid.addressLine2", "Address Line1 is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.addressByHomeAddressId.city", "error.invalid.city",
				"City is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.addressByHomeAddressId.state", "error.invalid.state",
				"State is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.addressByHomeAddressId.zipcode", "error.invalid.zip",
				"ZipCode is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.addressByHomeAddressId.phoneNo",
				"error.invalid.phoneNo", "Phone No is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.patient.bloodGroup", "error.invalid.bloodGrp",
				"Blood group is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.patient.reportingZipCode",
				"error.invalid.reportingZip", "Reporting Zipcode is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.userName",
				"User name is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password is Required");

		Useraccount ua = (Useraccount) obj;
		if (null != ua.getUserName()) {
			pattern = Pattern.compile(Constants.USERNAME_PATTERN);
			matcher = pattern.matcher(ua.getUserName());
			if (!matcher.matches()) {
				errors.rejectValue("userName", "userName.incorrect", "Enter a valid userName(3-15 characters), @ not allowed");
			}
		}
		if (null != ua.getPassword()) {
			if(ua.getPassword().trim().length() < 8 )
				errors.rejectValue("password", "password.incorrect", "Minimum 8 charaters allowed");
			}
		if (null != ua.getPerson().getEmailId()) {
			pattern = Pattern.compile(Constants.EMAIL_PATTERN);
			matcher = pattern.matcher(ua.getPerson().getEmailId());
			if (!matcher.matches()) {
				errors.rejectValue("person.emailId", "person.emailId.incorrect", "Enter a valid Email Id");
			}
		}
		
		if (null != ua.getPerson().getPatient().getEmailId()) {
			pattern = Pattern.compile(Constants.EMAIL_PATTERN);
			matcher = pattern.matcher(ua.getPerson().getPatient().getEmailId());
			if (!matcher.matches()) {
				errors.rejectValue("person.patient.emailId", "person.emailId.incorrect", "Enter a valid Email Id");
			}
		}
		
		if (null != ua.getPerson().getPatient().getPhoneNo()) {
			pattern = Pattern.compile(Constants.MOBILE_PATTERN);
			matcher = pattern.matcher(String.valueOf(ua.getPerson().getPatient().getPhoneNo()));
			if (!matcher.matches()) {
				errors.rejectValue("person.patient.phoneNo", "person.phone.incorrect", "Enter a valid Phone no");
			}
		}
		
		if (null != ua.getPerson().getAddressByHomeAddressId().getPhoneNo()) {
			pattern = Pattern.compile(Constants.MOBILE_PATTERN);
			matcher = pattern.matcher(String.valueOf(ua.getPerson().getAddressByHomeAddressId().getPhoneNo()));
			if (!matcher.matches()) {
				errors.rejectValue("person.addressByHomeAddressId.phoneNo", "phone.incorrect", "Enter a valid Phone no");
			}
		}
		
//		if (null != ua.getPerson().getPatient().getReportingZipCode()) {
//			pattern = Pattern.compile(Constants.ZIP_CODE_PATTERN);
//			matcher = pattern.matcher(String.valueOf(ua.getPerson().getPatient().getReportingZipCode()));
//			if (!matcher.matches()) {
//				errors.rejectValue("person.patient.reportingZipCode", "zipode.incorrect", "Enter a valid zipCode");
//			}
//		}
//		
//		if (null != ua.getPerson().getAddressByHomeAddressId().getZipcode()) {
//			pattern = Pattern.compile(Constants.ZIP_CODE_PATTERN);
//			matcher = pattern.matcher(String.valueOf(ua.getPerson().getAddressByHomeAddressId().getZipcode()));
//			if (!matcher.matches()) {
//				errors.rejectValue("person.addressByHomeAddressId.zipcode", "zipCode.incorrect", "Enter a valid zipCode");
//			}
//		}
		
		if(null != ua.getPerson().getDob())
		{
			Date currDate = new Date();
			if(ua.getPerson().getDob().after(currDate))
			{
				errors.rejectValue("person.dob", "dob.incorrect", "Date cannot be later than todays' Date");
				
			}
		}
		
		if(null != ua.getPerson().getFirstName())
			pattern = Pattern.compile(Constants.ALPHANUMERIC_PATTERN);
		matcher = pattern.matcher(ua.getPerson().getFirstName());
		if(!matcher.matches())
		{
			errors.rejectValue("person.firstName", "name.incoorect","Enter valid name");
		}
		
	}

}
