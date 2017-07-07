package com.reverse.generated;
// Generated Apr 2, 2016 9:49:27 PM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Address generated by hbm2java
 */
@Entity
@Table(name = "address", catalog = "webtools")
public class Address implements java.io.Serializable {

	private long addressId;
	private String addressType;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private Integer zipcode;
	private Long phoneNo;
	private Set personsForOfficeAddressId = new HashSet(0);
	private Set personsForHomeAddressId = new HashSet(0);

	public Address() {
	}

	public Address(long addressId) {
		this.addressId = addressId;
	}

	public Address(long addressId, String addressType, String addressLine1, String addressLine2, String city,
			String state, Integer zipcode, Long phoneNo, Set personsForOfficeAddressId, Set personsForHomeAddressId) {
		this.addressId = addressId;
		this.addressType = addressType;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.phoneNo = phoneNo;
		this.personsForOfficeAddressId = personsForOfficeAddressId;
		this.personsForHomeAddressId = personsForHomeAddressId;
	}

	@Id

	@Column(name = "AddressID", unique = true, nullable = false)
	public long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	@Column(name = "AddressType", length = 20)
	public String getAddressType() {
		return this.addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	@Column(name = "AddressLine1", length = 150)
	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	@Column(name = "AddressLine2", length = 150)
	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	@Column(name = "City", length = 200)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "State", length = 100)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "Zipcode")
	public Integer getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "PhoneNo")
	public Long getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "addressByOfficeAddressId")
	public Set getPersonsForOfficeAddressId() {
		return this.personsForOfficeAddressId;
	}

	public void setPersonsForOfficeAddressId(Set personsForOfficeAddressId) {
		this.personsForOfficeAddressId = personsForOfficeAddressId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "addressByHomeAddressId")
	public Set getPersonsForHomeAddressId() {
		return this.personsForHomeAddressId;
	}

	public void setPersonsForHomeAddressId(Set personsForHomeAddressId) {
		this.personsForHomeAddressId = personsForHomeAddressId;
	}

}