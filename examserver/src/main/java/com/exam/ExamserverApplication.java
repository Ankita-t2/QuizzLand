package com.exam;

import java.util.HashSet;
import java.util.Set;

import com.exam.helper.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}
	
	
	public void run(String... args)throws Exception{
		System.out.println("starting code");
		try {


			User user = new User();
			user.setEmail("ankita@gmail.com");
			//user.setEnabled();
			user.setFirstName("Ankita");
			user.setId(null);
			user.setLastName("Thorat");

			user.setPassword(this.bCryptPasswordEncoder.encode("m"));
			user.setUserName("Ankita21");
			//user.setProfile("");

			Role role1 = new Role();
			role1.setRoleId(44L);
			role1.setRoleName("admin");
			Set<UserRole> userRoleSet = new HashSet<>();
			UserRole userRole = new UserRole();
			userRole.setRole(role1);
			userRole.setUser(user);
			userRoleSet.add(userRole);
			User user1 = this.userService.createUser(user, userRoleSet);


			System.out.println(user1.getUserName());
		}catch (UserFoundException e){
			e.printStackTrace();

		}
		
		
	}
}
