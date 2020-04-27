package com.gourab.learning.springsecurityjpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gourab.learning.springsecurityjpa.model.MyUserDetails;
import com.gourab.learning.springsecurityjpa.model.User;
import com.gourab.learning.springsecurityjpa.model.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user =  userRepository.findUserByName(username);
		return new MyUserDetails(user.get());
	}

}
