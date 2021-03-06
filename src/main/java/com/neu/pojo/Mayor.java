package com.neu.pojo;
//Generated Apr 9, 2016 10:44:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
* Mayor generated by hbm2java
*/
@Entity
@Table(name = "mayor", catalog = "webtools")
public class Mayor implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2265244783969827978L;
	private long personId;
	private Person person;
	private String cityOfficial;
	private String mayor;

	public Mayor() {
	}

	public Mayor(Person person) {
		this.person = person;
	}

	public Mayor(Person person, String cityOfficial, String mayor) {
		this.person = person;
		this.cityOfficial = cityOfficial;
		this.mayor = mayor;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "person"))
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "PersonId", unique = true, nullable = false)
	public long getPersonId() {
		return this.personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Column(name = "CityOfficial", length = 20)
	public String getCityOfficial() {
		return this.cityOfficial;
	}

	public void setCityOfficial(String cityOfficial) {
		this.cityOfficial = cityOfficial;
	}

	@Column(name = "Mayor", length = 200)
	public String getMayor() {
		return this.mayor;
	}

	public void setMayor(String mayor) {
		this.mayor = mayor;
	}

}
