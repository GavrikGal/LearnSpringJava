package com.gmail.gal.gavrik.display.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.gal.gavrik.display.domain.Users;
import com.gmail.gal.gavrik.display.repository.UsersRepository;
import com.gmail.gal.gavrik.display.service.UsersService;
import com.google.common.collect.Lists;

@Service("usersService")
@Repository
@Transactional
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository	usersRepository;

	@Transactional(readOnly = true)
	public List<Users> findAll() {
		return Lists.newArrayList(usersRepository.findAll());
	}

	@Transactional(readOnly = true)
	public Users findById(Long id) {
		return usersRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public Users findByFirstName(String firstName) {
		return usersRepository.findByFirstName(firstName);
	}

	public Users save(Users users) {
		return usersRepository.save(users);
	}

}
