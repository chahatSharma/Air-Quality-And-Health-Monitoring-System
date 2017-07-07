package com.neu.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.neu.dao.AddUserDao;
import com.neu.pojo.Indoorairquality;
import com.neu.pojo.Outdoorairquality;
import com.neu.pojo.Useraccount;

public class LoadAQIFromExcelJob implements Job{
	
	
public void run()
{
	AddUserDao dao = new AddUserDao();
	List<Useraccount> userList= dao.getUserList();
	for(Useraccount ua:userList)
	{
		startDataload(ua);
	}
}
	public void startDataload(Useraccount ua) {
		
		enterData("HourlyDataIndoorAQI_" + ua.getUserId() + ".xlsx",
				"AverageDataIndoorAQI_" + ua.getUserId()  + ".xlsx", "Indoor",ua);
//		enterData(
//				"resources//excel//HourlyDataOutdoorAQI_" + ua.getPerson().getAddressByHomeAddressId().getZipcode() + ".xlsx",
//				"resources//excel//AverageDataOutdoorAQI_" + ua.getPerson().getAddressByHomeAddressId().getZipcode() + ".xlsx",
//				"Outdoor");
	}

	private void enterData(String fileName1, String fileName2, String type,Useraccount ua) {
		try {
			ClassLoader classLoader = LoadAQIFromExcelJob.class.getClassLoader();
			// System.out.println(fileName1);
			
			System.out.println(classLoader.getSystemResourceAsStream(fileName1) );
File filess = new File("C:/Users/Angad/workspace/FinalProject_webtools/src/main/webapp/resources/excel/"+fileName1);
			//File filess = new File(classLoader.getResource(fileName1).getFile());
			System.out.println(filess);
			if (filess.exists()) {
				FileInputStream file = new FileInputStream(filess);

				Workbook workbook = new XSSFWorkbook(file);

				// Get first/desired sheet from the workbook
				Sheet sheet = workbook.getSheetAt(0);
				int rowCount = sheet.getLastRowNum();
				//if (ua.getPerson().getPatient().getAirQualityHist().getAirQualityHistory().size() < rowCount) {
					ArrayList<Indoorairquality> tempp = new ArrayList<Indoorairquality>();
					for (int i = 1; i < 2; i++) {System.out.println("excel");
						System.out.println(sheet.getRow(i).getCell(0).getDateCellValue());
						Indoorairquality airQualitySample = new Indoorairquality();
						airQualitySample.setCaptureDate(sheet.getRow(i).getCell(0).getDateCellValue());
						airQualitySample.setTvoc(
								Double.valueOf(String.valueOf(sheet.getRow(i).getCell(1).getNumericCellValue())));
//						airQualitySample.setCo2(
//								Double.valueOf(String.valueOf(sheet.getRow(i).getCell(2).getNumericCellValue())));
//						airQualitySample.setOzone(
//								Double.valueOf(String.valueOf(sheet.getRow(i).getCell(3).getNumericCellValue())));
//						airQualitySample.setPm2dot5(
//								Double.valueOf(String.valueOf(sheet.getRow(i).getCell(4).getNumericCellValue())));
//						airQualitySample.setPm10(
//								Double.valueOf(String.valueOf(sheet.getRow(i).getCell(5).getNumericCellValue())));
//						airQualitySample
//								.setCo(Double.valueOf(String.valueOf(sheet.getRow(i).getCell(6).getNumericCellValue())));
//						airQualitySample.setSo2(
//								Double.valueOf(String.valueOf(sheet.getRow(i).getCell(7).getNumericCellValue())));
//						airQualitySample.setNox(
//								Double.valueOf(String.valueOf(sheet.getRow(i).getCell(8).getNumericCellValue())));
//						airQualitySample.setTemp(
//								Double.valueOf(String.valueOf(sheet.getRow(i).getCell(9).getNumericCellValue())));
//						airQualitySample.setRelHum(
//								Double.valueOf(String.valueOf(sheet.getRow(i).getCell(10).getNumericCellValue())));
						airQualitySample.setAqi(
								Double.valueOf(String.valueOf(sheet.getRow(i).getCell(11).getNumericCellValue())));
						airQualitySample.setRepPollutant(sheet.getRow(i).getCell(12).getStringCellValue());
						//airQualitySample.setCaptureTime((Short)sheet.getRow(i).getCell(13).getNumericCellValue());
						System.out.println(ua.getPerson().getPatient());
						//airQualitySample.setType(type);(type);
						tempp.add(airQualitySample);
						airQualitySample.setPatient(ua.getPerson().getPatient());
						AddUserDao dao = new AddUserDao();
						
						
						dao.addAirQuality(airQualitySample);
						//airQualitySample.setPatient(patient);
						//ua.getPerson().getPatient().getAirQualityHist().getAirQualityHistory().add(airQualitySample);
					//}
					// System.out.println(tempp.get(0).getDateOfCapturing());

					//ua.getPerson().getPatient().setIndoorAirQuality(tempp);
					File file2 = new File("C:/Users/Angad/workspace/FinalProject_webtools/src/main/webapp/resources/excel/"+fileName2);
					if (file2.exists()) {
						file = new FileInputStream(file2);
						workbook = new XSSFWorkbook(file);

						// Get first/desired sheet from the workbook
						sheet = workbook.getSheetAt(0);
						int rows = sheet.getLastRowNum();
						// System.out.println("rowcount is" + rows);
						ArrayList<Outdoorairquality> temp = new ArrayList<Outdoorairquality>();
						for (int j = 1; j < rows + 1; j++) {
							Outdoorairquality avgAQI = new Outdoorairquality();
							avgAQI.setCaptureDate(sheet.getRow(j).getCell(0).getDateCellValue());
							avgAQI.setAqi(
									Float.valueOf(String.valueOf(sheet.getRow(j).getCell(1).getNumericCellValue())));
							avgAQI.setRespPollutant(sheet.getRow(j).getCell(2).getStringCellValue());
							//avgAQI.setZipCode(02139);
							//ua.getPerson().getPatient().getAvgAQIHist().addAvgAirQuality(avgAQI);
							if (type.equalsIgnoreCase("Indoor")) {
								// System.out.println("date from sheet" +
								// sheet.getRow(j).getCell(0).getDateCellValue());
								try {
									// System.out.println(ua.getPerson().getPatient().getAvgAQIHist().getAirQualityHistory().get(j-1).getDateOfCapturing());
								} catch (Exception e) {
									System.err.println(e);
								}
							}
							//temp.add(avgAQI);
						}
						//System.out.println(avgAQI);
						//ua.getPerson().getPatient().setIndoorAirQualityAvg(temp);
					}
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}

	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		AddUserDao dao = new AddUserDao();
		List<Useraccount> userList= dao.getUserList();
		for(Useraccount ua:userList)
		{
			startDataload(ua);
		}
		
	}

}
