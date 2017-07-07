package com.neu.pojo;

import java.util.Date;

public class GoogleMap {
	private String zip;
	private Date date;
	private double aqi;
	
	
	public double getAqi() {
		return aqi;
	}
	public void setAqi(double aqi) {
		this.aqi = aqi;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}
