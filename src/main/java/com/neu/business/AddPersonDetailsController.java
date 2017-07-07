package com.neu.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.AddUserDao;
import com.neu.exception.AdException;
import com.neu.pojo.Person;
import com.neu.pojo.Useraccount;

@Controller

public class AddPersonDetailsController {
	private static final Logger logger = LoggerFactory.getLogger(AddPersonDetailsController.class);
	@Autowired
	@Qualifier("detailsValidator")
	DetailsValidator detailValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(detailValidator);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		
	}

	@RequestMapping(value = "/addDetails.htm", method = RequestMethod.POST)
	protected ModelAndView doSubmitAction(@ModelAttribute("ua") Useraccount ua, BindingResult result,
			HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("login");

		 detailValidator.validate(ua, result);
		if (result.hasErrors()) {
			mv = new ModelAndView("signUp");
			return mv;
		}
		Person person = ua.getPerson();
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		String update = request.getParameter("update");
		logger.info("username properties" + request.getParameter("update"));
		if (null != update && update.trim().equalsIgnoreCase("update")) {
			HttpSession session = request.getSession();
			Useraccount authenticatedUser = (Useraccount) session.getAttribute("authenticaedUser");

			inputMap.put("update", (String) request.getParameter("update"));
System.out.println(ua.getUserName() + "ua.getUserName()");
System.out.println(authenticatedUser.getUserName() + "authenticatedUser.getUserName()");
			

			authenticatedUser.setUserName(ua.getUserName());
			authenticatedUser.setPassword(ua.getPassword());
			authenticatedUser.getPerson().setDob(person.getDob());
			authenticatedUser.getPerson().setEmailId(person.getEmailId());
			authenticatedUser.getPerson().setFirstName(person.getFirstName());
			authenticatedUser.getPerson().setLastName(person.getLastName());
			authenticatedUser.getPerson().setUseraccount(authenticatedUser);
			authenticatedUser.getPerson().getPatient().setBloodGroup(person.getPatient().getBloodGroup());
			authenticatedUser.getPerson().getPatient().setDoctorFirstName(person.getPatient().getDoctorFirstName());
			authenticatedUser.getPerson().getPatient().setDoctorPracticeNo(person.getPatient().getDoctorPracticeNo());
			authenticatedUser.getPerson().getPatient().setDoctorLastName(person.getPatient().getDoctorLastName());
			authenticatedUser.getPerson().getPatient().setEmailId(person.getPatient().getEmailId());
			authenticatedUser.getPerson().getPatient().setPrefModeOfComm(person.getPatient().getPrefModeOfComm());
			authenticatedUser.getPerson().getPatient().setPhoneNo(person.getPatient().getPhoneNo());
			authenticatedUser.getPerson().getPatient().setReportingZipCode(person.getPatient().getReportingZipCode());
			authenticatedUser.getPerson().getPatient().setPerson(authenticatedUser.getPerson());
			authenticatedUser.getPerson().setAddressByHomeAddressId(person.getAddressByHomeAddressId());

			inputMap.put("authenticatedUser", authenticatedUser);
			//mv = new ModelAndView("signUp", "successMessage", "User Account updated successfully");
			
				mv = new ModelAndView("logout", "successMessage", "UserAccount updated successfully, Please relogin");
			
			mv.addObject("ua", authenticatedUser);
			mv.addObject("update","update");
		} else {
			inputMap.put("firstName", person.getFirstName());
			inputMap.put("lastName", person.getLastName());
			inputMap.put("dob", person.getDob());
			inputMap.put("emailId", person.getEmailId());
			inputMap.put("homeAddressId", person.getAddressByHomeAddressId());
			inputMap.put("person", person);
			inputMap.put("patient", person.getPatient());
			Set<Useraccount> userSet = new HashSet<Useraccount>();
			// Useraccount ua = new Useraccount();
			// ua.setUserName(person.getUserName());
			// ua.setPassword(person.getPassword());
			ua.setRole("User");
			userSet.add(ua);
			// Iterator<Useraccount> it = userSet.iterator();
			// Useraccount useraccounts = null;
			//
			// while(it.hasNext())
			// {
			// useraccounts=it.next();
			// System.out.println(useraccounts.getUserName());
			// break;
			// }
			inputMap.put("useraccounts", userSet);
			inputMap.put("mappedUserAccount", ua);

		}
		AddUserDao dao = new AddUserDao();
		try {
			Person p = dao.create(inputMap);
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mv;
	}

	@RequestMapping(value = "/addDetails.htm", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("person") Person person, BindingResult result) {

		return "signUp";
	}

	@RequestMapping(value = "/myAccount.htm", method = RequestMethod.GET)
	public String myAccount(Model model, HttpServletRequest req) {

		HttpSession session = req.getSession();
		Useraccount ua = (Useraccount) session.getAttribute("authenticaedUser");
		// ua1.setUserName(ua.getUserName());
		// ua1.setPerson(ua.getPerson());
		// ua1.setUserId(ua.getUserId());
		// ua1.setPassword(ua.getPassword());

		logger.info("inside myaccount" + ua.getCreationTime());
		model.addAttribute("ua", ua);
		model.addAttribute("update", "update");
		model.addAttribute("personId", ua.getPerson().getPersonId());
		return "signUp";
	}
}
