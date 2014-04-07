package com.gmail.gal.gavrik.display.service.jpa.CustomUserDetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.gmail.gal.gavrik.display.domain.Users;

public class CustomUserDetails extends User {

	private static final long	serialVersionUID	= -4205754242760057139L;
	private Users				usersDetails;
	private String				firstName;
	private String				lastName;

	public CustomUserDetails(Users user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getUserName(), user.getPassword(), authorities);
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.usersDetails = user;
	}

	public Users getUsersDetails() {
		return usersDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setUsersDetails(Users usersDetails) {
		this.usersDetails = usersDetails;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	
}
