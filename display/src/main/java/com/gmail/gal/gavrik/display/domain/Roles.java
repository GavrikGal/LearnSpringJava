package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles implements Serializable {
	private static final long	serialVersionUID	= -684162762837065878L;
	private Long				idRoles;
	private String				role;
	private Set<Users>			users;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Role")
	public Long getIdRoles() {
		return idRoles;
	}

	public void setIdRoles(Long idRoles) {
		this.idRoles = idRoles;
	}

	@Column(name = "Role")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ManyToMany
	@JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "role"), inverseJoinColumns = @JoinColumn(name = "user"))
	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	
	
}
