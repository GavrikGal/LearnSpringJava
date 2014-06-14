package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "measurements")
public class Measurements implements Serializable {

	private static final long	serialVersionUID	= -1257165263393374914L;

	private Long				idMeasurements;
	private Equipments			equipment;
	private DateOfMeasurement	dateOfMeasurement;
//	private String				description;
	private DateOfMeasurement	dateOfSecondMeasurement;
	private List<Spectrums>		spectrums;
//	private List<Users>			users;
	private Users user;

	
	
	
//	@Column(name = "Description")
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}

	@ManyToOne
	@JoinColumn(name = "Date_of_measurement")
	public DateOfMeasurement getDateOfMeasurement() {
		return dateOfMeasurement;
	}

	public void setDateOfMeasurement(DateOfMeasurement date) {
		this.dateOfMeasurement = date;
	}

	@ManyToOne
	@JoinColumn(name = "Date_of_second_measurement")
	public DateOfMeasurement getDateOfSecondMeasurement() {
		return dateOfSecondMeasurement;
	}

	public void setDateOfSecondMeasurement(DateOfMeasurement dateOfSecondMeasurement) {
		this.dateOfSecondMeasurement = dateOfSecondMeasurement;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Measurements")
	public Long getIdMeasurements() {
		return idMeasurements;
	}

	@ManyToOne
	@JoinColumn(name = "Equipment")
	public Equipments getEquipment() {
		return equipment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setIdMeasurements(Long idMeasurements) {
		this.idMeasurements = idMeasurements;
	}

	public void setEquipment(Equipments equipment) {
		this.equipment = equipment;
	}
	
	

//	public void setUsers(List<Users> users) {
//		this.users = users;
//	}
//
//	@ManyToMany
//	@JoinTable(name = "users_of_measurement", joinColumns = @JoinColumn(name = "Measurement"), inverseJoinColumns = @JoinColumn(name = "User"))
//	@OrderBy("firstName")
//	@LazyCollection(LazyCollectionOption.FALSE)
//	public List<Users> getUsers() {
//		return users;
//	}

	@ManyToOne
	@JoinColumn(name = "User")
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@OneToMany(mappedBy = "measurement", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("time")
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<Spectrums> getSpectrums() {
		return spectrums;
	}

	public void setSpectrums(List<Spectrums> spectrums) {
		this.spectrums = spectrums;
	}

}
