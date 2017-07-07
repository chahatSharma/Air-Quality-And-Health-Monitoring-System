package com.neu.business;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.hibernate.util.ArrayHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.neu.dao.AddUserDao;
import com.neu.exception.AdException;
import com.neu.pojo.Indoorairquality;
import com.neu.pojo.Patient;
import com.neu.pojo.Person;
import com.neu.pojo.Vitalsign;
import com.neu.springview.PdfReportView;

@Controller
public class CaptureVitalsController {
	@Autowired
	@Qualifier("vitalSignValidator")
	VitalSignValidator vitalSignValidator;
	
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(vitalSignValidator);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/captureVital.htm", method = RequestMethod.POST)
	public ModelAndView captureVital(@ModelAttribute("vitals") Vitalsign vs, BindingResult result,
			HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("CaptureVitals");
		vitalSignValidator.validate(vs, result);
		if (result.hasErrors()) {
			
			return mv;
		}
		HttpSession session = req.getSession();
		System.out.println("CaptureDate" + vs.getCaptureDate());

		Patient patient = (Patient) session.getAttribute("patient");
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("patient", patient);

		AgeCalculator calculate = new AgeCalculator();
		int age = calculate.calculateAge(patient.getPerson().getDob());
		CheckVitalSignNormality cvs = new CheckVitalSignNormality();
		String conclusion = cvs.checkVitalSign(vs, age);
		vs.setConclusion(conclusion);
		inputMap.put("vitalSign", vs);

		AddUserDao dao = new AddUserDao();
		dao.addVitalSignsAndSymptoms(inputMap);
		
		List symptomsList;
		try {
			symptomsList = dao.list();
			mv.addObject("symptomsList", symptomsList);
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mv.addObject("successMessage", "Vital Signs successfully captured");
		return mv;
	}

	@RequestMapping(value = "/viewVitals.htm", method = RequestMethod.GET)
	public ModelAndView viewVital(@ModelAttribute("vitals") Vitalsign vs, BindingResult result, HttpServletRequest req,
			HttpServletResponse response) {
		System.out.println("inside viewVital");
		ModelAndView mv = new ModelAndView("viewVitalSign");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String formattedDate=formatter.format(date);
		mv.addObject("serverTime", formattedDate );
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -7);
		Date prevDate = c.getTime();
		String previousDate = formatter.format(prevDate);
		mv.addObject("prevDate", previousDate );
		return mv;
	}

