package com.neu.pojo;
// Generated Apr 9, 2016 10:44:56 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Symptomsreported generated by hbm2java
 */
@Entity
@Table(name = "symptomsreported", catalog = "webtools")
public class Symptomsreported implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7938444702630695107L;
	private long reportId;
	private Indoorairquality indoorairquality;
	private Patient patient;
	private Vitalsign vitalsign;
	private Date dateReported;
	private Date creationTime;
	private Date modifiedTime;
	//private Symptomslist symptomsList;

	public Symptomsreported() {
	}

	public Symptomsreported(long reportId, Patient patient, Date creationTime, Date modifiedTime) {
		this.reportId = reportId;
		this.patient = patient;
		this.creationTime = creationTime;
		this.modifiedTime = modifiedTime;
	}

	public Symptomsreported(long reportId, Indoorairquality indoorairquality, Patient patient, Vitalsign vitalsign,
			Date dateReported, Date creationTime, Date modifiedTime) {
		this.reportId = reportId;
		this.indoorairquality = indoorairquality;
		this.patient = patient;
		this.vitalsign = vitalsign;
		this.dateReported = dateReported;
		this.creationTime = creationTime;
		this.modifiedTime = modifiedTime;
	}

	@Id

	@Column(name = "ReportId", unique = true, nullable = false)
	public long getReportId() {
		return this.reportId;
	}

	public void setReportId(long reportId) {
		this.reportId = reportId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AirQualityId")
	public Indoorairquality getIndoorairquality() {
		return this.indoorairquality;
	}

	public void setIndoorairquality(Indoorairquality indoorairquality) {
		this.indoorairquality = indoorairquality;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PersonId", nullable = false)
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VitalSignId")
	public Vitalsign getVitalsign() {
		return this.vitalsign;
	}

	public void setVitalsign(Vitalsign vitalsign) {
		this.vitalsign = vitalsign;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DateReported", length = 10)
	public Date getDateReported() {
		return this.dateReported;
	}

	public void setDateReported(Date dateReported) {
		this.dateReported = dateReported;
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
