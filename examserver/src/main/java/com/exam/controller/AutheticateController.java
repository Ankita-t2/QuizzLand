package com.exam.controller;

import com.exam.helper.UserNotFoundException;
import com.exam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.exam.config.JwtUtil;
import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.service.impl.UserDetailsServiceimpl;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AutheticateController {
	@Autowired
	private AuthenticationManager authenticationmanager;
	
	@Autowired
	private UserDetailsServiceimpl userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	//User Authentication complete
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws UserNotFoundException {
		try {
			authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new UserNotFoundException();
		}
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUserName());
		String token=this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	private void authenticate(String username,String password) throws Exception  {
		
		try {
			authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(username, password)); 
			
		}catch (DisabledException e) {
			throw new Exception("USER DISABLED"+e.getMessage());
		}catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials"+e.getMessage());
			
		}
		
	}


	@GetMapping("/current-user")
	public User getcurrentUser(Principal principal){
		return ( (User) this.userDetailsService.loadUserByUsername(principal.getName()));
	}
	

}
