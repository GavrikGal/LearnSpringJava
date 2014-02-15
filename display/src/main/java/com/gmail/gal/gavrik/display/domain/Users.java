package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users implements Serializable {

	private static final long	serialVersionUID	= 7925769595205799558L;

	private Long				idUser;
	private String				FirstName;
	private String				LastName;
	private String				FatherName;
	private String				shortName;
	private Set<Measurements>	measurements		= new HashSet<Measurements>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_User")
	public Long getIdUsers() {
		return idUser;
	}

	public void setIdUsers(Long idUsers) {
		this.idUser = idUsers;
	}

	@Column(name = "First_name")
	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	@Column(name = "Last_name")
	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	@Column(name = "Father_name")
	public String getFatherName() {
		return FatherName;
	}

	public void setFatherName(String fatherName) {
		FatherName = fatherName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ManyToMany
	@JoinTable(name = "users_of_measurement", joinColumns = @JoinColumn(name = "User"), inverseJoinColumns = @JoinColumn(name = "Measurement"))
	public Set<Measurements> getMeasurements() {
		return this.measurements;
	}

	public void setMeasurements(Set<Measurements> measurements) {
		this.measurements = measurements;
	}

	@Transient
	public List<Measurements> getMeasurementsAsList() {
		return new ArrayList<Measurements>(measurements);
	}

	@Column(name = "Short_name")
	public String getShortName() {
		if (shortName == null) {
			shortName = FirstName + " " + LastName.substring(0, 1).toUpperCase() + "."
					+ FatherName.substring(0, 1).toUpperCase() + ".";
		}

		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}
