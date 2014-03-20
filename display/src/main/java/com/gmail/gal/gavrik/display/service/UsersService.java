package com.gmail.gal.gavrik.display.service;

import java.util.List;


import com.gmail.gal.gavrik.display.domain.Users;

public interface UsersService {
	
	public List<Users> findAll();
	
	public Users findById(Long id);
	
	public Users findByFirstName(String firstName);

	public Users save(Users users);

}
