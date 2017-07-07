package com.neu.business;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.neu.pojo.Person;
import com.neu.pojo.Useraccount;

import org.springframework.validation.ValidationUtils;
@Component
public class UserValidator implements Validator{
	

	

	

	
	

	    public boolean supports(Class aClass)
	    {
	        return aClass.equals(Useraccount.class);
	    }

	    public void validate(Object obj, Errors errors)
	    {
	    	Useraccount newAdvert = (Useraccount) obj;

	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.name", "Username required");
	    }
	

}

