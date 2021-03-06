package com.neu.pojo;
// Generated Apr 9, 2016 10:44:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Citylist generated by hbm2java
 */
@Entity
@Table(name = "citylist", catalog = "webtools", uniqueConstraints = @UniqueConstraint(columnNames = "cityName"))
public class Citylist implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1857316419344139701L;
	private long cityId;
	private Statelist statelist;
	private String cityName;

	public Citylist() {
	}

	public Citylist(long cityId, Statelist statelist, String cityName) {
		this.cityId = cityId;
		this.statelist = statelist;
		this.cityName = cityName;
	}

	@Id

	@Column(name = "cityId", unique = true, nullable = false)
	public long getCityId() {
		return this.cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stateId", nullable = false)
	public Statelist getStatelist() {
		return this.statelist;
	}

	public void setStatelist(Statelist statelist) {
		this.statelist = statelist;
	}

	@Column(name = "cityName", unique = true, nullable = false, length = 200)
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
