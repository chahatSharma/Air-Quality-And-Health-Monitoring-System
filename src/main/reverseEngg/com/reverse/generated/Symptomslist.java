package com.reverse.generated;
// Generated Apr 2, 2016 9:49:27 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Symptomslist generated by hbm2java
 */
@Entity
@Table(name = "symptomslist", catalog = "webtools")
public class Symptomslist implements java.io.Serializable {

	private long symptomsId;
	private String symptomsDescription;
	private Character activeKey;

	public Symptomslist() {
	}

	public Symptomslist(long symptomsId) {
		this.symptomsId = symptomsId;
	}

	public Symptomslist(long symptomsId, String symptomsDescription, Character activeKey) {
		this.symptomsId = symptomsId;
		this.symptomsDescription = symptomsDescription;
		this.activeKey = activeKey;
	}

	@Id

	@Column(name = "SymptomsId", unique = true, nullable = false)
	public long getSymptomsId() {
		return this.symptomsId;
	}

	public void setSymptomsId(long symptomsId) {
		this.symptomsId = symptomsId;
	}

	@Column(name = "Symptoms Description", length = 200)
	public String getSymptomsDescription() {
		return this.symptomsDescription;
	}

	public void setSymptomsDescription(String symptomsDescription) {
		this.symptomsDescription = symptomsDescription;
	}

	@Column(name = "Active Key", length = 1)
	public Character getActiveKey() {
		return this.activeKey;
	}

	public void setActiveKey(Character activeKey) {
		this.activeKey = activeKey;
	}

}
