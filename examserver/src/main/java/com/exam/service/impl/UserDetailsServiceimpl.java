package com.exam.service.impl;

import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.repo.UserRepository;

@Service
public class UserDetailsServiceimpl implements UserDetailsService {
	@Autowired 
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user =this.userRepository.findByUserName(username);
		if(user==null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("No user Found");
		}
		
		
		return user;
	}
	

}
