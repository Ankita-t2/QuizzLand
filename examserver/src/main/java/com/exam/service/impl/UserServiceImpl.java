package com.exam.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.DoubleStream;

import com.exam.helper.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepo;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

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


//	@Override
//	public User updateUser(User user) {
//		return userRepository.save(user); // Save the user entity to update it
//	}

	@Override
	@Transactional // Make sure this method runs in a transaction
	public User updateUser(User user) {
		// Get the managed entity from the database
		User existingUser = userRepository.findByUserName(user.getUsername());
		if (existingUser != null) {
			// Update the fields of the managed entity with the new values
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setEmail(user.getEmail());
			existingUser.setPhone(user.getPhone());
			// Check if the profile image is not null, then update it
			if (user.getProfile() != null) {
				existingUser.setProfile(user.getProfile());
			}
			// Save the updated entity
			return userRepository.save(existingUser);
		} else {
			// Handle the case when the user is not found
			throw new IllegalArgumentException("User not found with username: " + user.getUsername());
		}
	}

	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
		
	}
	
}
