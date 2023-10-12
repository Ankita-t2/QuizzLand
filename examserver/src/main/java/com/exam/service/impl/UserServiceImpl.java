package com.exam.service.impl;

import java.util.Set;

import com.exam.helper.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepo;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepo roleRepository;
	
	//creating user
	@Override
	public User createUser(User user,Set<UserRole>userRoles) throws UserFoundException {
		
		User local =this.userRepository.findByUserName(user.getUsername());
		if(local!=null) {
		System.out.println("User is aleready there!");
		throw new UserFoundException();
		}
		else {
			//create user
			for(UserRole ur:userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local=this.userRepository.save(user);
		}
		
		return local;
		
		
	}

	//getting user by username
	@Override
	public User getUser(String username) {
		
		return this.userRepository.findByUserName(username);
	}

	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
		
	}
	
}
