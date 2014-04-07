package com.gmail.gal.gavrik.display.service.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.gal.gavrik.display.domain.Roles;
import com.gmail.gal.gavrik.display.domain.Users;
import com.gmail.gal.gavrik.display.repository.UsersRepository;
import com.gmail.gal.gavrik.display.service.UsersService;
import com.gmail.gal.gavrik.display.service.jpa.CustomUserDetails.CustomUserDetails;
import com.google.common.collect.Lists;

@Service("usersService")
@Repository
@Transactional
public class UsersServiceImpl implements UsersService, UserDetailsService {

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

	public Users save(Users user) {
		return usersRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Users userFromDB = usersRepository.findByUserName(userName);
		User user;
		
		if (userFromDB != null) {
			System.out.println("Get user. \nUserName: " + userFromDB.getUserName()
					+ "\nPassword: " + userFromDB.getPassword()
					+ "\nid: " + userFromDB.getIdUser()
					+ "\nFirstName: " + userFromDB.getFirstName()
					+ "\nLastName:" + userFromDB.getLastName());
			if (userFromDB.getRoles() != null) {
				System.out.println("Roles:");
				Collection<GrantedAuthority> userRoles = new ArrayList<GrantedAuthority>();
				for (Roles role : userFromDB.getRoles()) {
					userRoles.add(new SimpleGrantedAuthority(role.getRole()));
					System.out.println("     " + role.getRole());					
				}				
				user = new CustomUserDetails(userFromDB, userRoles);
				
			} else {
				throw new UsernameNotFoundException("У пользователя отсутствую привилегии");
			}
		} else {
			System.out.println("Can not find user with UserName: " + userName);
			throw new UsernameNotFoundException("В системе пока нет пользователя: " + userName);
		}
		
		return user;
	}

}
