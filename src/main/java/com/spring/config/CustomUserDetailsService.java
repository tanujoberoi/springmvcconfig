package com.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spring.model.User;
import com.spring.service.UserServiceImpl;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserServiceImpl userservice;

	@SuppressWarnings("null")
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		  User user = userservice.findUserByUsername(username);
		  System.out.println(user);
		  if(user == null)
		  {
			  throw new UsernameNotFoundException("UserName "+user.getUsername()+" not found");
		  }
		return new SecurityUser(user);
	}

}
