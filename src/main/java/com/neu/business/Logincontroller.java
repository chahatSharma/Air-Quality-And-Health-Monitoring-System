package com.neu.business;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.AddUserDao;
import com.neu.exception.AdException;
import com.neu.pojo.Indoorairquality;
import com.neu.pojo.Outdoorairquality;
import com.neu.pojo.Patient;
import com.neu.pojo.Person;
import com.neu.pojo.Useraccount;

@Controller

public class Logincontroller {
	private static final Logger logger = LoggerFactory.getLogger(Logincontroller.class);
	@Autowired
	@Qualifier("userValidator")
	UserValidator userValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {

		binder.setValidator(userValidator);
	}

	@RequestMapping(value = "/userAccount.htm", method = RequestMethod.POST)
	protected ModelAndView login(@ModelAttribute("ua1") Useraccount ua, BindingResult result,
			HttpServletRequest request, HttpSession session) {

		userValidator.validate(ua, result);
		System.out.println(result);
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("login");
			return mv;
		}

		logger.info("PErson" + ua.getUserName());

		String userName = ua.getUserName();
		String password = ua.getPassword();

		AddUserDao dao = new AddUserDao();
		try {
			ModelAndView mv = new ModelAndView("loginHomePage");
			Person p = null;
			if (userName.equalsIgnoreCase("adminHead") && password.equalsIgnoreCase("adminHead")) {
				ModelAndView mvv = new ModelAndView("adminHomePage");
				return mvv;
			}
			Useraccount authenticatedUser = dao.get(userName, password);
			if (null != authenticatedUser)
				p = authenticatedUser.getPerson();
			if (p == null) {
				ModelAndView mv1 = new ModelAndView("login");
				mv1.addObject("errorMessage", "Incorrect UserName or password");
				return mv1;
			}

			dao.enterLoginDetails(authenticatedUser);
			session = request.getSession(true);
			String userRole = authenticatedUser.getRole();
			session.setAttribute("role", userRole);
			session.setAttribute("authenticaedUser", authenticatedUser);
			if (null != userRole && userRole.equalsIgnoreCase("Doctor")) {
				HashMap<String, Object> inputMap = new HashMap<String, Object>();
				inputMap.put("doctor", authenticatedUser.getPerson().getDoctor());
				List<Patient> patientsList = dao.getPatientsList(inputMap);
				session.setAttribute("name", p.getFirstName());
				System.out.println(patientsList.size());
				// List<Patient> newPatientList = new ArrayList<Patient>();
				// if(null != patientsList)
				// {
				// Iterator patientListIterator = patientsList.iterator();
				// while (patientListIterator.hasNext())
				// {
				//
				// }

				// }
				return new ModelAndView("doctorHomePage", "patientsList", patientsList);

			} else if (null != userRole && (userRole.equalsIgnoreCase("Mayor") || userRole.equalsIgnoreCase("PHO"))) {
				return new ModelAndView("mayorHomePage");
			} else {

				session.setAttribute("name", p.getFirstName());
				session.setAttribute("userId", authenticatedUser.getUserId());
				session.setAttribute("personid", p.getPersonId());
				System.out.println("p.getPatient()" + p.getPatient());
				session.setAttribute("patient", p.getPatient());
				

				mv.addObject("name", p.getFirstName());
				return mv;
			}
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ModelAndView mv = new ModelAndView("login");
			mv.addObject("errorMessage", "Incorrect UserName or password");
			return mv;

		}
	}

	@RequestMapping(value = "/loadAvg.htm", method = RequestMethod.GET)
	@ResponseBody
	protected void homePageAjaxCall(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Useraccount authenticatedUser = (Useraccount) session.getAttribute("authenticaedUser");
		AddUserDao dao = new AddUserDao();
		Patient patient = authenticatedUser.getPerson().getPatient();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();
		String todate = dateFormat.format(date);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		Date fromDate = cal.getTime();
		List<Indoorairquality> indoorAQIList;
		try {
			indoorAQIList = dao.getIndoorAQIAvg(patient, fromDate, date);
			int zip = authenticatedUser.getPerson().getAddressByHomeAddressId().getZipcode();
			List<Outdoorairquality> outDoorAqiList = dao.getOutdoorAQIAvg(zip, fromDate, date);

			if (null != indoorAQIList && indoorAQIList.size() > 0) {
				JSONObject obj = new JSONObject();
				Object[][] arraysIndoor = new Object[indoorAQIList.size()][2];
				Object[][] arraysOutdoor = new Object[indoorAQIList.size()][2];

				System.out.println("vsList" + indoorAQIList.size());

				for (int i = 0; i < indoorAQIList.size(); i++)

				{

					for (int j = 0; j < outDoorAqiList.size(); j++) {
						if (indoorAQIList.get(i).getCaptureDate().equals(outDoorAqiList.get(j).getCaptureDate())) {
							arraysIndoor[i][0] = indoorAQIList.get(i).getCaptureDate();
							arraysIndoor[i][1] = indoorAQIList.get(i).getAqi();

							arraysOutdoor[i][0] = outDoorAqiList.get(i).getCaptureDate();
							arraysOutdoor[i][0] = outDoorAqiList.get(i).getAqi();
						}
					}
					
					try {
						obj.put("dataset1", arraysIndoor);
						obj.put("dataset2", arraysOutdoor);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					PrintWriter out;
					try {
						out = response.getWriter();
						out.println(obj);
						System.out.println(obj);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

				}
			}
		} catch (AdException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	@RequestMapping(value = "/admPage.htm", method = RequestMethod.POST)
	protected ModelAndView addUserRoles(@ModelAttribute("ua1") Useraccount ua, BindingResult result,
			HttpServletRequest request, HttpSession session) {

		AddUserDao dao = new AddUserDao();
		HashMap<String, Object> inputmHashMap = new HashMap<String, Object>();
		inputmHashMap.put("ua", ua);
		String role = ua.getRole();
		String practiceNo = "";
		if (role.equalsIgnoreCase("Doctor")) {
			practiceNo = ua.getPerson().getDoctor().getPracticeNo();
			boolean exists = dao.checkIfDoctorExists(practiceNo);
			if (exists) {
				return new ModelAndView("adminHomePage", "errorMessage", "User already exists");
			}
		}
		inputmHashMap.put("role", role);
		dao.createAccountForRoles(inputmHashMap);
		return new ModelAndView("homePage");

	}

	

	@RequestMapping(value = "/signUp.htm", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("ua") Useraccount ua, BindingResult result) {

		return "signUp";
	}

}
