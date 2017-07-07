package com.neu.business;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.AddUserDao;
import com.neu.exception.AdException;
import com.neu.pojo.Email;
import com.neu.pojo.Patient;
import com.neu.pojo.Person;
import com.neu.pojo.Useraccount;
import com.neu.pojo.Workrequest;

@Controller
public class DoctorController {

	@RequestMapping(value = "/searchPatient.htm", method = RequestMethod.POST)
	public ModelAndView searchPatient(@RequestParam(value = "firstNameSearch") String firstName,
			@RequestParam(value = "lastNameSearch") String lastName, HttpServletRequest req,
			HttpServletResponse response) {
		HttpSession session = req.getSession();
		Useraccount authenticatedUser =(Useraccount) session.getAttribute("authenticaedUser");
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		AddUserDao dao = new AddUserDao();
		inputMap.put("doctor", authenticatedUser.getPerson().getDoctor());
		inputMap.put("firstName", firstName);
		inputMap.put("lastName", lastName);
		try {
			List patientsList = dao.searchPatientsList(inputMap);
			return new ModelAndView("doctorHomePage","patientsList",patientsList);
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	@RequestMapping(value="/viewRequestDetailsDoctor.htm",method=RequestMethod.POST)
	public ModelAndView viewRequestDetails(@RequestParam(value = "requestId") String requestId, HttpServletRequest req,
			HttpServletResponse response)
	{
		AddUserDao dao = new AddUserDao();
		ModelAndView mv = new ModelAndView("viewRequestedDetails");
		try {
			Workrequest workReq =dao.fetchRequestDetails(requestId);
			String fileName = workReq.getFileName();
			if(null != fileName)
			{
				mv.addObject("attachment","yes");
				mv.addObject("fileName",workReq.getFileName());
				mv.addObject("location",workReq.getLocation());
			}
			
			
			Person person= workReq.getPersonBySenderId();
			String firstName =person.getFirstName();
			String lastName= person.getLastName();
			String name= lastName +", " + firstName;
			mv.addObject("senderName",name);
			mv.addObject("requestDate",workReq.getRequestDate());
			mv.addObject("message",workReq.getMessage());
			mv.addObject("requestId",requestId);
			mv.addObject("reply",workReq.getReply());
			mv.addObject("status",workReq.getStatus());
			
			
				return mv;
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@RequestMapping(value="/replyToRequest.htm",method=RequestMethod.POST)
	public ModelAndView sendReply(@RequestParam(value="requestId")String requestId,@RequestParam(value="reply")String reply,
	HttpServletRequest req,HttpServletResponse resp)
	{
		AddUserDao dao=new AddUserDao();
		try {
			dao.reply(requestId, reply);
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mv =getRequests(req,resp);
		mv.addObject("successMessage","Reply sent successfully");
		return mv;
		
	}
	@RequestMapping(value="/downloadAttachment.htm*",method=RequestMethod.GET)
	public ModelAndView downloadAttachment(@RequestParam(value="fileName")String fileName,
			@RequestParam(value="location")String location,HttpServletRequest request,HttpServletResponse resp)
	{
		File file = new File(location);
		String fullPath = location + File.separator +fileName;      
        File downloadFile = new File(fullPath);
        FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(downloadFile);
			// get MIME type of the file
	        String mimeType = request.getContentType();
	        if (mimeType == null) {
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
	        System.out.println("MIME type: " + mimeType);
	 
	        // set content attributes for the response
	        resp.setContentType(mimeType);
	        resp.setContentLength((int) downloadFile.length());
	 
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"",
	                downloadFile.getName());
	        resp.setHeader(headerKey, headerValue);
	 
	        // get output stream of the response
	        OutputStream outStream;
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	        outStream = resp.getOutputStream();
			while ((bytesRead = inputStream.read(buffer)) != -1) {
			    outStream.write(buffer, 0, bytesRead);
		} inputStream.close();
		outStream.close();

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
       
 
        // write bytes read from the input stream into the output stream
       
 
       
        
		
		return null;
		
	}
	
	@RequestMapping(value="/viewAttachment.htm*",method=RequestMethod.GET)
	public void viewAttachment(@RequestParam(value="fileName")String fileName,
			@RequestParam(value="location")String location,HttpServletRequest request,HttpServletResponse resp)
	{
		Desktop dk=Desktop.getDesktop();
		String fullPath = location + File.separator +fileName;    
		
		 // Open a file
		 try {
			dk.open(new File(fullPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/repliedList.htm",method=RequestMethod.GET)
	public ModelAndView getRepliedRequests(HttpServletRequest request,HttpServletResponse response)
	{
		Useraccount user =(Useraccount) request.getSession().getAttribute("authenticaedUser");
		Person person = user.getPerson();
		AddUserDao dao = new AddUserDao();
		List<Workrequest> requestList=dao.getRequestList(person,"Replied");
		List<ListOrderedMap> newList = new ArrayList<ListOrderedMap>();
		ModelAndView mv = new ModelAndView("repliedListDoctor");
		if(null != requestList && requestList.size()>0)
		{
			for(Workrequest workRequest : requestList){
			ListOrderedMap temp = new ListOrderedMap();
			temp.put("requestDate", workRequest.getRequestDate());
			temp.put("message", workRequest.getMessage());
			temp.put("requestId", workRequest.getRequestId());
			temp.put("senderFirstName", workRequest.getPersonBySenderId().getFirstName());
			temp.put("senderLastName", workRequest.getPersonBySenderId().getLastName());
			temp.put("senderId", workRequest.getPersonBySenderId().getPersonId());
			newList.add(temp);
			}
			mv.addObject("requestList",newList);
		}
		else
		{
			mv.addObject("errorMessage","No pending request found.");
		}
		
		mv.addObject("requestList",newList);
		return mv;
	}
	
	@RequestMapping(value="/requestList.htm",method=RequestMethod.GET)
	public ModelAndView getRequests(HttpServletRequest request,HttpServletResponse response)
	{
		Useraccount user =(Useraccount) request.getSession().getAttribute("authenticaedUser");
		Person person = user.getPerson();
		AddUserDao dao = new AddUserDao();
		List<Workrequest> requestList=dao.getRequestList(person,"Initiated");
		List<ListOrderedMap> newList = new ArrayList<ListOrderedMap>();
		ModelAndView mv = new ModelAndView("requestListDoctor");
		if(null != requestList && requestList.size()>0)
		{
			for(Workrequest workRequest : requestList){
			ListOrderedMap temp = new ListOrderedMap();
			temp.put("requestDate", workRequest.getRequestDate());
			temp.put("message", workRequest.getMessage());
			temp.put("requestId", workRequest.getRequestId());
			temp.put("senderFirstName", workRequest.getPersonBySenderId().getFirstName());
			temp.put("senderLastName", workRequest.getPersonBySenderId().getLastName());
			temp.put("senderId", workRequest.getPersonBySenderId().getPersonId());
			newList.add(temp);
			}
			mv.addObject("requestList",newList);
		}
		else
		{
			mv.addObject("errorMessage","No pending request found.");
		}
		
		mv.addObject("requestList",newList);
		return mv;
	}
	
	@RequestMapping(value = "/sendMail.htm", method = RequestMethod.POST)
	public ModelAndView sendEmailMethod(@ModelAttribute("email")Email email, BindingResult result, HttpServletRequest req,
			HttpServletResponse response) {
		System.out.println(req.getParameter("patientId"));
		String[] args = new String[4];
		args[0]= email.getTo();
		args[1]=email.getMsg();
		args[2]=email.getSubject();
		args[3]=email.getFrom();
		SendEmail.main(args);
		return new ModelAndView("email","successMessage","Message sent successfully!");

	}
	
	@RequestMapping(value = "/contact.htm", method = RequestMethod.POST)
	public ModelAndView openEmailPage(@ModelAttribute("email")Email email, BindingResult result,@RequestParam(value="patientId")String personId,HttpServletRequest req,HttpServletResponse resp)
	{
		AddUserDao dao = new AddUserDao();
		String finalEmailId ="";
		try {
			if(null != personId)
			{
		Patient patient = dao.getPatient(Long.valueOf(personId));
			String prefModeOfCom = patient.getPrefModeOfComm();
			String emailId = patient.getEmailId();
			Long mobileNo = patient.getPhoneNo();
			
			
			if(null != prefModeOfCom && prefModeOfCom.equalsIgnoreCase("email"))
			{
				if(null != emailId && !emailId.trim().isEmpty())
				{
					finalEmailId = emailId;
				}
			}
			else if(null !=mobileNo && mobileNo != 0)
			{
				EmailSendMethods send = new EmailSendMethods();
				finalEmailId =send.setEmailId(mobileNo);
			}
			email.setTo(finalEmailId);
			return new ModelAndView("email","finalEmailId",finalEmailId);
			}
			else
				return new ModelAndView("email");
			
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	@RequestMapping(value = "/reset.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView resetPatientList( HttpServletRequest req,
			HttpServletResponse response) {
		HttpSession session = req.getSession();
		Useraccount authenticatedUser =(Useraccount) session.getAttribute("authenticaedUser");
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		AddUserDao dao = new AddUserDao();
		inputMap.put("doctor", authenticatedUser.getPerson().getDoctor());
		
		try {
			List<Patient> patientsList = dao.getPatientsList(inputMap);
			
			return new ModelAndView("doctorHomePage","patientsList",patientsList);
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/viewPatientSummary.htm", method = RequestMethod.POST)
	public ModelAndView viewPatientSummary(@RequestParam(value = "patientId") String personId, HttpServletRequest req,
			HttpServletResponse response) {
		
		return new ModelAndView("patientSummaryPage");

	}

	@RequestMapping(value = "/viewVitalsDoctor.htm", method = RequestMethod.POST)
	public ModelAndView viewVitals(@RequestParam(value = "patientId") String personId, HttpServletRequest req,
			HttpServletResponse response) {

		HttpSession session = req.getSession();
		session.setAttribute("patientId", personId);
		ModelAndView mv=new ModelAndView("patientVitalDetails");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String formattedDate=formatter.format(date);
		mv.addObject("serverTime", formattedDate );
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -7);
		Date prevDate = c.getTime();
		String previousDate = formatter.format(prevDate);
		mv.addObject("prevDate", previousDate );
		mv.addObject("patient", personId);


		return mv;

	}

}
