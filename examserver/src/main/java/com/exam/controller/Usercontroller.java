package com.exam.controller;



import java.util.HashSet;
import java.util.Set;

import com.exam.helper.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.helper.UserNotFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class Usercontroller {
	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception{
		//Set<UserRole> roles=new HashSet<>();
		user.setProfile("default.jpg");
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		Set<UserRole> roles=new HashSet<>();
		
		Role role=new Role();
		role.setRoleId(45L);
		role.setRoleName("Normal");
		
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		roles.add(userRole);
		return	this.userService.createUser(user,roles );
		
	}
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
		
	}
	
	//delete the user by id
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId")Long userId) {
		this.userService.deleteUser(userId);
	}
	
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<String> exceptionHandler(UserFoundException ex){

	        return new ResponseEntity<>("User Found", HttpStatus.FOUND);
	}
	

}
