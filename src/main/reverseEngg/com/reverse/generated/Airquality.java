package com.reverse.generated;
// Generated Apr 2, 2016 9:49:27 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Airquality generated by hbm2java
 */
@Entity
@Table(name = "airquality", catalog = "webtools")
public class Airquality implements java.io.Serializable {

	private long airQualityId;
	private Patient patient;
	private Double tvoc;
	private Double co2;
	private Double co;
	private Double ozone;
	private Double temp;
	private Double relHum;
	private Double dewPoint;
	private String type;
	private Double aqi;
	private Double pm10;
	private Double nox;
	private Double pm2dot5;
	private Double so2;
	private Double repPollutant;
	private Date captureDate;
	private Short captureTime;

	public Airquality() {
	}

	public Airquality(long airQualityId) {
		this.airQualityId = airQualityId;
	}

	public Airquality(long airQualityId, Patient patient, Double tvoc, Double co2, Double co, Double ozone, Double temp,
			Double relHum, Double dewPoint, String type, Double aqi, Double pm10, Double nox, Double pm2dot5,
			Double so2, Double repPollutant, Date captureDate, Short captureTime) {
		this.airQualityId = airQualityId;
		this.patient = patient;
		this.tvoc = tvoc;
		this.co2 = co2;
		this.co = co;
		this.ozone = ozone;
		this.temp = temp;
		this.relHum = relHum;
		this.dewPoint = dewPoint;
		this.type = type;
		this.aqi = aqi;
		this.pm10 = pm10;
		this.nox = nox;
		this.pm2dot5 = pm2dot5;
		this.so2 = so2;
		this.repPollutant = repPollutant;
		this.captureDate = captureDate;
		this.captureTime = captureTime;
	}

	@Id

	@Column(name = "AirQualityId", unique = true, nullable = false)
	public long getAirQualityId() {
		return this.airQualityId;
	}

	public void setAirQualityId(long airQualityId) {
		this.airQualityId = airQualityId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "PatientId", referencedColumnName = "PatientId"),
			@JoinColumn(name = "PersonId", referencedColumnName = "PersonId") })
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Column(name = "tvoc", precision = 22, scale = 0)
	public Double getTvoc() {
		return this.tvoc;
	}

	public void setTvoc(Double tvoc) {
		this.tvoc = tvoc;
	}

	@Column(name = "co2", precision = 22, scale = 0)
	public Double getCo2() {
		return this.co2;
	}

	public void setCo2(Double co2) {
		this.co2 = co2;
	}

	@Column(name = "co", precision = 22, scale = 0)
	public Double getCo() {
		return this.co;
	}

	public void setCo(Double co) {
		this.co = co;
	}

	@Column(name = "ozone", precision = 22, scale = 0)
	public Double getOzone() {
		return this.ozone;
	}

	public void setOzone(Double ozone) {
		this.ozone = ozone;
	}

	@Column(name = "temp", precision = 22, scale = 0)
	public Double getTemp() {
		return this.temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	@Column(name = "relHum", precision = 22, scale = 0)
	public Double getRelHum() {
		return this.relHum;
	}

	public void setRelHum(Double relHum) {
		this.relHum = relHum;
	}

	@Column(name = "dewPoint", precision = 22, scale = 0)
	public Double getDewPoint() {
		return this.dewPoint;
	}

	public void setDewPoint(Double dewPoint) {
		this.dewPoint = dewPoint;
	}

	@Column(name = "type", length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "AQI", precision = 22, scale = 0)
	public Double getAqi() {
		return this.aqi;
	}

	public void setAqi(Double aqi) {
		this.aqi = aqi;
	}

	@Column(name = "PM10", precision = 22, scale = 0)
	public Double getPm10() {
		return this.pm10;
	}

	public void setPm10(Double pm10) {
		this.pm10 = pm10;
	}

	@Column(name = "NOx", precision = 22, scale = 0)
	public Double getNox() {
		return this.nox;
	}

	public void setNox(Double nox) {
		this.nox = nox;
	}

	@Column(name = "PM2Dot5", precision = 22, scale = 0)
	public Double getPm2dot5() {
		return this.pm2dot5;
	}

	public void setPm2dot5(Double pm2dot5) {
		this.pm2dot5 = pm2dot5;
	}

	@Column(name = "SO2", precision = 22, scale = 0)
	public Double getSo2() {
		return this.so2;
	}

	public void setSo2(Double so2) {
		this.so2 = so2;
	}

	@Column(name = "repPollutant", precision = 22, scale = 0)
	public Double getRepPollutant() {
		return this.repPollutant;
	}

	public void setRepPollutant(Double repPollutant) {
		this.repPollutant = repPollutant;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CaptureDate", length = 10)
	public Date getCaptureDate() {
		return this.captureDate;
	}

	public void setCaptureDate(Date captureDate) {
		this.captureDate = captureDate;
	}

	@Column(name = "CaptureTime")
	public Short getCaptureTime() {
		return this.captureTime;
	}

	public void setCaptureTime(Short captureTime) {
		this.captureTime = captureTime;
	}

}