	@RequestMapping(value = "/captureVitals.htm", method = RequestMethod.GET)
	public ModelAndView initializeForm(@ModelAttribute("vitals") Vitalsign vs, BindingResult result) {

		AddUserDao dao = new AddUserDao();
		try {
			List symptomsList = dao.list();
			ModelAndView mv = new ModelAndView("CaptureVitals");
			mv.addObject("symptomsList", symptomsList);
			Date date = new Date();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate=formatter.format(date);
			//String formattedDate = dateFormat.format(date);
			
			mv.addObject("serverTime", formattedDate );
			return mv;
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// pageContext.setAttribute("symptomsList", symptomsList);

		return new ModelAndView("CaptureVitals");
		// ("viewAdverts", "adverts", advList);

	}

	@RequestMapping(value = "/checkEntry.htm", method = RequestMethod.POST)
	@ResponseBody
	public void checkEntryDone(Date captureDate, HttpServletRequest req, HttpServletResponse response) {
		HttpSession session = req.getSession();
		Patient patient = (Patient) session.getAttribute("patient");
		JSONObject obj = new JSONObject();
		System.out.println("captureDate" + req.getAttribute("captureDate"));
		AddUserDao dao = new AddUserDao();
		Vitalsign vs = dao.checkVitalSignEntryDone(patient, captureDate);
		if (null == vs)
			try {
				obj.put("result", "no entry");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		else {
			try {
				obj.put("respRate", vs.getRespirationRate());
				obj.put("heartRate", vs.getHeartRate());
				obj.put("weight", vs.getWeight());
				obj.put("bp", vs.getSysBp());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

	@RequestMapping(value = "/report.pdf", method = RequestMethod.GET)
	public ModelAndView createReport(@RequestParam(value = "startDate") Date startDate,
			@RequestParam(value = "endDate") Date endDate, HttpServletResponse response, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String role = (String) session.getAttribute("role");
		AddUserDao dao = new AddUserDao();
		Patient patient=null;
		String firstName = "";
		String lastName="";
		if(null != role && role.equalsIgnoreCase("doctor"))
		{
			String patientId = (String) session.getAttribute("patientId");
			if(null != patientId)
			{
			try {
				patient =dao.getPatient(Long.valueOf(patientId));
				Person person = dao.getPersonDetails(patientId);
				firstName = person.getFirstName();
				lastName = person.getLastName();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AdException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
			
		}
		else
		{
		patient = (Patient) session.getAttribute("patient");
		Person person;
		try {
			person = dao.getPersonDetails(String.valueOf(patient.getPersonId()));
			firstName = person.getFirstName();
			lastName = person.getLastName();
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		List<Vitalsign> vsList=null;
		if(null != patient)
		{
			vsList = dao.getVitalSignList(patient, startDate, endDate);
		

		}
		
		HashMap<String, Object> model = new HashMap<String, Object>();
		model.put("vitalList", vsList);
		model.put("firstName", firstName);
		model.put("lastName", lastName);
		System.out.println(vsList);
		View view = new PdfReportView();

		return new ModelAndView(view, model);
			}
	
	@RequestMapping(value = "/viewPatient.htm*", method = RequestMethod.POST)
	@ResponseBody
	protected void handleAjaxCalls(Date startDate, Date endDate, HttpServletResponse response, HttpServletRequest req) {
		
		System.out.println("inside viewPatientDetails");
		HttpSession session = req.getSession();
		String patientId = (String) session.getAttribute("patientId");
		
		AddUserDao dao = new AddUserDao();
		if(null == patientId)
		{
			JSONObject obj = new JSONObject();

			
				
					
				
				PrintWriter out;
				try {
					try {
						obj.put("errorMessage", "No Person found");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out = response.getWriter();
					out.println(obj);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		long patientsId = Long.valueOf(patientId);
	
		Patient patient;
		try {
			patient = dao.getPatient(patientsId);
			String patientName = patient.getPerson().getLastName() + ", " + patient.getPerson().getFirstName(); 
			System.out.println(patient.getPersonId());
			//AddUserDao dao =new AddUserDao();
//			List vsList =dao.getVitalSignList(patient, startDate, endDate);
//			ModelAndView mv = new ModelAndView("patientVitalDetails","newList",vsList);
//			mv.addObject("patientName" ,patientName);
//			
			
			/*8888888888888888888888888888888888888888*/
			if (null == startDate || null == endDate) {
				Date currDate = new Date();
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, -7);
				Date prevDate = c.getTime();

				startDate = prevDate;
				endDate = currDate;
			}

			
			List<Vitalsign> vsList = dao.getVitalSignList(patient, startDate, endDate);
			List<ListOrderedMap> newList = new ArrayList<ListOrderedMap>();
			
			if (null != vsList && vsList.size() > 0) {
				JSONObject obj = new JSONObject();
				Object[][] arraysResp = new Object[vsList.size()][2];
				Object[][] arraysHeart = new Object[vsList.size()][2];
				Object[][] arrayWeight = new Object[vsList.size()][2];
				Object[][] arrayBp = new Object[vsList.size()][2];
				System.out.println("vsList" + vsList.size());
				try {
					for (int i = 0; i < vsList.size(); i++)

					{
						ListOrderedMap temp = new ListOrderedMap();
						temp.put("captureDate", vsList.get(i).getCaptureDate());
						temp.put("respRate", vsList.get(i).getRespirationRate());
						temp.put("heartRate",vsList.get(i).getHeartRate());
						temp.put("sysBp", vsList.get(i).getSysBp());
						temp.put("weight", vsList.get(i).getWeight());
						temp.put("conclusion", vsList.get(i).getConclusion());
						newList.add(temp);
						arraysResp[i][0] = vsList.get(i).getCaptureDate();
						arraysResp[i][1] = vsList.get(i).getRespirationRate();
						arraysHeart[i][0] = vsList.get(i).getCaptureDate();
						arraysHeart[i][1] = vsList.get(i).getHeartRate();
						arrayBp[i][0] = vsList.get(i).getCaptureDate();
						arrayBp[i][1] = vsList.get(i).getSysBp();
						arrayWeight[i][0] = vsList.get(i).getCaptureDate();
						arrayWeight[i][1] = vsList.get(i).getWeight();

						obj.put("datasetResp", arraysResp);
						obj.put("datasetHear", arraysHeart);
						obj.put("datasetBp", arrayBp);
						obj.put("datasetWeight", arrayWeight);

					}
					obj.put("newList", newList);
					obj.put("patientName", patientName);

					System.out.println("obj" + obj);
					PrintWriter out;
					out = response.getWriter();
					out.println(obj);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}

			else {

				JSONObject obj = new JSONObject();

				try {
					if (!startDate.before(endDate))
						obj.put("errorMessage", "End Date should be later than start Date");
					else
						obj.put("errorMessage", "No records found");
					PrintWriter out;
					try {
						out = response.getWriter();
						out.println(obj);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


		} catch (AdException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		
		/*88888888888888*/
		//return mv;
		
		
		
		
	}

	@RequestMapping(value = "/viewVitals.htm*", method = RequestMethod.POST)
	@ResponseBody
	protected void handleAjaxCall(Date startDate, Date endDate, HttpServletResponse response, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Patient patient = (Patient) session.getAttribute("patient");

		if (null == startDate || null == endDate) {
			Date currDate = new Date();
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -7);
			Date prevDate = c.getTime();

			startDate = prevDate;
			endDate = currDate;
		}

		AddUserDao dao = new AddUserDao();
		List<Vitalsign> vsList = dao.getVitalSignList(patient, startDate, endDate);

		if (null != vsList && vsList.size() > 0) {
			JSONObject obj = new JSONObject();
			Object[][] arraysResp = new Object[vsList.size()][2];
			Object[][] arraysHeart = new Object[vsList.size()][2];
			Object[][] arrayWeight = new Object[vsList.size()][2];
			Object[][] arrayBp = new Object[vsList.size()][2];
			System.out.println("vsList" + vsList.size());
			try {
				for (int i = 0; i < vsList.size(); i++)

				{

					arraysResp[i][0] = vsList.get(i).getCaptureDate();
					arraysResp[i][1] = vsList.get(i).getRespirationRate();
					arraysHeart[i][0] = vsList.get(i).getCaptureDate();
					arraysHeart[i][1] = vsList.get(i).getHeartRate();
					arrayBp[i][0] = vsList.get(i).getCaptureDate();
					arrayBp[i][1] = vsList.get(i).getSysBp();
					arrayWeight[i][0] = vsList.get(i).getCaptureDate();
					arrayWeight[i][1] = vsList.get(i).getWeight();

					obj.put("datasetResp", arraysResp);
					obj.put("datasetHear", arraysHeart);
					obj.put("datasetBp", arrayBp);
					obj.put("datasetWeight", arrayWeight);

				}

				System.out.println("obj" + obj);
				PrintWriter out;
				out = response.getWriter();
				out.println(obj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}

		else {

			JSONObject obj = new JSONObject();

			try {
				if (!startDate.before(endDate))
					obj.put("errorMessage", "End Date should be later than start Date");
				else
					obj.put("errorMessage", "No records found");
				PrintWriter out;
				try {
					out = response.getWriter();
					out.println(obj);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	@RequestMapping(value = "/viewIndoorAqi*", method = RequestMethod.GET)
//	@ResponseBody
//	protected void displayIndoorAQI(Date date,HttpServletResponse response, HttpServletRequest req) {
//		HttpSession session = req.getSession();
//		Patient patient = (Patient) session.getAttribute("patient");
//		
////		if(null == startDate || null == endDate)
////		{
////			Date currDate = new Date();
////			Calendar c = Calendar.getInstance();
////			c.add(Calendar.DATE, -7);
////			Date prevDate = c.getTime();
////	
////			startDate=prevDate;
////			endDate=currDate;
////		}
//			
//		AddUserDao dao = new AddUserDao();
//		List<Indoorairquality> vsList = dao.getIndoorAQI(patient,date);
//JSONObject obj = new JSONObject();
//		if(null != vsList && vsList.size()>1)
//		{
//			Double aqi=vsList.get(0).getAqi();
//			try {
//				obj.put("aqi", aqi);
//				obj.put("respPoll", vsList.get(0).getRepPollutant());
//				obj.put("meaning", "");
//				
//				//vsList.get(0).get
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			try {
//				PrintWriter out=response.getWriter();
//				out.println(obj);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//	}
//	
	@RequestMapping(value = "/viewGoogleMap*", method = RequestMethod.GET)
	@ResponseBody
	protected void displayOutdoorAQI(Date date,HttpServletResponse response, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Patient patient = (Patient) session.getAttribute("patient");
		

			
		AddUserDao dao = new AddUserDao();
		List<Indoorairquality> vsList = dao.getIndoorAQI(patient,date);
JSONObject obj = new JSONObject();
		if(null != vsList && vsList.size()>1)
		{
			Double aqi=vsList.get(0).getAqi();
			try {
				obj.put("aqi", aqi);
				obj.put("respPoll", vsList.get(0).getRepPollutant());
				obj.put("meaning", "");
				
				//vsList.get(0).get
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				PrintWriter out=response.getWriter();
				out.println(obj);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
//	@RequestMapping(value="/viewOutdoor.htm",method=RequestMethod.POST)
//	protected ModelAndView viewOutDoor(@RequestParam(value="zip")String zip,
//			@RequestParam(value="date")Date date,
//			HttpServletRequest request, 
//			HttpServletResponse response) throws Exception {
//		float lat=0;
//		float lng=0;
//		
//		GoogleResponse res = new AddressConverter().convertToLatLong(zip);
//        if (res.getStatus().equals("OK")) {
//            for (Result result : res.getResults()) {
//               
//               
////                    String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="
////                            + result.getGeometry().getLocation().getLat() + "," + result.getGeometry().getLocation().getLng() + "&zoom=11&size=612x612&scale=2&maptype=hybrid&path=color:red|weight:1|fillcolor:yellow";
//
//                    lat = Float.valueOf(result.getGeometry().getLocation().getLat());
//                    lng = Float.valueOf(result.getGeometry().getLocation().getLng());
//                    System.out.println(lat);
//                    System.out.println(lng);
//            }
//        }
//		ModelAndView mv = new ModelAndView("results");
//		mv.addObject("lat",lat);
//		mv.addObject("lng",lng);
//		mv.addObject("zip",zip);
//		return mv;
//	}

}
