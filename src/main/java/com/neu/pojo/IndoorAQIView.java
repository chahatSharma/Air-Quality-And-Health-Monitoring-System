package com.neu.pojo;

import java.util.Date;

public class IndoorAQIView {

	private double aqi;
	private String respPoll;
	private Date date;
	private String meaning;
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public double getAqi() {
		return aqi;
	}
	public void setAqi(double aqi) {
		this.aqi = aqi;
	}
	public String getRespPoll() {
		return respPoll;
	}
	public void setRespPoll(String respPoll) {
		this.respPoll = respPoll;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
