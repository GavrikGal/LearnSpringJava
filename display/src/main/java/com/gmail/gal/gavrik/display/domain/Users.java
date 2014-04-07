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
	private String				UserName;
	private String				Password;
	private Set<Measurements>	measurements		= new HashSet<Measurements>();
	private Set<Roles>			roles;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_User")
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUsers) {
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

	// @ManyToMany
	// @JoinTable(name = "users_of_measurement", joinColumns = @JoinColumn(name
	// = "User"), inverseJoinColumns = @JoinColumn(name = "Measurement"))
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
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

	@Column(name = "User_name")
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	@Column(name = "Password")
	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	@ManyToMany
	@JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "user"), inverseJoinColumns = @JoinColumn(name = "role"))
	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

}
