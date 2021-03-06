package com.neu.pojo;
// Generated Apr 9, 2016 10:44:56 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Outdoorairquality generated by hbm2java
 */
@Entity
@Table(name = "outdoorairquality", catalog = "webtools")
public class Outdoorairquality implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4407090126119480469L;
	private long outdoorAqid;
	private double aqi;
	private Date captureDate;
	private String respPollutant;
	private Double temp;
	private int zipCode;
	private Date creationTime;
	private Date modifiedTime;

	public Outdoorairquality() {
	}

	public Outdoorairquality(long outdoorAqid, double aqi, Date captureDate, int zipCode, Date creationTime,
			Date modifiedTime) {
		this.outdoorAqid = outdoorAqid;
		this.aqi = aqi;
		this.captureDate = captureDate;
		this.zipCode = zipCode;
		this.creationTime = creationTime;
		this.modifiedTime = modifiedTime;
	}

	public Outdoorairquality(long outdoorAqid, double aqi, Date captureDate, String respPollutant, Double temp,
			int zipCode, Date creationTime, Date modifiedTime) {
		this.outdoorAqid = outdoorAqid;
		this.aqi = aqi;
		this.captureDate = captureDate;
		this.respPollutant = respPollutant;
		this.temp = temp;
		this.zipCode = zipCode;
		this.creationTime = creationTime;
		this.modifiedTime = modifiedTime;
	}

	@Id

	@Column(name = "OutdoorAQId", unique = true, nullable = false)
	public long getOutdoorAqid() {
		return this.outdoorAqid;
	}

	public void setOutdoorAqid(long outdoorAqid) {
		this.outdoorAqid = outdoorAqid;
	}

	@Column(name = "AQI", nullable = false, precision = 22, scale = 0)
	public double getAqi() {
		return this.aqi;
	}

	public void setAqi(double aqi) {
		this.aqi = aqi;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "captureDate", nullable = false, length = 10)
	public Date getCaptureDate() {
		return this.captureDate;
	}

	public void setCaptureDate(Date captureDate) {
		this.captureDate = captureDate;
	}

	@Column(name = "respPollutant", length = 20)
	public String getRespPollutant() {
		return this.respPollutant;
	}

	public void setRespPollutant(String respPollutant) {
		this.respPollutant = respPollutant;
	}

	@Column(name = "temp", precision = 22, scale = 0)
	public Double getTemp() {
		return this.temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	@Column(name = "zipCode", nullable = false)
	public int getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationTime", nullable = false, length = 19)
	public Date getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifiedTime", nullable = false, length = 19)
	public Date getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

}
