package com.reverse.generated;
// Generated Apr 2, 2016 9:49:27 PM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Patient generated by hbm2java
 */
@Entity
@Table(name = "patient", catalog = "webtools")
public class Patient implements java.io.Serializable {

	private PatientId id;
	private Person person;
	private Set symptomsreporteds = new HashSet(0);
	private Set vitalsigns = new HashSet(0);
	private Set airqualities = new HashSet(0);

	public Patient() {
	}

	public Patient(PatientId id, Person person) {
		this.id = id;
		this.person = person;
	}

	public Patient(PatientId id, Person person, Set symptomsreporteds, Set vitalsigns, Set airqualities) {
		this.id = id;
		this.person = person;
		this.symptomsreporteds = symptomsreporteds;
		this.vitalsigns = vitalsigns;
		this.airqualities = airqualities;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "patientId", column = @Column(name = "PatientId", nullable = false)),
			@AttributeOverride(name = "personId", column = @Column(name = "PersonId", nullable = false)) })
	public PatientId getId() {
		return this.id;
	}

	public void setId(PatientId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PersonId", nullable = false, insertable = false, updatable = false)
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set getSymptomsreporteds() {
		return this.symptomsreporteds;
	}

	public void setSymptomsreporteds(Set symptomsreporteds) {
		this.symptomsreporteds = symptomsreporteds;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set getVitalsigns() {
		return this.vitalsigns;
	}

	public void setVitalsigns(Set vitalsigns) {
		this.vitalsigns = vitalsigns;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set getAirqualities() {
		return this.airqualities;
	}

	public void setAirqualities(Set airqualities) {
		this.airqualities = airqualities;
	}

}
