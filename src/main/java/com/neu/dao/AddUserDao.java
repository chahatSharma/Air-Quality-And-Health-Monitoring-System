package com.neu.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.multipart.MultipartFile;

import com.neu.exception.AdException;
import com.neu.pojo.Address;
import com.neu.pojo.Doctor;
import com.neu.pojo.Indoorairquality;
import com.neu.pojo.Logindetails;
import com.neu.pojo.Mayor;
import com.neu.pojo.Outdoorairquality;
import com.neu.pojo.Patient;
import com.neu.pojo.Person;
import com.neu.pojo.Publichealthofficer;
import com.neu.pojo.Symptomslist;
import com.neu.pojo.Useraccount;
import com.neu.pojo.Vitalsign;
import com.neu.pojo.Workrequest;

public class AddUserDao extends DAO {

	public AddUserDao() {
	}
	public List<Workrequest> getRequestWorklistForUser(Person person)
	{
		begin();
		Criteria crit=getSession().createCriteria(Workrequest.class);
		crit.add(Restrictions.eq("personBySenderId", person));
		List<Workrequest> reqList=crit.list();
		commit();
		close();
		return reqList;
		
	}
	public void addAirQuality(Indoorairquality airQualitySample)
	{
		begin();
		getSession().save(airQualitySample);
		//airQualitySample.setPatient(patient);
		commit();
		close();
		
	}
	public List<Useraccount> getUserList()
	{
		begin();
		Criteria crit =getSession().createCriteria(Useraccount.class);
		List<Useraccount> list =crit.list();
		commit();
		close();
		return list;
	}
	
	public void reply(String requestId,String reply) throws AdException
	{
		try{
		begin();
		Query q =getSession().createQuery("update Workrequest set reply=:reply,status=:status where requestId=:requestId ");
		q.setString("reply", reply);
		q.setString("status", "Replied");
		q.setLong("requestId", Long.valueOf(requestId));
		q.executeUpdate();
		commit();
		close();
		}
		catch(HibernateException e)
		{
			rollback();
			throw new AdException("Error in replying",e);
		}
	}
	public Workrequest fetchRequestDetails(String requestID)throws AdException
	{
		try{
		begin();
		Criteria crit =getSession().createCriteria(Workrequest.class);
		crit.add(Restrictions.idEq(Long.valueOf(requestID)));
		Workrequest request=(Workrequest) crit.uniqueResult();
		close();
		return request;
		}
		catch(HibernateException e)
		{
			throw new AdException("Error finding request",e);
		}
	}
	
	public List<Workrequest> getRequestList(Person person,String status)
	{
		begin();
		Criteria crit=getSession().createCriteria(Workrequest.class);
		crit.add(Restrictions.eq("personByReceiverId", person));
		crit.add(Restrictions.eq("status", status));
		List<Workrequest> requestList=crit.list();
		close();
		return requestList;
	}

	public long registerRequestByUser(HashMap<String, Object> inputmap) {

		//try{
		begin();
		System.out.println(inputmap);
		String docId = (String) inputmap.get("doctor");
		Criteria c = getSession().createCriteria(Person.class);
		c.add(Restrictions.idEq(Long.valueOf(docId)));
		Person doctor = (Person) c.uniqueResult();

		Useraccount user = (Useraccount) inputmap.get("user");
		Person person = user.getPerson();
		if (doctor != null) {System.out.println("inside doctor not null");
			Workrequest workRequest = new Workrequest();
			
			workRequest.setMessage((String) inputmap.get("message"));
			workRequest.setPersonByReceiverId(doctor);
			workRequest.setPersonBySenderId(person);
			workRequest.setRequestDate(new Date());
			workRequest.setStatus("Initiated");
			workRequest.setUseraccount(user);
			
			if(null != inputmap.get("file")){
			
			workRequest.setFileName((String) inputmap.get("fileName"));
			workRequest.setLocation((String) inputmap.get("location"));
			
			}
			
			long requestId =(Long) getSession().save(workRequest);
			commit();
			close();
			return requestId;
		}
		else
			return 0;
		}
//		catch(HibernateException e)
//		{
//			
//		}
	//}

	public Doctor findMappedDoctor(String practiceNo) throws AdException {
		try {
			begin();
			Criteria crit = getSession().createCriteria(Doctor.class);
			crit.add(Restrictions.eq("practiceNo", practiceNo));
			Doctor doctor = (Doctor) crit.uniqueResult();

			return doctor;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("exception finding doctor", e);
		}
	}

