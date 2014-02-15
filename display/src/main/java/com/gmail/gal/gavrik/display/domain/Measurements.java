package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "measurements")
public class Measurements implements Serializable {

	private static final long	serialVersionUID	= -1257165263393374914L;

	private Long				idMeasurements;
	private Equipments			equipment;
	private Set<Users>			users				= new HashSet<Users>();

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

	@ManyToMany
	@JoinTable(name = "users_of_measurement", joinColumns = @JoinColumn(name = "Measurement"), inverseJoinColumns = @JoinColumn(name = "User"))
	public Set<Users> getUsers() {
		return this.users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	@Transient
	public List<Users> getUsersAsList() {
		return new ArrayList<Users>(users);
	}

	public String toString() {
		return "Measurements - Id: " + idMeasurements ;
	}
}
