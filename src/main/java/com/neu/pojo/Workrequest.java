package com.neu.pojo;
// Generated Apr 9, 2016 10:44:56 PM by Hibernate Tools 4.3.1.Final

import java.beans.Transient;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import com.neu.business.Auditable;



/**
 * Workrequest generated by hbm2java
 */
@Entity
@Table(name = "workrequest", catalog = "webtools")
public class Workrequest implements java.io.Serializable, Auditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1653140612842693165L;
	private long requestId;
	private Person personBySenderId;
	private Person personByReceiverId;
	private Useraccount useraccount;
	private String status;
	private String message;
	private Date requestDate;
	private Date resolveDate;
	private Date creationTime;
	private Date modifiedTime;
	private String reply;
	
	private String fileName;
	private String location;

	public Workrequest() {
	}

	public Workrequest(long requestId, Person personBySenderId, Person personByReceiverId, Date creationTime,
			Date modifiedTime) {
		this.requestId = requestId;
		this.personBySenderId = personBySenderId;
		this.personByReceiverId = personByReceiverId;
		this.creationTime = creationTime;
		this.modifiedTime = modifiedTime;
	}

	public Workrequest(long requestId, Person personBySenderId, Person personByReceiverId, Useraccount useraccount,
			String status, String message, Date requestDate, Date resolveDate, Date creationTime, Date modifiedTime) {
		this.requestId = requestId;
		this.personBySenderId = personBySenderId;
		this.personByReceiverId = personByReceiverId;
		this.useraccount = useraccount;
		this.status = status;
		this.message = message;
		this.requestDate = requestDate;
		this.resolveDate = resolveDate;
		this.creationTime = creationTime;
		this.modifiedTime = modifiedTime;
	}

	@Id
	@GenericGenerator(name="requestId",strategy="increment")
	@GeneratedValue(generator="requestId")
	@Column(name = "requestId", unique = true, nullable = false)
	public long getRequestId() {
		return this.requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "senderID", nullable = false)
	public Person getPersonBySenderId() {
		return this.personBySenderId;
	}

	public void setPersonBySenderId(Person personBySenderId) {
		this.personBySenderId = personBySenderId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ReceiverId", nullable = false)
	public Person getPersonByReceiverId() {
		return this.personByReceiverId;
	}

	public void setPersonByReceiverId(Person personByReceiverId) {
		this.personByReceiverId = personByReceiverId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserID")
	public Useraccount getUseraccount() {
		return this.useraccount;
	}

	public void setUseraccount(Useraccount useraccount) {
		this.useraccount = useraccount;
	}

	@Column(name = "status", length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "message", length = 2000)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "requestDate", length = 10)
	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "resolveDate", length = 10)
	public Date getResolveDate() {
		return this.resolveDate;
	}

	public void setResolveDate(Date resolveDate) {
		this.resolveDate = resolveDate;
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
	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name = "reply", length = 2000)
	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	
	
	

}
