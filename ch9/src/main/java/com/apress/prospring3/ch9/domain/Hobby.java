package com.apress.prospring3.ch9.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "hobby")
public class Hobby implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 775296496060215634L;
	private String hobbyId;
	private Set<Contact> contacts = new HashSet<Contact>();

	@ManyToMany
	@JoinTable(name = "contact_hobby_detail", 
		joinColumns = @JoinColumn(name = "HOBBY_ID"), 
		inverseJoinColumns = @JoinColumn(name = "CONTACT_ID"))
	public Set<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	@Id
	@Column(name = "HOBBY_ID")
	public String getHobbyId() {
		return hobbyId;
	}

	public void setHobbyId(String hobbyId) {
		this.hobbyId = hobbyId;
	}

	public String toString() {
		return "Hobby: " + getHobbyId();
	}
}
