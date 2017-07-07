package com.neu.business;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.AddUserDao;
import com.neu.pojo.Doctor;
import com.neu.pojo.GoogleMap;
import com.neu.pojo.IndoorAQIView;
import com.neu.pojo.Indoorairquality;
import com.neu.pojo.Patient;
import com.neu.pojo.Useraccount;
import com.neu.pojo.Vitalsign;
import com.neu.pojo.Workrequest;

@Controller

public class SignUpController {
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);
	
	@RequestMapping(value = "/addFile.htm", method = RequestMethod.GET)
	protected ModelAndView handleRequestForAddFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("inside handleRequestForAddFile");
		HttpSession session = request.getSession();
		Useraccount user =(Useraccount) session.getAttribute("authenticaedUser");
		String practiceNo = user.getPerson().getPatient().getDoctorPracticeNo();
		AddUserDao dao = new AddUserDao();
		Doctor doctor =dao.findMappedDoctor(practiceNo);
		ModelAndView mv =new ModelAndView("addFile");
		if(null == doctor )
		{
			mv.addObject("errorMessage","Could not find a user for the given doctor.");
		}
		else
		{
		String docFirstName = doctor.getPerson().getFirstName();
		String docLastName = doctor.getPerson().getLastName();
		String docName= "Dear " +docFirstName + " " +docLastName;
		mv.addObject("docName",docName);
		mv.addObject("docId",doctor.getPerson().getPersonId());
		}
		
		
		return mv;

	}
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mv = new ModelAndView("logout");
		mv.addObject("successMessage", "User Logged Out Successfull");
		HttpSession session = request.getSession();
		session.invalidate();
		return mv;

	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	protected ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("inside login in signupcontroller");
		ModelAndView mv = new ModelAndView("login");
		return mv;

	}

	@RequestMapping(value = "/loginHomePage.htm", method = RequestMethod.GET)
	protected ModelAndView handleIncomingRequest(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView mv = new ModelAndView("loginHomePage");
		return mv;
	}
	
	@RequestMapping(value = "/viewIndoorAQI.htm", method = {RequestMethod.GET,RequestMethod.POST})
	protected ModelAndView handleIncomingRequestForIndoor(@ModelAttribute(value="indoorAQIView" )IndoorAQIView view,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fromJsp =request.getParameter("fromJsp");
		Date date=null;
		double aqi =0;
		String respPol ="";
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView("viewIndoorAQI");
				Useraccount user = (Useraccount) session.getAttribute("authenticaedUser");
				Patient patient =user.getPerson().getPatient();
		if(null != view && null != fromJsp &&fromJsp.equalsIgnoreCase("true"))
		{
			date = view.getDate();
		}
		else
		{
			
			date = new Date();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			String formattedDate=formatter.format(date);
			mv.addObject("serverTime", formattedDate );
			
		}
		AddUserDao dao = new AddUserDao();
		Indoorairquality indoorAQI = dao.getIndoorAqi(date, patient);
		if(null != indoorAQI)
		{
			aqi=indoorAQI.getAqi();
			respPol = indoorAQI.getRepPollutant();
		}
		
		view.setDate(date);
		view.setAqi(aqi);
		view.setRespPoll(respPol);
		
		
		return mv;
	}

	@RequestMapping(value = "/viewOutDoorAQI.htm", method = RequestMethod.GET)
	protected ModelAndView handleIncomingRequestForOutdoor(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("inside view outdoor");
		ModelAndView mv = new ModelAndView("viewOutDoorAQI");
		return mv;
	}

	@RequestMapping(value = "/viewVitalSign.htm", method = RequestMethod.GET)
	protected ModelAndView handleIncomingRequestForViewVitals(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView mv = new ModelAndView("viewVitalSign");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String formattedDate=formatter.format(date);
		mv.addObject("serverTime", formattedDate );
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -7);
		Date prevDate = c.getTime();
		String previousDate = formatter.format(prevDate) ;
		mv.addObject("prevDate", previousDate );
		return mv;
	}

	@RequestMapping(value = "/viewOutdoor.htm", method = {RequestMethod.GET,RequestMethod.POST})
	protected ModelAndView viewOutDoorPost(@ModelAttribute("googleMap") GoogleMap gm, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Date date = null;
		String zip = "";
		float lat = 0;
		ModelAndView mv = new ModelAndView("results");
		float lng = 0;
		
		System.out.println("11111111111"+request.getParameter("initialize"));
 System.out.println("gm"+gm);
		if (null != gm && null == request.getParameter("initialize") ) {
			date = gm.getDate();
			zip = gm.getZip();
		} else {
			HttpSession session = request.getSession();
			Useraccount user = (Useraccount) session.getAttribute("authenticaedUser");
			System.out.println("user"+user);
			if (null != user) {
				Integer zipInt = user.getPerson().getAddressByHomeAddressId().getZipcode();
System.out.println("user not null");
				date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String formattedDate=formatter.format(date);
				mv.addObject("serverTime", formattedDate );
				if (null != zipInt && zipInt != 0)
					zip = String.valueOf(zipInt);
				
				
				
				
			}
		}
		if (null == zip || zip.isEmpty())
			zip = "02139";
		GoogleResponse res = new AddressConverter().convertToLatLong(zip);
		if (res.getStatus().equals("OK")) {
			for (Result result1 : res.getResults()) {

				// String imageUrl =
				// "https://maps.googleapis.com/maps/api/staticmap?center="
				// + result.getGeometry().getLocation().getLat() + "," +
				// result.getGeometry().getLocation().getLng() +
				// "&zoom=11&size=612x612&scale=2&maptype=hybrid&path=color:red|weight:1|fillcolor:yellow";

				lat = Float.valueOf(result1.getGeometry().getLocation().getLat());
				lng = Float.valueOf(result1.getGeometry().getLocation().getLng());
				System.out.println(lat);
				System.out.println(lng);
			}
			AddUserDao dao = new AddUserDao();
			double aqi = dao.getOutdoorAqi(zip, date);
			gm.setAqi(aqi);
			System.out.println(zip);
			
			mv.addObject("lat", lat);
			mv.addObject("lng", lng);
			mv.addObject("zip", zip);
			mv.addObject("date", date);
			mv.addObject("aqi",aqi);
			gm.setDate(date);
			gm.setZip(zip);
			int color=0;
			 if (0 <= aqi && aqi <= 50) {
				 color=0;
             }
             if (50 < aqi && aqi <= 100) {
            	 color=1;
             }
             if (100 < aqi && aqi <= 150) {
            	 color=2;
             }
             if (150 < aqi && aqi <= 200) {
            	 color=3;
             }
             if (200 < aqi && aqi <= 300) {
            	 color=4;
             }
             if (300 < aqi) {
            	 color=5;
             }
			mv.addObject("color",color);
			return mv;

		}
		return null;
	}

	@RequestMapping(value = "/validate.htm*", method = RequestMethod.POST)
	@ResponseBody
	protected void handleAjaxCall(String userName,String update, HttpServletResponse response,HttpServletRequest req) {
		AddUserDao dao = new AddUserDao();
		JSONObject obj = new JSONObject();
		boolean exists = dao.checkIfUserExists(userName);

		ModelAndView mv = new ModelAndView("signUp");
		

		try {
			if (exists)
				obj.put("exists", "yes");
			else
				obj.put("exists", "no");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		if(null != update && !update.trim().isEmpty())
		{
			HttpSession session = req.getSession();
			Useraccount authenticatedUser = (Useraccount) session.getAttribute("authenticaedUser");
			String previousUserName = authenticatedUser.getUserName();
			if(previousUserName.equalsIgnoreCase(userName))
			{
				try {
					obj.put("exists", "no");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		
	}
}