	public Person getPersonDetails(String personId) throws AdException {
		try {
			begin();
			Criteria crit = getSession().createCriteria(Person.class);
			crit.add(Restrictions.idEq(Long.valueOf(personId)));
			Person person = (Person) crit.uniqueResult();
			commit();
			close();
			return person;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get person profile", e);
		}

	}

	public Useraccount get(String username, String password) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from Useraccount where userName = :username and password= :password");
			q.setString("username", username);
			q.setString("password", password);
			Useraccount user = (Useraccount) q.uniqueResult();
			Person person = null;
			if (null != user)
				person = user.getPerson();

			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get user " + username, e);
		}
	}

	public void createAccountForRoles(HashMap<String, Object> inputmHashMap) {
		begin();
		Useraccount ua = (Useraccount) inputmHashMap.get("ua");
		Person person = ua.getPerson();
		String role = (String) inputmHashMap.get("role");
		if (role.equalsIgnoreCase("Doctor")) {
			Doctor doctor = person.getDoctor();
			ua.setRole("Doctor");
			getSession().save(ua);
			long personId = (Long) getSession().save(person);
			ua.setUserId(personId);
			person.setUseraccount(ua);
			doctor.setPerson(person);
			doctor.setPersonId(personId);
			getSession().save(doctor);
			commit();

		} else if (role.equalsIgnoreCase("Mayor")) {
			Mayor mayor = person.getMayor();

			ua.setRole("Mayor");
			getSession().save(ua);
			long personId = (Long) getSession().save(person);
			ua.setUserId(personId);
			person.setUseraccount(ua);
			mayor.setPerson(person);
			mayor.setPersonId(personId);
			getSession().save(mayor);
			commit();
		} else if (role.equalsIgnoreCase("PublicHealthofficer")) {
			Publichealthofficer pho = person.getPublichealthofficer();

			ua.setRole("PHO");
			getSession().save(ua);
			long personId = (Long) getSession().save(person);
			ua.setUserId(personId);
			person.setUseraccount(ua);
			pho.setPerson(person);
			pho.setPersonId(personId);
			getSession().save(pho);
			commit();
		}

	}

	public List getVitalSignList(Patient patient, Date startDate, Date endDate) {
		System.out.println("startDate" + startDate);
		System.out.println("End Date" + endDate);
		System.out.println("patient" + patient.getPersonId());
		begin();
		Criteria crit = getSession().createCriteria(Vitalsign.class);
		crit.add(Restrictions.between("captureDate", startDate, endDate));
		crit.add(Restrictions.eq("patient", patient));
		System.out.println(patient.getPersonId());
		List<Vitalsign> vsList = crit.list();
		System.out.println("vslist" + vsList);
		return vsList;

	}

	public List list() throws AdException {
		try {
			begin();
			Query q = getSession()
					.createQuery("select symptomsId,symptomsDescription from Symptomslist where activeKey='Y'");
			List list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw e;
			// throw new AdException("Could not list the categories", e);
		}
	}

	public void enterLoginDetails(Useraccount ua) {
		begin();
		Logindetails details = new Logindetails();
		details.setCreationTime(new Date());
		details.setLoginTime(new Date());
		details.setUseraccount(ua);
		getSession().save(details);

		System.out.println("Inside dao" + ua.getUserId());

		commit();

	}

	@SuppressWarnings("unchecked")
	public Person create(HashMap<String, Object> inputMap) throws AdException {
		try {
			begin();
			System.out.println(inputMap);
			String update = (String) inputMap.get("update");
			String firstName = (String) inputMap.get("firstName");
			String lastName = (String) inputMap.get("lastName");
			Person person = (Person) inputMap.get("person");
			Patient patient = (Patient) inputMap.get("patient");
			// Person person = new Person(firstName, lastName);

			Useraccount ua = (Useraccount) inputMap.get("mappedUserAccount");
			Address addresHome = (Address) inputMap.get("homeAddressId");
			if (null != update) {
				Useraccount authenticatedUser = (Useraccount) inputMap.get("authenticatedUser");

				getSession().merge(authenticatedUser);
				Address address = authenticatedUser.getPerson().getAddressByHomeAddressId();
				getSession().save(address);
				authenticatedUser.getPerson().setAddressByHomeAddressId(address);
				getSession().merge(authenticatedUser.getPerson().getPatient());
				getSession().merge(authenticatedUser.getPerson());

				// getSession().update(addresHome);
				// getSession().update(patient);
				//
				// getSession().update(ua);
				// getSession().update(person);
				// person.setUseraccount(ua);
				// person.setAddressByHomeAddressId(addresHome);

				commit();
				return person;
			} else {
				System.out.println("inside DAO");

				// PErson email=new Email(emailId);

				person.setDob((Date) inputMap.get("dob"));
				person.setEmailId((String) inputMap.get("emailId"));

				person.setAddressByHomeAddressId(addresHome);
				getSession().save(addresHome);

				patient.setPerson(person);
				person.setPatient(patient);
				getSession().save(patient);
				getSession().save(ua);
				Long id = (Long) getSession().save(person);
				ua.setUserId(id);

				Date date = new Date();
				person.setUseraccount(ua);

				ua.setPerson(person);

				// person.setCreationTime(date);
				// person.setModifiedTime(date);
				// addresHome.setCreationTime(date);
				// addresHome.setModifiedTime(date);
				// ua.setCreationTime(date);
				// ua.setModifiedTime(date);
				// ua.setPerson(person);
				// person.setUseraccount(ua);
				// addresHome.setAddressLine1((String)inputMap.get(""));

				System.out.println("Inside dao" + ua.getUserId());

				commit();
				close();
				return person;
			}
		} catch (HibernateException e) {
			rollback();
			throw e;
			// throw new AdException("Could not create user " + username, e);
			// throw new AdException("Exception while creating user: " +
			// e.getMessage());
		}
	}

	// public void delete(User user)
	// throws AdException {
	// try {
	// begin();
	// getSession().delete(user);
	// commit();
	// } catch (HibernateException e) {
	// rollback();
	// throw new AdException("Could not delete user " + user.getName(), e);
	// }
	// }

	public String addVitalSignsAndSymptoms(HashMap<String, Object> inputMap) {

		begin();
		Patient patient = (Patient) inputMap.get("patient");
		Vitalsign vs = (Vitalsign) inputMap.get("vitalSign");
		ArrayList<String> symptomsId = vs.getSymptomsSelected();
		System.out.println("patient" + patient.getPersonId());
		vs.setPatient(patient);
		Set<Symptomslist> set = new HashSet<Symptomslist>();
		for (String symptomId : symptomsId) {
			// Symptomslist list = new Symptomslist();
			// list.setSymptomsId(Long.valueOf(symptomId));
			// list.setCreationTime(new Date());
			// list.setModifiedTime(new Date());
			Query q = getSession().createQuery("from Symptomslist where symptomsId=:symptomsId");
			q.setString("symptomsId", symptomId);
			Symptomslist list = (Symptomslist) q.uniqueResult();
			set.add(list);
		}

		vs.setSymptomsList(set);
		getSession().save(vs);

		commit();
		return "SUCCESS";

	}

	public boolean checkIfUserExists(String userName) {
		begin();
		Query q = getSession().createQuery("from Useraccount where userName = :username");
		q.setString("username", userName);
		Useraccount ua = (Useraccount) q.uniqueResult();

		if (null != ua) {
			return true;
		}
		commit();
		return false;
	}

	public boolean checkIfDoctorExists(String practiceNo) {
		begin();
		Query q = getSession().createQuery("from Doctor where practiceNo = :practiceNo");
		q.setString("practiceNo", practiceNo);
		Doctor ua = (Doctor) q.uniqueResult();

		if (null != ua) {
			return true;
		}
		commit();
		return false;
	}

	public Vitalsign checkVitalSignEntryDone(Patient patient, Date captureDate) {
		begin();
		Query q = getSession().createQuery("from Vitalsign where patient = :patient and captureDate = :captureDate");
		q.setEntity("patient", patient);
		q.setDate("captureDate", captureDate);
		Vitalsign vs = (Vitalsign) q.uniqueResult();

		commit();
		return vs;
	}

	public Vitalsign getVitalSign(HashMap<String, Object> inputMap) {
		begin();
		Date captureDate = (Date) inputMap.get("captureDate");
		Criteria crit = getSession().createCriteria(Vitalsign.class);
		crit.add(Restrictions.allEq(inputMap));
		List<Vitalsign> vitalSign = crit.list();
		if (null != vitalSign && vitalSign.size() > 0)
			return vitalSign.get(0);
		else
			return null;

	}

	public List<Indoorairquality> getIndoorAQI(Patient patient, Date date) {
		begin();
		Criteria crit = getSession().createCriteria(Indoorairquality.class);

		crit.add(Restrictions.eq("captureDate", date));
		crit.add(Restrictions.eq("patient", patient));
		List<Indoorairquality> aqi = crit.list();
		// if (null != vitalSign && vitalSign.size() > 0)
		// return vitalSign.get(0);
		// else
		return aqi;

	}

	public List<Patient> getPatientsList(HashMap<String, Object> inputMap) throws AdException {
		try {
			begin();
			Doctor doctor = (Doctor) inputMap.get("doctor");
			System.out.println(doctor.getPracticeNo());
			String practiceNo = doctor.getPracticeNo();
			Query q =getSession().createQuery("from Patient where doctorPracticeNo=:doctorPracticeNo");
			q.setString("doctorPracticeNo", practiceNo);
			
			Criteria crit = getSession().createCriteria(Patient.class);
			crit.add(Restrictions.eq("doctorPracticeNo", practiceNo));

			List<Patient> patientsList = q.list();//crit.list();
			
			
			commit();
			
			System.out.println(patientsList.size());
			return patientsList;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not list the patients", e);
		}

	}

	public List searchPatientsList(HashMap<String, Object> inputMap) throws AdException {
		try {
			begin();
			Doctor doctor = (Doctor) inputMap.get("doctor");
			System.out.println(doctor.getPracticeNo());
			String firstName = (String) inputMap.get("firstName");
			String lastName = (String) inputMap.get("lastName");

			String practiceNo = doctor.getPracticeNo();
			Criteria c = getSession().createCriteria(Person.class);
			c.add(Restrictions.eq("firstName", firstName.toUpperCase()));
			c.add(Restrictions.eq("lastName", lastName.toUpperCase()));
			List persons = c.list();
			System.out.println(persons);
			List<Long> ids = new ArrayList<Long>();
			Iterator it = persons.iterator();
			while (it.hasNext()) {
				Person person = (Person) it.next();
				ids.add(person.getPersonId());
			}

			Criteria crit = getSession().createCriteria(Patient.class);
			crit.add(Restrictions.eq("doctorPracticeNo", practiceNo));
			crit.add(Restrictions.in("personId", ids));

			List patientsList = crit.list();
			commit();
			return patientsList;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not list the patients", e);
		}

	}

	public Patient getPatient(long personId) throws AdException {
		try {
			begin();

			Criteria crit = getSession().createCriteria(Patient.class);
			crit.add(Restrictions.idEq(personId));
			Patient patient = (Patient) crit.uniqueResult();
			commit();
			return patient;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get patient", e);
		}
	}

	public double getOutdoorAqi(String zip, Date captureDate) throws AdException {
		try {
			begin();
			double aqi = 0;
			Criteria crit = getSession().createCriteria(Outdoorairquality.class);
			crit.add(Restrictions.eq("zipCode", Integer.valueOf(zip)));
			crit.add(Restrictions.eq("captureDate", captureDate));
			Outdoorairquality aqiOut = (Outdoorairquality) crit.uniqueResult();
			if (aqiOut != null)
				aqi = aqiOut.getAqi();
			commit();
			return aqi;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Error retrieving Outdoor AQI");
		}

	}

	public Indoorairquality getIndoorAqi(Date date, Patient patient) throws AdException {
		try {
			begin();
			Criteria crit = getSession().createCriteria(Indoorairquality.class);
			crit.add(Restrictions.eq("patient", patient));
			crit.add(Restrictions.eq("captureDate", date));
			List aqiList = (List) crit.list();
			Indoorairquality aqi = null;
			if (null != aqiList && aqiList.size() > 0)
				aqi = (Indoorairquality) aqiList.get(0);
			commit();
			return aqi;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Error retrieving Outdoor AQI");
		}
	}

	public List getIndoorAQIAvg(Patient patient, Date startDate, Date endDate) throws AdException {
		try {
			begin();
			Criteria crit = getSession().createCriteria(Indoorairquality.class);
			crit.add(Restrictions.between("captureDate", startDate, endDate));
			crit.add(Restrictions.eq("patient", patient));
			List list = crit.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Error retrieving Weekly Indoor aQI", e);
		}
	}

	public List getOutdoorAQIAvg(int zip, Date startDate, Date endDate) throws AdException {
		try {
			begin();
			Criteria crit = getSession().createCriteria(Outdoorairquality.class);
			crit.add(Restrictions.between("captureDate", startDate, endDate));
			crit.add(Restrictions.eq("zipCode", Integer.valueOf(zip)));
			List list = crit.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Error retrieving Weekly Indoor aQI", e);
		}
	}
	
	public List getAverageVitalSign(Date startDate,Date endDate,Patient patient)
	{
		begin();
		
			Criteria c = getSession().createCriteria(Vitalsign.class);
			c.add(Restrictions.eq("patient", patient));
			c.add(Restrictions.between("captureDate", startDate, endDate));
			List<Vitalsign> vsList =c.list();
			
			commit();
			close();
			return vsList;
		
		
	}
}
