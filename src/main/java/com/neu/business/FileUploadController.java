package com.neu.business;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.AddUserDao;
import com.neu.exception.AdException;
import com.neu.pojo.Person;
import com.neu.pojo.Useraccount;
import com.neu.pojo.Workrequest;

/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@RequestMapping(value="/viewReqListUser.htm",method=RequestMethod.GET)
	public ModelAndView viewReqListUser(HttpServletRequest req,HttpServletResponse response)
	{
		AddUserDao dao = new AddUserDao();
		Useraccount ua =(Useraccount) req.getSession().getAttribute("authenticaedUser");
		Person person =ua.getPerson();
		List<Workrequest> reqList=dao.getRequestWorklistForUser(person);
		List<ListOrderedMap> newList = new ArrayList<ListOrderedMap>();
		ModelAndView mv = new ModelAndView("requestListUser");
		if(null != reqList && reqList.size()>0)
		{
			for(Workrequest workRequest : reqList){
			ListOrderedMap temp = new ListOrderedMap();
			temp.put("requestDate", workRequest.getRequestDate());
			temp.put("message", workRequest.getMessage());
			temp.put("requestId", workRequest.getRequestId());
			if(null != workRequest.getReply() && !workRequest.getReply().trim().isEmpty())
			{
			temp.put("replied","Yes");
			temp.put("repliedDate", workRequest.getModifiedTime());
			}
			else
			{
				temp.put("replied","No");
				temp.put("repliedDate", "NA");
			}
			
			newList.add(temp);
			}
			mv.addObject("requestList",newList);
		}
		else
		{
			mv.addObject("errorMessage","No request made.");
		}
		return mv;
	}
	@RequestMapping(value = "/uploadFile.htm", method = RequestMethod.POST)
	public @ResponseBody ModelAndView uploadFileHandler(@RequestParam("name") String name,
			@RequestParam("message") String message, @RequestParam("file") MultipartFile file,
			@RequestParam("docId") String docId, @RequestParam("fileUpload") String fileUpload,
			HttpServletRequest request) {
		
		if(null != file && file.getSize() >100000)
		{
			return new ModelAndView("error");
		}
		
		HashMap<String, Object> inputmap = new HashMap<String, Object>();
		Useraccount user = (Useraccount) request.getSession().getAttribute("authenticaedUser");
		AddUserDao dao = new AddUserDao();
		ModelAndView mv = new ModelAndView("addFile");
		mv.addObject("docId",docId);
		System.out.println(fileUpload);
		if (null != fileUpload && fileUpload.trim().equalsIgnoreCase("yes")) {System.out.println(fileUpload);
			if (!file.isEmpty()) {
				
				try {System.out.println(fileUpload);

					byte[] bytes = file.getBytes();

					System.out.println(file.getOriginalFilename());
					System.out.println(file.getContentType());

					// Creating the directory to store file
					String rootPath = System.getProperty("catalina.home");
					File dir = new File(rootPath + File.separator + "tmpFiles");
					String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
							file.getOriginalFilename().length());
					if (!dir.exists())
						dir.mkdirs();
					String fileName = name + extension;
					File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					inputmap.put("file", file);
					inputmap.put("fileName", fileName);
					inputmap.put("location", dir.getAbsolutePath());
					inputmap.put("message", message);
					inputmap.put("user", user);
					inputmap.put("doctor", docId);
					
					long requestId = dao.registerRequestByUser(inputmap);
					logger.info("Server File Location=" + serverFile.getAbsolutePath() + requestId);
					
					if (0 != requestId) {
						mv.addObject("successMessage", "request registered successfully with request No" + requestId);
						return mv;
					} else {
						mv.addObject("errorMessage", "Error generating request. Please try later");
						return mv;
					}

				} catch (IOException e) {
					return mv.addObject("errorMessage",
							"Failed to upload file:" + name + " as> " + e.getMessage());
				}
			} else {
				return mv.addObject("errorMessage",
						"Failed to upload " + name + " because the file was empty.");
			}
		} else {
			System.out.println(fileUpload);
			inputmap.put("file", null);
			inputmap.put("fileName", "NA");
			inputmap.put("location", "NA");
			inputmap.put("message", message);
			inputmap.put("user", user);
			inputmap.put("doctor", docId);
			
			
				long requestId = dao.registerRequestByUser(inputmap);
				if (0 != requestId) {
					mv.addObject("successMessage", "request registered successfully with request No" + requestId);
					return mv;
				} else {
					mv.addObject("errorMessage", "Error generating request. Please try later");
					return mv;
				}
			

		}
		
	}
	
	
	@RequestMapping(value="/viewRequestDetailsUser.htm",method=RequestMethod.POST)
	public ModelAndView viewRequestDetails(@RequestParam(value = "requestId") String requestId, HttpServletRequest req,
			HttpServletResponse response)
	{
		AddUserDao dao = new AddUserDao();
		ModelAndView mv = new ModelAndView("viewRequestedDetailsUser");
		try {
			Workrequest workReq =dao.fetchRequestDetails(requestId);
			String fileName = workReq.getFileName();
			if(null != fileName)
			{
				mv.addObject("attachment","yes");
				mv.addObject("fileName",workReq.getFileName());
				mv.addObject("location",workReq.getLocation());
			}
			
			
			Person person= workReq.getPersonByReceiverId();
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
	
}
