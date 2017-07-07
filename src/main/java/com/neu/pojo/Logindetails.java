package com.neu.pojo;
// Generated Apr 9, 2016 10:44:56 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Logindetails generated by hbm2java
 */
@Entity
@Table(name = "logindetails", catalog = "webtools")
public class Logindetails implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7171900300493201374L;
	private long loginId;
	private Useraccount useraccount;
	private Date loginTime;
	private Date logoutTime;
	private Date creationTime;
	private Date modifiedTime;
	private String passwordChangedFlag;
	private Date passwordChangedDate;

	public Logindetails() {
	}

	public Logindetails(long loginId, Useraccount useraccount) {
		this.loginId = loginId;
		this.useraccount = useraccount;
	}

	public Logindetails(long loginId, Useraccount useraccount, Date loginTime, Date logoutTime, Date creationTime,
			Date modifiedTime, String passwordChangedFlag, Date passwordChangedDate) {
		this.loginId = loginId;
		this.useraccount = useraccount;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.creationTime = creationTime;
		this.modifiedTime = modifiedTime;
		this.passwordChangedFlag = passwordChangedFlag;
		this.passwordChangedDate = passwordChangedDate;
	}

	@Id
	@GenericGenerator(name="loginId",strategy="increment")
	@GeneratedValue(generator="loginId")
	@Column(name = "loginId", unique = true, nullable = false)
	public long getLoginId() {
		return this.loginId;
	}

	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserID", nullable = false)
	public Useraccount getUseraccount() {
		return this.useraccount;
	}

	public void setUseraccount(Useraccount useraccount) {
		this.useraccount = useraccount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "loginTime", length = 19)
	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "logoutTime", length = 19)
	public Date getLogoutTime() {
		return this.logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationTime", length = 19)
	public Date getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifiedTime", length = 19)
	public Date getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Column(name = "passwordChangedFlag", length = 1)
	public String getPasswordChangedFlag() {
		return this.passwordChangedFlag;
	}

	public void setPasswordChangedFlag(String passwordChangedFlag) {
		this.passwordChangedFlag = passwordChangedFlag;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "passwordChangedDate", length = 19)
	public Date getPasswordChangedDate() {
		return this.passwordChangedDate;
	}

	public void setPasswordChangedDate(Date passwordChangedDate) {
		this.passwordChangedDate = passwordChangedDate;
	}

}
